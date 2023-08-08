package com.gers.gers.service.spare;

import com.gers.gers.models.Spares;
import com.gers.gers.repository.SpareRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class spareServImpl implements  spareService{

    @Autowired
    private SpareRepo spareRepo;
    /**
     * @param spare
     * @return
     */
    @Override
    public Spares storeSpare(Spares spare) {
        return  spareRepo.save(spare);
    }

    /**
     * @return
     */
    @Override
    public List<Spares> getSpares() {
        return (List<Spares>) spareRepo.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Spares getOneSpare(Long id) {
        return spareRepo.findById(id).get();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Spares updateSpare(Spares spare) {
        return spareRepo.save(spare);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public void deleteSpare(Long id) {
         spareRepo.deleteById(id);
    }
}
