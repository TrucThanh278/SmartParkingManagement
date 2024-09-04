package com.ou.services.Impl;

import com.ou.dto.request.DTOBookingInfoRequest;
import com.ou.dto.request.DTOBookingInfoUpdateRequest;
import com.ou.dto.response.DTOBookingInformationResponse;
import com.ou.mappers.BookingInfoMapper;
import com.ou.pojo.BookingInformation;
import com.ou.pojo.ParkingSpot;
import com.ou.pojo.Vehicle;
import com.ou.repositories.BookingInformationRepository;
import com.ou.repositories.ParkingSpotsRepository;
import com.ou.repositories.VehicleRepository;
import com.ou.services.BookingInformationService;
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

    @Override
    public List<BookingInformation> getBookingListWithPakingSpotId(Integer id) {
        return this.bookingInformationRepository.getBookingListWithParkingSpotId(id);
    }

    @Override
    public List<BookingInformation> getBookingListOfParkingLot(Integer id) {
        return this.bookingInformationRepository.getBookingListOfParkingLot(id);
    }

    @Override
    public BookingInformation addBookingInfo(DTOBookingInfoRequest dtoBookingInfoRequest) {
        BookingInformation bookingInformation = bookingInfoMapper.addBookingInfo(dtoBookingInfoRequest);

        ParkingSpot parkingSpot = parkingSpotRepository.findById(dtoBookingInfoRequest.getParkingSpotId());
        Vehicle vehicle = vehicleRepository.findById(dtoBookingInfoRequest.getVehicleId());

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
    public DTOBookingInformationResponse updateBookingInfo(Integer intgr, DTOBookingInfoUpdateRequest dtbr) {
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
}
