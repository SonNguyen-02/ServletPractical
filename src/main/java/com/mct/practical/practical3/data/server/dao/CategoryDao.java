package com.mct.practical.practical3.data.server.dao;

import com.mct.practical.practical3.domain.model.Category;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface CategoryDao extends BaseDao<Category> {

    long getTotalCategoryBySearch(String search);

    List<Category> getCategories(String search, int limit, int offset);

    Category getCategory(long id);

    boolean isExists(long id, String search);
}
