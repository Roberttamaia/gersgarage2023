package com.gers.gers.service.booking;

import com.gers.gers.models.Booking;
import com.gers.gers.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BookingServImpl implements BookingService {
   @Autowired
    BookingRepo bookingRepo;


    /**
     * @param booking
     * @return
     */
    @Override
    public Booking createNew(Booking booking) {
        Random random = new Random();

        //String name = "Order/"+getTotalBookings() ;
        //booking.setOrderName(name);
        return bookingRepo.save(booking);
    }

    /**
     * @return
     */
    @Override
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Booking getOneBooking(Long id) {
        return bookingRepo.findById(id).get();
    }

    /**
     * @param booking
     * @return
     */
    @Override
    public Booking updateBooking(Booking booking) {
        return bookingRepo.save(booking);
    }

    /**
     * @param booking
     * @return
     */
    @Override
    public Booking newOrder(Booking booking) {
        return bookingRepo.save(booking);
    }

    @Override
    public List<Booking> getUserBookings(String username) {
        return bookingRepo.getuserBookings(username);
    }

    @Override
    public List<Booking> getNewBookings() {
        return bookingRepo.getNewBookings();
    }

    @Override
    public List<Booking> getBookings() {
        return bookingRepo.getBooked();
    }

    @Override
    public List<Booking> getCollectedBookings() {
        return bookingRepo.getCollectedBookings();
    }

    @Override
    public List<Booking> getScrappedBookings() {
        return bookingRepo.getScrappedBookings();
    }

    @Override
    public List<Booking> getFixedBookings() {
        return bookingRepo.getFixedBookings();
    }

    @Override
    public List<Booking> getServiceBookings() {
        return bookingRepo.getServiceBookings();
    }

    @Override
    public List<Booking> getLastRepair(String user) {
        return bookingRepo.getlastRepair(user);
    }

    @Override
    public List<Booking> getMech(String mechanic) {
        return bookingRepo.getMech(mechanic);
    }

    @Override
    public int getLastbooking() {
        return bookingRepo.getLastBookings();
    }


    @Override
    public int getTotalBookings() {
        return  bookingRepo.geTotalBookings();
    }



    /**
     * @param id
     */
    @Override
    public void deleteBooking(Long id) {
            bookingRepo.deleteById(id);
    }
}
