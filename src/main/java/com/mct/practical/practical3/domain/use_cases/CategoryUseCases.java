package com.mct.practical.practical3.domain.use_cases;

import com.mct.practical.practical3.domain.use_cases.category.*;

public class CategoryUseCases {

    private final CreateCategory createCategory;
    private final UpdateCategory updateCategory;
    private final RemoveCategory removeCategory;
    private final GetCategories getCategories;
    private final GetCategory getCategory;
    private final CheckExistsCategory checkExistsCategory;

    public CategoryUseCases(CreateCategory createCategory,
                            UpdateCategory updateCategory,
                            RemoveCategory removeCategory,
                            GetCategories getCategories,
                            GetCategory getCategory,
                            CheckExistsCategory checkExistsCategory) {
        this.createCategory = createCategory;
        this.updateCategory = updateCategory;
        this.removeCategory = removeCategory;
        this.getCategories = getCategories;
        this.getCategory = getCategory;
        this.checkExistsCategory = checkExistsCategory;
    }

    public CreateCategory getCreateCategory() {
        return createCategory;
    }

    public UpdateCategory getUpdateCategory() {
        return updateCategory;
    }

    public RemoveCategory getRemoveCategory() {
        return removeCategory;
    }

    public GetCategories getGetCategories() {
        return getCategories;
    }

    public GetCategory getGetCategory() {
        return getCategory;
    }

    public CheckExistsCategory getCheckExistsCategory() {
        return checkExistsCategory;
    }
}
