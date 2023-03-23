package com.defaults.marketplace.service.controller;

import com.defaults.marketplace.service.model.Provider;
import com.defaults.marketplace.service.model.SocialMedia;
import com.defaults.marketplace.service.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "providers")
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
        log.info("Finding the provider with id " + id);
        return ResponseEntity.status(HttpStatus.OK).body(service.findProviderById(id));
    }

    @PostMapping
    public ResponseEntity<Boolean> saveProvider(@RequestBody Provider provider) {
        log.info("Creating new provider");
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveProvider(provider));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Boolean> deleteProvider(@PathVariable String id) {
        log.info("Trying to delete the provider with id " + id);
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteProvider(id));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable String id, @RequestBody List<SocialMedia> socialMedia) {
        log.info("Trying to update the provider with id " + id);
        return ResponseEntity.status(HttpStatus.OK).body(service.updateProvider(id, socialMedia));
    }
}
