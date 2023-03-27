package com.defaults.marketplace.msservices.repository;

import com.defaults.marketplace.msservices.models.entities.SocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialMediaRepository extends JpaRepository<SocialMedia, Integer> {

    List<SocialMedia> findAllByProviderId(Integer providerId);
}
