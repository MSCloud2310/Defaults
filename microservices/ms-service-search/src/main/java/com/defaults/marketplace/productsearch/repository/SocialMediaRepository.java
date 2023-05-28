package com.defaults.marketplace.productsearch.repository;

import com.defaults.marketplace.productsearch.models.entities.SocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialMediaRepository extends JpaRepository<SocialMedia, Integer> {

    List<SocialMedia> findAllByProviderId(Integer providerId);
}
