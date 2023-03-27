package com.defaults.marketplace.msusers.controllers;

import com.defaults.marketplace.msusers.exceptions.AlreadyExistException;
import com.defaults.marketplace.msusers.exceptions.NotFoundException;
import com.defaults.marketplace.msusers.models.User;
import com.defaults.marketplace.msusers.models.UserDTO;
import com.defaults.marketplace.msusers.models.UserRole;
import com.defaults.marketplace.msusers.requests.RegisterRequest;
import com.defaults.marketplace.msusers.responses.AuthenticationResponse;
import com.defaults.marketplace.msusers.services.AuthenticationService;
import com.defaults.marketplace.msusers.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
        if (userService.getUsers().isEmpty()){
            throw new NotFoundException("No users registered.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.getUsers());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id){
        if (userService.getUserById(id) == null){
            throw new NotFoundException("User with id " + id + " doesn't exist.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.getUserById(id));
    }

    @GetMapping(params = "role")
    public ResponseEntity<List<UserDTO>> getUserByRole(@RequestParam String role){
        role = role.toUpperCase();
        if (!Objects.equals(role, "CLIENT") && !Objects.equals(role, "PROVIDER")){
            throw new NotFoundException("Role " + role + " doesn't exist.");
        }
        UserRole roleEnum = UserRole.valueOf(role);
        if(userService.getUserByRole(roleEnum).isEmpty()){
            throw new NotFoundException("Any user with role " + roleEnum + " is registered.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.getUserByRole(roleEnum));
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> saveUser(@Valid @RequestBody RegisterRequest request) {
        try{
            return ResponseEntity.ok(authenticationService.register(request)); // Register with authentication
        } catch (DataAccessException sql){
            throw new AlreadyExistException("User with email " + request.getEmail() + " already exists.");
        }
        //return ResponseEntity.status(HttpStatus.CREATED).body(service.saveUser(user));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Integer id){
        if (userService.getUserById(id) == null){
            throw new NotFoundException("User with id " + id + " doesn't exist.");
        }
        user.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        if (userService.getUserById(id) == null){
            throw new NotFoundException("User with id " + id + " doesn't exist.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }

    @GetMapping(value = "/validateToken/{email}")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token, @PathVariable String email){
        String jwtToken = token.substring(7); //Remove "Bearer " from token
        if (authenticationService.validateToken(jwtToken, email)){
            return ResponseEntity.status(HttpStatus.OK).body("Token is valid.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid.");
    }
}