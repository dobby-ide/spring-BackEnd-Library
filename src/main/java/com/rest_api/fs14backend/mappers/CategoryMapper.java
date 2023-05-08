package com.rest_api.fs14backend.mappers;

import com.rest_api.fs14backend.entities.Category;
import com.rest_api.fs14backend.model.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {

    Category categoryDtoToCategory(CategoryDTO dto);

    CategoryDTO categoryToCategoryDto(Category category);
}
