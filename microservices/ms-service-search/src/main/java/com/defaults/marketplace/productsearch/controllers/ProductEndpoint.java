package com.defaults.marketplace.productsearch.controllers;

import com.defaults.marketplace.productsearch.entities.*;
import com.defaults.marketplace.productsearch.models.entities.ServiceC;
import com.defaults.marketplace.productsearch.repository.ServiceRepository;
import com.defaults.marketplace.productsearch.utils.Transform;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Endpoint
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.defaults.com/marketplace/productSearch/entities";


    @Autowired
    private ServiceRepository repository;


    public ProductEndpoint(ServiceRepository serviceRepository){
        this.repository = serviceRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllServicesRequest")
    @ResponsePayload
    public GetServicesListResponse getAllServices(){
        GetServicesListResponse response = new GetServicesListResponse();
        List<ServiceC> found = repository.findAll();
        for (ServiceC service : found) {
            Transform transform = new Transform();

            response.getService().add(transform.transform(service));
        }

        System.out.println(response);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getServiceByIdRequest")
    @ResponsePayload
    public GetServiceByIdResponse getServiceById(@RequestPayload GetServiceByIdRequest request){
        Transform transform = new Transform();

        ServiceC service = repository.findServiceCById(Integer.valueOf(request.getServiceId()));
        GetServiceByIdResponse response = new GetServiceByIdResponse();

        response.setService(transform.transform(service));
        System.out.println(response);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getServicesByFilter")
    @ResponsePayload
    public GetServicesListResponse getServiceByFilter(@RequestPayload GetServicesByFilter request){
        GetServicesListResponse response = new GetServicesListResponse();
        List<ServiceC> found = repository.findAll();
        Transform transform = new Transform();
        System.out.println(request.getFilter());
        List<ServiceC> filtersfound = found.stream().filter(serviceC ->serviceC.getTitle().contains(request.getFilter())).collect(Collectors.toList());
        List<ServiceC> filtersfound2 = found.stream().filter(serviceC ->serviceC.getDescription().contains(request.getFilter())).collect(Collectors.toList());
        filtersfound.addAll(filtersfound2);
        for(ServiceC service: filtersfound){
            response.getService().add(transform.transform(service));
        }
        System.out.println(response);
        return response;
    }
}
