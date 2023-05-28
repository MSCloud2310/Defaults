package com.defaults.marketplace.productsearch.services;

import com.defaults.marketplace.productsearch.models.entities.SocialMedia;
import com.defaults.marketplace.productsearch.repository.SocialMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialMediaService {
    @Autowired
    private SocialMediaRepository repository;

    public String deleteByProviderId(Integer providerId){
        List<SocialMedia> socialMedia = repository.findAllByProviderId(providerId);
        repository.deleteAll(socialMedia);
        return "Social media deleted.";
    }

}
