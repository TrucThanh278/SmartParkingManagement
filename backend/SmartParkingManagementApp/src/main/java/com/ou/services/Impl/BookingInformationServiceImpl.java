package com.ou.services.Impl;

import com.ou.dto.request.BookingInfoRequestDTO;
import com.ou.dto.request.BookingInfoUpdateRequestDTO;
import com.ou.dto.response.BookingInformationResponseDTO;
import com.ou.mappers.BookingInfoMapper;
import com.ou.pojo.BookingInformation;
import com.ou.pojo.ParkingLot;
import com.ou.pojo.ParkingSpot;
import com.ou.pojo.Vehicle;
import com.ou.repositories.BookingInformationRepository;
import com.ou.repositories.ParkingLotRepository;
import com.ou.repositories.ParkingSpotsRepository;
import com.ou.repositories.VehicleRepository;
import com.ou.services.BookingInformationService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingInformationServiceImpl implements BookingInformationService {

    @Autowired
    private BookingInformationRepository bookingInformationRepository;

    @Autowired
    private BookingInfoMapper bookingInfoMapper;

    @Autowired
    private ParkingSpotsRepository parkingSpotRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Override
    public List<BookingInformation> getBookingListWithPakingSpotId(Integer id) {
        return this.bookingInformationRepository.getBookingListWithParkingSpotId(id);
    }

    @Override
    public List<BookingInformation> getBookingListOfParkingLot(Integer id) {
        return this.bookingInformationRepository.getBookingListOfParkingLot(id);
    }

    @Override
    public BookingInformation addBookingInfo(BookingInfoRequestDTO dtoBookingInfoRequest) {
        BookingInformation bookingInformation = bookingInfoMapper.addBookingInfo(dtoBookingInfoRequest);

        ParkingSpot parkingSpot = parkingSpotRepository.findById(dtoBookingInfoRequest.getParkingSpotId());
        Vehicle vehicle = vehicleRepository.findById(dtoBookingInfoRequest.getVehicleId());

        ParkingLot parkingLot = parkingSpot.getParkingLotId();

        if (!bookingInformation.isWithinParkingLotHours(parkingLot)) {
            throw new IllegalArgumentException("Booking times are outside of the parking lot operational hours.");
        }

        bookingInformation.setParkingSpotId(parkingSpot);
        bookingInformation.setVehicleId(vehicle);

        BookingInformation savedBookingInfo = bookingInformationRepository.saveBookingInfo(bookingInformation);

        if (parkingSpot != null) {
            parkingSpot.setStatus(true);
            parkingSpotRepository.save(parkingSpot);
        }

        return savedBookingInfo;
    }

    @Override
    public BookingInformationResponseDTO updateBookingInfo(Integer intgr, BookingInfoUpdateRequestDTO dtbr) {
        BookingInformation existingBookingInfo = bookingInformationRepository.findById(intgr);

        bookingInfoMapper.updateBookingInfoFromDto(dtbr, existingBookingInfo);

        BookingInformation updatedBookingInfo = bookingInformationRepository.saveBookingInfo(existingBookingInfo);

        return bookingInfoMapper.toDto(updatedBookingInfo);
    }

    @Override
    public void deleteBookingInfo(Integer id) {
        BookingInformation bookingInformation = bookingInformationRepository.findById(id);

        if (bookingInformation != null) {

            bookingInformation.setParkingSpotId(null);
            bookingInformation.setVehicleId(null);
            bookingInformationRepository.saveBookingInfo(bookingInformation);

            bookingInformationRepository.deleteBookingInfo(id);
        }
    }

    @Override
    public List<BookingInformation> getBookingListByCurrentDateAndParkingSpotId(LocalDate currentDate,  Integer parkingSpotId) {
        return bookingInformationRepository.getBookingListByCurrentDateAndParkingSpotId(currentDate, parkingSpotId);
    }

    @Override
    public List<BookingInformation> getBookingListWithUserId(int userId) {
        return this.bookingInformationRepository.getBookingListWithUserId(userId);
    }
}
