package com.rest_api.fs14backend.services;

import com.rest_api.fs14backend.model.CategoryDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    List<CategoryDTO> listCategory();

    Optional<CategoryDTO> getCategoryById(UUID id);

    CategoryDTO saveNewCategory(CategoryDTO category);

    Optional<CategoryDTO> updateCategoryById(UUID categoryId, CategoryDTO category);

    Boolean deleteById(UUID categoryId);
}
