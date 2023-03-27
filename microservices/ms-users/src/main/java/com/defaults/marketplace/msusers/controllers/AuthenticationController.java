package com.defaults.marketplace.msusers.controllers;

import com.defaults.marketplace.msusers.requests.AuthenticationRequest;
import com.defaults.marketplace.msusers.responses.AuthenticationResponse;
import com.defaults.marketplace.msusers.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;
    /*
     * @PostMapping(value = "/register")
     * public ResponseEntity<AuthenticationResponse> register(@RequestBody
     * RegisterRequest request){
     * return ResponseEntity.ok(service.register(request));
     * }
     */

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
