package com.defaults.marketplace.msservices.repository;

import com.defaults.marketplace.msservices.models.entities.ServiceC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceC, Integer> {

    ServiceC findServiceCById(Integer serviceId);
    List<ServiceC> findAllByProviderId(Integer providerId);
}
