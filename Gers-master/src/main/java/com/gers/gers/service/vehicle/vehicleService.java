package com.gers.gers.service.vehicle;

import com.gers.gers.models.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface vehicleService {

    Vehicle createNew(Vehicle vehicle);
    List<Vehicle> showAllVehicles();
    Vehicle getOneVehicle(Long id);

    Vehicle updateVehicle(Vehicle vehicle);

    void  deleteVehicle(Long id);
}
