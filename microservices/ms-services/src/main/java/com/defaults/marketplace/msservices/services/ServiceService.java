package com.defaults.marketplace.msservices.services;

import com.defaults.marketplace.msservices.models.entities.ServiceC;
import com.defaults.marketplace.msservices.models.entities.ServiceRating;
import com.defaults.marketplace.msservices.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepository repository;

    public ServiceC saveService(ServiceC serviceC) {
        return repository.save(serviceC);
    }

    public List<ServiceC> getServices() {
        return repository.findAll();
    }

    public ServiceC getServiceById(Integer id) {
        return repository.findServiceCById(id);
    }

    public ServiceC updateService(ServiceC serviceC) {
        ServiceC existingService = repository.findServiceCById(serviceC.getId());
        existingService.setCategory(serviceC.getCategory());
        existingService.setTitle(serviceC.getTitle());
        existingService.setDescription(serviceC.getDescription());
        existingService.setCountryCode(serviceC.getCountryCode());
        existingService.setCost(serviceC.getCost());
        existingService.setCapacity(serviceC.getCapacity());
        // serviceC.setProviderId(existingService.getProviderId());

        return repository.save(existingService);
    }

    public String deleteById(Integer id) {
        repository.deleteById(id);
        return "Service deleted";
    }

    public String deleteByProviderId(Integer providerId) {
        List<ServiceC> services = repository.findAllByProviderId(providerId);
        repository.deleteAll(services);
        return "Services deleted.";
    }

    // Rating methods

    public List<ServiceRating> getRatings (int serviceId) {
        ServiceC service = repository.findServiceCById(serviceId);
        return service.getRatings();
    }

    public ServiceC addRating (int serviceId, ServiceRating rating) {
        ServiceC service = repository.findServiceCById(serviceId);
        
        for (ServiceRating r : service.getRatings()) {
            if (r.getUserId() == rating.getUserId()) {
                r.setRating(rating.getRating());
                r.setComment(rating.getComment());
                repository.save(service);
                return service;
            }
        }
        service.getRatings().add(rating);
        return repository.save(service);
    }

    public Boolean deleteRating (int serviceId, int userId) {
        ServiceC service = repository.findServiceCById(serviceId);
        Boolean wasRemoved = service.getRatings().removeIf(rating -> rating.getUserId() == userId);
        repository.save(service);
        return wasRemoved;
    }

}
