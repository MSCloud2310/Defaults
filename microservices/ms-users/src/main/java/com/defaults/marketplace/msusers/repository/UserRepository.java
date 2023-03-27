package com.defaults.marketplace.msusers.repository;

import com.defaults.marketplace.msusers.models.User;
import com.defaults.marketplace.msusers.models.UserDTO;
import com.defaults.marketplace.msusers.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Integer> {
    @Query("SELECT new com.defaults.marketplace.msusers.models.UserDTO(user.id, user.email, user.firstName, user.lastName, user.birthday, user.pictureUrl, user.description, user.role) FROM User user")
    List<UserDTO> findAllUsersDTO();
    @Query("SELECT new com.defaults.marketplace.msusers.models.UserDTO(user.id, user.email, user.firstName, user.lastName, user.birthday, user.pictureUrl, user.description, user.role) FROM User user WHERE user.id = ?1")
    UserDTO findUserDTOById(Integer id);
    @Query("SELECT new com.defaults.marketplace.msusers.models.UserDTO(user.id, user.email, user.firstName, user.lastName, user.birthday, user.pictureUrl, user.description, user.role) FROM User user WHERE user.role = ?1")
    List<UserDTO> findUserDTOByRole(UserRole role);

    User findUserById(Integer id);
    Optional<User> findByEmail(String email);
}
