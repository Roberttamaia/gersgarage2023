package com.gers.gers.repository;

import com.gers.gers.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking,Long> {
    @Query(value = "SELECT u FROM Booking u WHERE u.user LIKE %?1")
    public List<Booking> getuserBookings(@Param("user") String user);

    @Query(value = "SELECT u FROM Booking u WHERE u.status = 'new'")
    public List<Booking> getNewBookings();

    @Query(value = "SELECT u.id FROM Booking u Order by u.id desc")
    public int getLastBookings();

    @Query(value = "SELECT u FROM Booking u WHERE  u.bookingDate = :bookingDate AND u.status = 'Booked' ORDER BY u.mechanic")
    public List<Booking> getRoster(@Param("bookingDate") LocalDate bookingDate);


    @Query(value = "SELECT u FROM Booking u WHERE  u.mechanic LIKE %?1 AND u.status = 'Booked'")
     public List<Booking> getMech(@Param("mechanic") String mechanic);
    @Query(value = "SELECT COUNT(*) FROM Booking")
    int geTotalBookings();
    @Query(value = "SELECT COUNT(u) FROM Booking u WHERE  u.status = 'Booked'")
    int TotalBookings();

    @Query(value = "SELECT u FROM Booking u WHERE  u.status = 'Booked'")
    public List<Booking> getBooked();

    @Query(value = "SELECT u FROM Booking u WHERE  u.status = 'Fixed' AND u.paid = 'notPaid'")
    public List<Booking> getFixedBookings();

    @Query(value = "SELECT u FROM Booking u WHERE  u.status = 'Fixed' AND  u.user LIKE %?1")
    public List<Booking> getlastRepair(@Param("user") String user);

    @Query(value = "SELECT u FROM Booking u WHERE  u.status = 'Scrapped'")
    public List<Booking> getScrappedBookings();

    @Query(value = "SELECT u FROM Booking u WHERE  u.status = 'Collected'")
    public List<Booking> getCollectedBookings();

    @Query(value = "SELECT u FROM Booking u WHERE  u.status = 'Service'")
    public List<Booking> getServiceBookings();


}
