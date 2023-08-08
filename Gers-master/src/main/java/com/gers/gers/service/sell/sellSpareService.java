package com.gers.gers.service.sell;

import com.gers.gers.models.SellSpares;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public interface sellSpareService {

      SellSpares createNew(SellSpares sellSpares);

      SellSpares getOne(Long id);
     List <SellSpares> allSales();

    List <SellSpares> findSales(String user);
    List <SellSpares> findOrder(Long orders);

   BigDecimal  totalAmount (Long orders);

     SellSpares updateSale(SellSpares sellSpares);



     void  deleteSale(Long id);

}
