package com.gers.gers.repository;

import com.gers.gers.models.SellSpares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SellSpareRepo  extends JpaRepository<SellSpares,Long> {

    @Query(value = "SELECT u FROM SellSpares u WHERE u.user LIKE %?1")
    public List<SellSpares> findUserSpares(@Param("user") String user);


    @Query(value = "SELECT u FROM SellSpares u WHERE u.orders = ?1")
     List<SellSpares> findOrderSpares( Long orders);

    @Query(value = "SELECT SUM(totalAmount) FROM SellSpares u WHERE u.orders = ?1")
    BigDecimal amount(Long orders);

    @Query(value = "SELECT COUNT(*) FROM SellSpares ")
    int  allSpares();


}
