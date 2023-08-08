package com.gers.gers.service.gerservices;


import com.gers.gers.models.gerServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface gerservice {

    gerServiceModel createService(gerServiceModel gerservice);

    List<gerServiceModel> showAllServices();

    gerServiceModel getService(Long id);

    gerServiceModel updateService(gerServiceModel gerservice);

    void  deleteService(Long id);
}
