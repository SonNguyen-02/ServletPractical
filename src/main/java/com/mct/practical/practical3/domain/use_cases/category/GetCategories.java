package com.mct.practical.practical3.domain.use_cases.category;

import com.mct.practical.practical3.domain.model.Category;
import com.mct.practical.practical3.domain.repository.CategoryRepository;

import java.util.List;

public class GetCategories {

    private final CategoryRepository categoryRepository;

    public GetCategories(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public long count(String search) {
        return categoryRepository.getTotalCategoryBySearch(search);
    }

    public List<Category> invoke(String search, int limit, int offset) {
        return categoryRepository.getCategories(search, limit, offset);
    }

}
