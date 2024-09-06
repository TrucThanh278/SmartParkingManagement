package com.ou.repositories;

import com.ou.pojo.BookingInformation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingInformationRepository {

    List<BookingInformation> getBookingListWithParkingSpotId(Integer id);

    List<BookingInformation> getBookingListOfParkingLot(Integer id);

    BookingInformation saveBookingInfo(BookingInformation bookingInformation);

    BookingInformation findById(Integer id);

    void deleteBookingInfo(Integer id);

    List<BookingInformation> getBookingListByCurrentDateAndParkingSpotId(LocalDate currentDate, Integer parkingSpotId);
    
    List<BookingInformation> getBookingListWithUserId(int userId);
}
