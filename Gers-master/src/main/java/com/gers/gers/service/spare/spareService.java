package com.gers.gers.service.spare;

import com.gers.gers.models.Spares;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface spareService {

    Spares storeSpare(Spares spare);
     List<Spares> getSpares();
     Spares getOneSpare(Long id);
     Spares updateSpare(Spares spare);

     void deleteSpare(Long id);
}
