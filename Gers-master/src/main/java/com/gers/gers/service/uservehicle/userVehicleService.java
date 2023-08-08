package com.gers.gers.service.uservehicle;

import com.gers.gers.models.userVehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface userVehicleService {

    userVehicle createNew(userVehicle userVehicle);

    List<userVehicle> ShowAll();

    userVehicle getVehicle(Long id);

    userVehicle updateVehicle(userVehicle userVehicle);

    void  deleteVehicle(Long id);

    List <userVehicle>showUsersVehicle(String user);



}
