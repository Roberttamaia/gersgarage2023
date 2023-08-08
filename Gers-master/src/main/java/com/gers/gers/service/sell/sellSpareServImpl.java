package com.gers.gers.service.sell;

import com.gers.gers.models.SellSpares;
import com.gers.gers.repository.SellSpareRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class sellSpareServImpl implements sellSpareService{
 @Autowired
  private  SellSpareRepo sellSpareRepo;

    /**
     * @param sellSpares
     * @return
     */
    @Override
    public SellSpares createNew(SellSpares sellSpares) {
        return sellSpareRepo.save(sellSpares);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SellSpares getOne(Long id) {
        return sellSpareRepo.findById(id).get();
    }

    /**
     * @return
     */
    @Override
    public List<SellSpares> allSales() {
        return sellSpareRepo.findAll();
    }

    @Override
    public List<SellSpares> findSales(String user) {
        return sellSpareRepo.findUserSpares(user);
    }

    @Override
    public List<SellSpares> findOrder(Long orders) {
        return sellSpareRepo.findOrderSpares(orders);
    }

    @Override
    public BigDecimal totalAmount(Long orders) {
        return sellSpareRepo.amount(orders);
    }

    /**
     * @param sellSpares
     * @return
     */
    @Override
    public SellSpares updateSale(SellSpares sellSpares) {
        return sellSpareRepo.save(sellSpares);
    }



    /**
     * @param id
     */
    @Override
    public void deleteSale(Long id) {
    sellSpareRepo.deleteById(id);
    }
}
