package com.mct.practical.practical3.domain.use_cases.category;

import com.mct.practical.practical3.domain.model.Category;
import com.mct.practical.practical3.domain.repository.CategoryRepository;

public class CreateCategory {

    private final CategoryRepository categoryRepository;

    public CreateCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Long invoke(Category category) {
        return categoryRepository.createCategory(category);
    }
}
