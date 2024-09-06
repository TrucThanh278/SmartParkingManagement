package com.ou.services;

import com.ou.dto.request.BookingInfoRequestDTO;
import com.ou.dto.request.BookingInfoUpdateRequestDTO;
import com.ou.dto.response.BookingInformationResponseDTO;
import com.ou.pojo.BookingInformation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingInformationService {

    public List<BookingInformation> getBookingListWithPakingSpotId(Integer id);

    public List<BookingInformation> getBookingListOfParkingLot(Integer id);

    public BookingInformation addBookingInfo(BookingInfoRequestDTO dtoBookingInfoRequest);

    BookingInformationResponseDTO updateBookingInfo(Integer id, BookingInfoUpdateRequestDTO dtoBookingInfoUpdateRequest);

    void deleteBookingInfo(Integer id);

    List<BookingInformation> getBookingListByCurrentDateAndParkingSpotId(LocalDate currentDate,  Integer parkingSpotId);
}
