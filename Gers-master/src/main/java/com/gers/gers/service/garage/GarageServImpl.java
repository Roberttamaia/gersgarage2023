package com.gers.gers.service.garage;

import com.gers.gers.models.Garage;
import com.gers.gers.repository.GarageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GarageServImpl implements GarageService{

    @Autowired
  private   GarageRepo garageRepo;
    /**
     * @param garage
     * @return
     */
    @Override
    public Garage create(Garage garage) {
        return garageRepo.save(garage);
    }

    /**
     * @return
     */
    @Override
    public List<Garage> showAll() {
        return garageRepo.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Garage getOne(Long id) {
        return garageRepo.findById(id).get();
    }

    /**
     * @param garage
     * @return
     */
    @Override
    public Garage update(Garage garage) {
        return garageRepo.save(garage);
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        garageRepo.deleteById(id);
    }
}
