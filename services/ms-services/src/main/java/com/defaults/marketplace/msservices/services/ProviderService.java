package com.defaults.marketplace.msservices.services;

import com.defaults.marketplace.msservices.models.entities.Provider;
import com.defaults.marketplace.msservices.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {
    @Autowired
    private ProviderRepository repository;
    @Autowired
    private SocialMediaService socialMediaService;
    @Autowired
    private ServiceService serviceService;

    public Provider saveProvider(Provider provider){
        return repository.save(provider);
    }

    public List<Provider> getProviders(){
        return repository.findAll();
    }

    public Provider getProviderById(Integer id){
        return repository.findProviderById(id);
    }

    public boolean providerAlreadyExist(String publicName){
        List<Provider> existingProviders = getProviders();
        for (Provider provider:existingProviders){
            if (provider.getPublicName().equals(publicName)){
                return true;
            }
        }
        return false;
    }

    public Provider updateProvider(Provider provider){
        Provider existingProvider = repository.findProviderById(provider.getId());
        existingProvider.setSocialMedia(provider.getSocialMedia());
        existingProvider.setPublicName(provider.getPublicName());
        existingProvider.setPictureUrl(provider.getPictureUrl());
        existingProvider.setDescription(provider.getDescription());
        existingProvider.setPhoneNumber(provider.getPhoneNumber());
        existingProvider.setWebPage(provider.getWebPage());
        socialMediaService.deleteByProviderId(provider.getId());

        return repository.save(existingProvider);
    }

    public String deleteProvider(Integer id){
        repository.deleteById(id);
        return "Provider deleted";
    }
}
