package com.defaults.marketplace.productsearch.utils;

import com.defaults.marketplace.productsearch.entities.ServiceCategory;
import com.defaults.marketplace.productsearch.entities.ServiceMealType;
import com.defaults.marketplace.productsearch.entities.ServiceSOAP;
import com.defaults.marketplace.productsearch.models.entities.ServiceC;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Transform {
    public static ServiceSOAP execute(ServiceC service){
        ServiceSOAP serviceSOAP = new ServiceSOAP();
        serviceSOAP.setVegan(service.getVegan());
        serviceSOAP.setTransportationType(service.getTransportationType());
        serviceSOAP.setServiceId(BigInteger.valueOf(service.getId()));
        serviceSOAP.setBreakfast(service.getBreakfast());
        serviceSOAP.setCapacity(BigInteger.valueOf(service.getCapacity()));
        serviceSOAP.setArrivalTime(toXmlGregorianCalendar(service.getArrivalTime()));
        serviceSOAP.setCheckinTime(toXmlGregorianCalendar(service.getCheckinTime()));
        serviceSOAP.setCheckoutTime(toXmlGregorianCalendar(service.getCheckoutTime()));
        serviceSOAP.setCost(service.getCost());
        serviceSOAP.setCategory(ServiceCategory.fromValue(service.getCategory().toString()));
        serviceSOAP.setDepartureTime(toXmlGregorianCalendar(service.getDepartureTime()));
        serviceSOAP.setDescription(service.getDescription());
        serviceSOAP.setDestination(service.getDestination());
        serviceSOAP.setInternet(service.getInternet());
        serviceSOAP.setLocation(service.getLocation());
        serviceSOAP.setMealType(ServiceMealType.fromValue(service.getMealType().toString()));
        serviceSOAP.setOrigin(service.getOrigin());
        if (service.getServings() != null){
            serviceSOAP.setServings(BigInteger.valueOf(service.getServings()));
        }

        serviceSOAP.setProviderId(BigInteger.valueOf(service.getProviderId()));
        serviceSOAP.setCountryCode(service.getCountryCode());
        serviceSOAP.setTitle(service.getTitle());
        return serviceSOAP;
    }

    private static XMLGregorianCalendar toXmlGregorianCalendar(LocalDate localDate) {
        try {
            GregorianCalendar gregCal = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            return DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
                    gregCal.get(Calendar.YEAR),
                    gregCal.get(Calendar.MONTH) + 1,
                    gregCal.get(Calendar.DAY_OF_MONTH),
                    DatatypeConstants.FIELD_UNDEFINED);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
