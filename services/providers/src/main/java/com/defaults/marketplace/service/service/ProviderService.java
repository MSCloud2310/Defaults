package com.defaults.marketplace.service.service;

import com.defaults.marketplace.service.exception.AlreadyExistException;
import com.defaults.marketplace.service.exception.NotFoundException;
import com.defaults.marketplace.service.model.SocialMedia;
import com.defaults.marketplace.service.repository.ProviderRepository;
import com.defaults.marketplace.service.model.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {
    @Autowired
    ProviderRepository repository;

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

    public Provider updateProvider(String id, List<SocialMedia> socialMedia) {
        Optional<Provider> optionalProvider = repository.findById(id);
        if(optionalProvider.isEmpty()) {
            throw new AlreadyExistException("Provider not found");
        }
        Provider provider = optionalProvider.get();
        provider.setSocialMedia(socialMedia);
        return repository.save(provider);
    }
}
