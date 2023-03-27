package com.defaults.marketplace.productsearch.services;

import com.defaults.marketplace.productsearch.models.entities.ServiceC;
import com.defaults.marketplace.productsearch.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepository repository;
    @Autowired
    private QuestionService questionService;

    public ServiceC saveService(ServiceC serviceC){
        return repository.save(serviceC);
    }

    public List<ServiceC> getServices(){
        return repository.findAll();
    }

    public ServiceC getServiceById(Integer id){
        return repository.findServiceCById(id);
    }

    public ServiceC updateService(ServiceC serviceC){
        ServiceC existingService = repository.findServiceCById(serviceC.getId());
        existingService.setCategory(serviceC.getCategory());
        existingService.setTitle(serviceC.getTitle());
        existingService.setDescription(serviceC.getDescription());
        existingService.setCountryCode(serviceC.getCountryCode());
        existingService.setCost(serviceC.getCost());
        existingService.setCapacity(serviceC.getCapacity());
        //serviceC.setProviderId(existingService.getProviderId());

        return repository.save(existingService);
    }

    public String deleteById(Integer id){
        repository.deleteById(id);
        return "Service deleted";
    }

    public String deleteByProviderId(Integer providerId){
        List<ServiceC> services = repository.findAllByProviderId(providerId);
        repository.deleteAll(services);
        return "Services deleted.";
    }

}
