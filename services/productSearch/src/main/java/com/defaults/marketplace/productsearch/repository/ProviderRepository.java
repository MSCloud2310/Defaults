package com.defaults.marketplace.productsearch.repository;

import com.defaults.marketplace.productsearch.models.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {

    Provider findProviderById(Integer id);
}
