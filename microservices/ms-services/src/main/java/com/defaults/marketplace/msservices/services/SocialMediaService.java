package com.defaults.marketplace.msservices.services;

import com.defaults.marketplace.msservices.models.entities.SocialMedia;
import com.defaults.marketplace.msservices.repository.SocialMediaRepository;
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
