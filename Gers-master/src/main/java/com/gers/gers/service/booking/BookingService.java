package com.gers.gers.service.booking;

import com.gers.gers.models.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {

    Booking createNew(Booking booking);

    List<Booking> getAllBookings();

    Booking getOneBooking(Long id);
    Booking updateBooking(Booking booking);

    Booking newOrder(Booking booking);

    List<Booking> getUserBookings(String username);

    List<Booking> getNewBookings();
    List<Booking> getBookings();
    List<Booking> getCollectedBookings();
    List<Booking> getScrappedBookings();
    List<Booking> getFixedBookings();
    List<Booking> getServiceBookings();
    List<Booking> getLastRepair(String user);
    List<Booking> getMech(String mechanic);

    int getLastbooking();

    int getTotalBookings();





    void deleteBooking(Long id);

}
