package com.gers.gers.repository;

import com.gers.gers.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice,Long> {

    @Query(value = "SELECT SUM(totalAmount) FROM Invoice")
    BigDecimal amountTotal();
}
