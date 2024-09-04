package com.ou.services;

import com.ou.dto.request.DTOBookingInfoRequest;
import com.ou.dto.request.DTOBookingInfoUpdateRequest;
import com.ou.dto.response.DTOBookingInformationResponse;
import com.ou.pojo.BookingInformation;
import java.util.List;

public interface BookingInformationService {
    public List<BookingInformation> getBookingListWithPakingSpotId(Integer id);
    public List<BookingInformation> getBookingListOfParkingLot(Integer id);
    public BookingInformation addBookingInfo (DTOBookingInfoRequest dtoBookingInfoRequest);
    DTOBookingInformationResponse updateBookingInfo(Integer id, DTOBookingInfoUpdateRequest dtoBookingInfoUpdateRequest);
    void deleteBookingInfo(Integer id);
}
