package com.rest_api.fs14backend.repositories;

import com.rest_api.fs14backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
