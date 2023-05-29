package com.defaults.marketplace.msservices.repository;

import com.defaults.marketplace.msservices.models.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    Location findLocationById(Integer id);
    List<Location> findAllByServiceId(Integer serviceId);
}
