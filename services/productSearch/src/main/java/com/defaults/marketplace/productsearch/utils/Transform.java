package com.defaults.marketplace.productsearch.utils;

import com.defaults.marketplace.productsearch.entities.ServiceSOAP;
import com.defaults.marketplace.productsearch.models.entities.ServiceC;
import jakarta.persistence.PersistenceContext;

import java.math.BigInteger;

public class Transform {

    public ServiceSOAP transform(ServiceC service){

        ServiceSOAP serviceSOAP = new ServiceSOAP();
        serviceSOAP.setVegan(service.getVegan());
        serviceSOAP.setTransportationType(service.getTransportationType());
        serviceSOAP.setServiceId(BigInteger.valueOf(service.getId()));
        serviceSOAP.setBreakfast(service.getBreakfast());
        serviceSOAP.setCapacity(BigInteger.valueOf(service.getCapacity()));
        serviceSOAP.setArrivalTime(service.getArrivalTime());
        serviceSOAP.setCheckinTime(service.getCheckinTime());
        serviceSOAP.setCheckoutTime(service.getCheckoutTime());
        serviceSOAP.setCost(service.getCost());
        serviceSOAP.setCategory(service.getCategory());
        serviceSOAP.setDepartureTime(service.getDepartureTime());
        serviceSOAP.setDescription(service.getDescription());
        serviceSOAP.setDestination(service.getDestination());
        serviceSOAP.setInternet(service.getInternet());
        serviceSOAP.setLocation(service.getLocation());
        serviceSOAP.setMealType(service.getMealType());
        serviceSOAP.setOrigin(service.getOrigin());
        if (service.getServings() != null){
            serviceSOAP.setServings(BigInteger.valueOf(service.getServings()));
        }

        serviceSOAP.setProviderId(BigInteger.valueOf(service.getProviderId()));
        serviceSOAP.setCountryCode(service.getCountryCode());
        serviceSOAP.setTitle(service.getTitle());
        return serviceSOAP;
    }

}
