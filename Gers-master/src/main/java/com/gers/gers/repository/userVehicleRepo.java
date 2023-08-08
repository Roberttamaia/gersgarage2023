package com.gers.gers.repository;

import com.gers.gers.models.userVehicle;
import jakarta.persistence.NamedQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface userVehicleRepo extends JpaRepository<userVehicle,Long> {


    @Query(value = "SELECT u FROM userVehicle u WHERE u.user LIKE %?1"

    )
    List<userVehicle> findByUser(@Param("user") String user);
}
