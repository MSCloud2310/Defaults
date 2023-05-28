package com.defaults.marketplace.productsearch.utils;

import com.defaults.marketplace.productsearch.entities.ServiceSOAP;
import com.defaults.marketplace.productsearch.models.entities.ServiceC;
import jakarta.persistence.PersistenceContext;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigInteger;

public class Transform {

    public ServiceSOAP transform(ServiceC service){
        ServiceSOAP serviceSOAP = new ServiceSOAP();
        serviceSOAP.setVegan(service.getVegan());
        serviceSOAP.setTransportationType(service.getTransportationType());
        serviceSOAP.setServiceId(BigInteger.valueOf(service.getId()));
        serviceSOAP.setBreakfast(service.getBreakfast());
        serviceSOAP.setCapacity(BigInteger.valueOf(service.getCapacity()));
        try {
            serviceSOAP.setArrivalTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(service.getArrivalTime().toString()));
            serviceSOAP.setCheckinTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(service.getCheckinTime().toString()));
            serviceSOAP.setCheckoutTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(service.getCheckoutTime().toString()));
            serviceSOAP.setDepartureTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(service.getDepartureTime().toString()));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        serviceSOAP.setCost(service.getCost());
        serviceSOAP.setCategory(service.getCategory().ordinal());
        serviceSOAP.setDescription(service.getDescription());
        serviceSOAP.setDestination(service.getDestination());
        serviceSOAP.setInternet(service.getInternet());
        serviceSOAP.setLocation(service.getLocation());
        serviceSOAP.setMealType(BigInteger.valueOf(service.getMealType().ordinal()));
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
