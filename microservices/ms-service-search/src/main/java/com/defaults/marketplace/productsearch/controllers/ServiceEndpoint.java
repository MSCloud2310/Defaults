package com.defaults.marketplace.productsearch.controllers;

import com.defaults.marketplace.productsearch.entities.*;
import com.defaults.marketplace.productsearch.services.ServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Slf4j
@Endpoint
public class ServiceEndpoint {
    private static final String NAMESPACE_URI = "http://www.defaults.com/marketplace/productSearch/entities";

    @Autowired
    private ServiceService service;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "searchServicesRequest")
    @ResponsePayload
    public SearchServicesResponse searchServices(@RequestPayload SearchServicesRequest request) {
        return service.findAllServicesByFilter(request.getFilter());
    }
}
