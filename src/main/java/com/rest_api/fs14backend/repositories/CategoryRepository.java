package com.rest_api.fs14backend.repositories;

import com.rest_api.fs14backend.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
