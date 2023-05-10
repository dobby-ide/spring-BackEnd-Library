package com.rest_api.fs14backend.services;

import com.rest_api.fs14backend.mappers.CategoryMapper;
import com.rest_api.fs14backend.model.CategoryDTO;
import com.rest_api.fs14backend.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class CategoryServiceJPA implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public List<CategoryDTO> listCategory() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDTO> getCategoryById(UUID id) {

        return Optional.ofNullable(categoryMapper.categoryToCategoryDto(categoryRepository.findById(id).orElse(null)));
    }

    @Override
    public CategoryDTO saveNewCategory(CategoryDTO category) {

        return categoryMapper.categoryToCategoryDto(categoryRepository.save(categoryMapper.categoryDtoToCategory(category)));
    }

    @Override
    public Optional<CategoryDTO> updateCategoryById(UUID categoryId, CategoryDTO category) {
        AtomicReference<Optional<CategoryDTO>> ref = new AtomicReference<>();

        categoryRepository.findById(categoryId).ifPresentOrElse(foundCategory -> {
            foundCategory.setCategoryName(category.getCategoryName());
            ref.set(Optional.of(categoryMapper.categoryToCategoryDto(categoryRepository.save(foundCategory))));
        }, () -> {
            ref.set(Optional.empty());
        });

        return ref.get();
    }

    @Override
    public Boolean deleteById(UUID categoryId) {
        if(categoryRepository.existsById(categoryId)){
            categoryRepository.deleteById(categoryId);
            return true;
        }
        return false;
    }
}
