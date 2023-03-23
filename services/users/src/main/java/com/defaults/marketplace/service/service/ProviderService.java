package com.defaults.marketplace.service.service;

import com.defaults.marketplace.service.exception.AlreadyExistException;
import com.defaults.marketplace.service.exception.NotFoundException;
import com.defaults.marketplace.service.repository.ProviderRepository;
import com.defaults.marketplace.service.model.Provider;
import com.defaults.marketplace.service.repository.SocialMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {
    @Autowired
    ProviderRepository repository;

    @Autowired
    SocialMediaRepository socialMediaRepository;

    public List<Provider> findAllProviders() {
        return (List<Provider>) repository.findAll();
    }

    public Provider findProviderById(String id) {
        Optional<Provider> provider = repository.findById(id);
        if(provider.isEmpty()) {
            throw new NotFoundException("Provider not found");
        }
        return provider.get();
    }

    public boolean saveProvider(Provider provider) {
        repository.save(provider);
        return true;
    }

    public boolean deleteProvider(String id) {
        if(repository.findById(id).isEmpty()) {
            throw new NotFoundException("Provider not found");
        }
        repository.deleteById(id);
        return true;
    }

    public void updateProvider(Provider provider) {
        Optional<Provider> providerValidation = repository.findById(provider.getId());
        if(providerValidation.isEmpty()) {
            throw new AlreadyExistException("Provider not found");
        }
        providerValidation.get().setId(provider.getId());
        repository.save(providerValidation.get());
    }
}
