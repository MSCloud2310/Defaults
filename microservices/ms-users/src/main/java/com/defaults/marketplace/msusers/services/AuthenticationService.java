package com.defaults.marketplace.msusers.services;

import com.defaults.marketplace.msusers.models.User;
import com.defaults.marketplace.msusers.repository.UserRepository;
import com.defaults.marketplace.msusers.requests.AuthenticationRequest;
import com.defaults.marketplace.msusers.requests.RegisterRequest;
import com.defaults.marketplace.msusers.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .birthday(request.getBirthday())
                .description(request.getDescription())
                .pictureUrl(request.getPictureUrl())
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .message("User registered successfully")
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .message("User authenticated successfully")
                .token(jwtToken)
                .build();
    }
    // Method to validate token using JwtService
    public boolean validateToken(String token, String email) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
        return jwtService.isTokenValid(token, userDetails);
    }
}
