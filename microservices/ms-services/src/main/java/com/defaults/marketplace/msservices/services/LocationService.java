package com.defaults.marketplace.msservices.services;

import com.defaults.marketplace.msservices.models.entities.Location;
import com.defaults.marketplace.msservices.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository repository;

    public Location saveLocation(Location location){
        return repository.save(location);
    }

    public List<Location> getLocationsByServiceId(Integer serviceId){
        return repository.findAllByServiceId(serviceId);
    }

    public Location getLocationById(Integer id){
        return repository.findLocationById(id);
    }

    public Location updateLocation(Location newLocation, Location existingLocation){
        existingLocation.setName(newLocation.getName());
        existingLocation.setCapital(newLocation.getCapital());
        existingLocation.setCurrencies(newLocation.getCurrencies());
        existingLocation.setLanguages(newLocation.getLanguages());
        existingLocation.setRegion(newLocation.getRegion());
        existingLocation.setPopulation(newLocation.getPopulation());
        existingLocation.setFlag(newLocation.getFlag());
        existingLocation.setServiceId(newLocation.getServiceId());

        return repository.save(existingLocation);
    }

    public Boolean deleteLocation(Integer id, Integer serviceId){
        List<Location> locations = getLocationsByServiceId(serviceId);
        for (Location l: locations){
            if (l.getId().equals(id)){
                repository.deleteById(id);
                return true;
            }
        }
        return false;
    }

}
