package com.ou.repositories;

import com.ou.pojo.BookingInformation;
import java.util.List;


public interface BookingInformationRepository {
    public List<BookingInformation> getBookingListWithParkingSpotId(Integer id);
    public List<BookingInformation> getBookingListOfParkingLot(Integer id);
    BookingInformation saveBookingInfo (BookingInformation bookingInformation);
    BookingInformation findById(Integer id);
    void deleteBookingInfo(Integer id);
}
