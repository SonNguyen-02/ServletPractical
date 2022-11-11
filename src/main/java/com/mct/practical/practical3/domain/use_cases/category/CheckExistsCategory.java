package com.mct.practical.practical3.domain.use_cases.category;

import com.mct.practical.practical3.domain.model.Category;
import com.mct.practical.practical3.domain.repository.CategoryRepository;
import org.jetbrains.annotations.NotNull;

public class CheckExistsCategory {

    private final CategoryRepository categoryRepository;

    public CheckExistsCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public boolean invoke(long id, String name) {
        return categoryRepository.isExists(id, name);
    }

    public boolean invoke(@NotNull Category category) {
        return invoke(category.getId(), category.getName());
    }

}
