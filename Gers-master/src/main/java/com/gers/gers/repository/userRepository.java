package com.gers.gers.repository;

import com.gers.gers.models.userInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<userInfo, Long> {
    Optional<userInfo> findByUsername(String username);
}
