package com.defaults.marketplace.productsearch.repository;

import com.defaults.marketplace.productsearch.models.entities.ServiceC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceC, Integer> {
    List<ServiceC> findAllByProviderId(Integer providerId);
    ServiceC findServiceCById(Integer id);
}
