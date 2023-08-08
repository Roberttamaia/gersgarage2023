package com.gers.gers.service.uservehicle;
import  com.gers.gers.repository.userVehicleRepo;
import com.gers.gers.models.userVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userVehicleServImpl implements  userVehicleService{
    @Autowired
    private  userVehicleRepo userVehicleRepo;
    /**
     * @param userVehicle
     * @return
     */
    @Override
    public userVehicle createNew(userVehicle userVehicle) {
        return userVehicleRepo.save(userVehicle);
    }

    /**
     * @return
     */
    @Override
    public List<userVehicle> ShowAll() {
        return userVehicleRepo.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public userVehicle getVehicle(Long id) {
        return userVehicleRepo.findById(id).get();
    }

    /**
     * @param userVehicle
     * @return
     */
    @Override
    public userVehicle updateVehicle(userVehicle userVehicle) {
        return userVehicleRepo.save(userVehicle);
    }

    /**
     * @param id
     */
    @Override
    public void deleteVehicle(Long id) {
        userVehicleRepo.deleteById(id);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public List<userVehicle> showUsersVehicle(String user) {
        return userVehicleRepo.findByUser(user);
    }
}
