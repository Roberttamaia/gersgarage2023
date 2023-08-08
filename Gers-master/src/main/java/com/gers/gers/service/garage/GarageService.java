package com.gers.gers.service.garage;

import com.gers.gers.models.Garage;
import com.gers.gers.repository.GarageRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface GarageService {

    Garage create(Garage garage);

    List<Garage> showAll();

    Garage getOne(Long id);

    Garage update(Garage garage);

    void delete(Long id);
}
