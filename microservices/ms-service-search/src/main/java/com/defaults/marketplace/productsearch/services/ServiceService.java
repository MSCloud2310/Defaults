package com.defaults.marketplace.productsearch.services;

import com.defaults.marketplace.productsearch.entities.SearchServicesResponse;
import com.defaults.marketplace.productsearch.repository.ServiceRepository;
import com.defaults.marketplace.productsearch.utils.Transform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepository repository;

    public SearchServicesResponse findAllServicesByFilter(String filter) {
        SearchServicesResponse response = new SearchServicesResponse();
        repository.findAllByFilter(filter)
                .forEach(result -> response.getService().add(Transform.execute(result)));
        return response;
    }
}
