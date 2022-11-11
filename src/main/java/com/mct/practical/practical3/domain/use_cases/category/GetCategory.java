package com.mct.practical.practical3.domain.use_cases.category;

import com.mct.practical.practical3.domain.model.Category;
import com.mct.practical.practical3.domain.repository.CategoryRepository;

public class GetCategory {

    private final CategoryRepository categoryRepository;

    public GetCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category invoke(long id) {
        return categoryRepository.getCategory(id);
    }

}
