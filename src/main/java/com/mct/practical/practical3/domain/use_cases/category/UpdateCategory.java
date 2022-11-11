package com.mct.practical.practical3.domain.use_cases.category;

import com.mct.practical.practical3.domain.model.Category;
import com.mct.practical.practical3.domain.repository.CategoryRepository;

public class UpdateCategory {

    private final CategoryRepository categoryRepository;

    public UpdateCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public boolean invoke(Category category) {
        return categoryRepository.updateCategory(category);
    }
}
