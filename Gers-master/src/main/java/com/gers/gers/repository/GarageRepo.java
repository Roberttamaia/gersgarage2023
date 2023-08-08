package com.gers.gers.repository;

import com.gers.gers.models.Booking;
import com.gers.gers.models.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GarageRepo extends JpaRepository<Garage, Long> {

    @Query(value = "SELECT numberOfServicesPerDay FROM  Garage")
    int getService();

    @Query(value = "SELECT numberOfRepairPerEmployee FROM  Garage")
    int getMechs();
}
