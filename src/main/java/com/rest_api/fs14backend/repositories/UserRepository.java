package com.rest_api.fs14backend.repositories;

import com.rest_api.fs14backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findAllByUsernameIsLikeIgnoreCase(String userName);

    List<User> findByEmail(String email);
}
