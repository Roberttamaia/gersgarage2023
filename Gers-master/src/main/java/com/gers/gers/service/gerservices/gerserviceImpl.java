package com.gers.gers.service.gerservices;

import com.gers.gers.models.gerServiceModel;
import com.gers.gers.repository.gerServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class gerserviceImpl implements gerservice {
    @Autowired
    private gerServiceRepo ger ;
    /**
     * @param gerservice
     * @return
     */
    @Override
    public gerServiceModel createService(gerServiceModel gerservice) {
        return ger.save(gerservice);

    }

    /**
     * @return
     */
    @Override
    public List<gerServiceModel> showAllServices() {
        return ger.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public gerServiceModel getService(Long id) {
        return ger.findById(id).get();
    }

    /**
     * @param gerservice
     * @return
     */
    @Override
    public gerServiceModel updateService(gerServiceModel gerservice) {
        return ger.save(gerservice);
    }

    /**
     * @param id
     */
    @Override
    public void deleteService(Long id) {
        ger.deleteById(id);
    }
}
