package com.gers.gers.repository;

import com.gers.gers.models.gerServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface gerServiceRepo extends JpaRepository<gerServiceModel, Long> {

    @Query(value = "SELECT u.serviceIndex FROM gerServiceModel u WHERE  u.serviceName LIKE %?1")
    int servIndex(@Param("serviceName") String serviceName);
}
