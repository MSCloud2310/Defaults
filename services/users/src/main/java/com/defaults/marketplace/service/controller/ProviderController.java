package com.defaults.marketplace.service.controller;

import com.defaults.marketplace.service.model.Provider;
import com.defaults.marketplace.service.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "users")
public class ProviderController {
    @Autowired
    ProviderService service;

    @GetMapping
    public ResponseEntity<List<Provider>> findAllProviders() {
        log.info("Retrieving all the providers");
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllProviders());
    }

    @GetMapping("{id}")
    public ResponseEntity<Provider> findProviderById(@PathVariable String id) {
        log.info("Finding user with id " + id);
        return ResponseEntity.status(HttpStatus.OK).body(service.findProviderById(id));
    }

    @PostMapping
    public ResponseEntity<Boolean> saveProvider(@RequestBody Provider provider) {
        log.info("Creating new user");
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveProvider(provider));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Boolean> deleteProvider(@PathVariable String id) {
        log.info("Trying to delete user with id " + id);
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteProvider(id));
    }
}
