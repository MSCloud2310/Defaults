package com.defaults.marketplace.msservices.repository;

import com.defaults.marketplace.msservices.models.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {

    Provider findProviderById(Integer id);
}
