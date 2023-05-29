package com.defaults.marketplace.productsearch.repository;

import com.defaults.marketplace.productsearch.models.entities.ServiceC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceC, Integer> {
    @Query(value = "SELECT * FROM services WHERE title LIKE ?1 OR description LIKE ?1", nativeQuery = true)
    List<ServiceC> findAllByFilter(String filter);
}
