package com.gers.gers.service.vehicle;

import com.gers.gers.models.Vehicle;
import com.gers.gers.repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class vehicleServImpl implements vehicleService{
@Autowired
    private VehicleRepo vehicleRepo;
    /**
     * @param vehicle
     * @return
     */
    @Override
    public Vehicle createNew(Vehicle vehicle) {
        return vehicleRepo.save(vehicle);
    }

    /**
     * @return
     */
    @Override
    public List<Vehicle> showAllVehicles() {
        return vehicleRepo.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Vehicle getOneVehicle(Long id) {
        return vehicleRepo.findById(id).get();
    }

    /**
     * @param vehicle
     * @return
     */
    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        return vehicleRepo.save(vehicle);
    }

    /**
     * @param id
     */
    @Override
    public void deleteVehicle(Long id) {

    }
}
