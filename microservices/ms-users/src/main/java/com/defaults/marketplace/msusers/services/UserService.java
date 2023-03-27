package com.defaults.marketplace.msusers.services;

import com.defaults.marketplace.msusers.models.User;
import com.defaults.marketplace.msusers.models.UserDTO;
import com.defaults.marketplace.msusers.models.UserRole;
import com.defaults.marketplace.msusers.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> getUsers(){
        return repository.findAllUsersDTO();
    }
    public UserDTO getUserById(Integer id){
        return repository.findUserDTOById(id);
    }

    public List<UserDTO> getUserByRole(UserRole role){
        return repository.findUserDTOByRole(role);
    }

    /*public User saveUser(User user){
        return repository.save(user);
    }*/ // Using authentication service

    public User updateUser(User user){
        User existingUser = repository.findUserById(user.getId());
        if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
        if (user.getPassword() != null) existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getFirstName() != null) existingUser.setFirstName(user.getFirstName());
        if (user.getLastName() != null) existingUser.setLastName(user.getLastName());
        if (user.getBirthday() != null) existingUser.setBirthday(user.getBirthday());
        if (user.getPictureUrl() != null) existingUser.setPictureUrl(user.getPictureUrl());
        if (user.getDescription() != null) existingUser.setDescription(user.getDescription());
        if (user.getRole() != null) existingUser.setRole(user.getRole());

        return repository.save(existingUser);
    }

    public String deleteUser(Integer id){
        repository.deleteById(id);
        return "User deleted";
    }
}
