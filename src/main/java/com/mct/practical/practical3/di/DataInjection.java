package com.mct.practical.practical3.di;

import com.mct.practical.practical3.data.repositoryimpl.CategoryRepositoryImpl;
import com.mct.practical.practical3.data.repositoryimpl.ProductRepositoryImpl;
import com.mct.practical.practical3.data.server.dao.CategoryDao;
import com.mct.practical.practical3.data.server.dao.ProductDao;
import com.mct.practical.practical3.data.server.database.ShopDatabase;
import com.mct.practical.practical3.domain.repository.CategoryRepository;
import com.mct.practical.practical3.domain.repository.ProductRepository;
import com.mct.practical.practical3.domain.use_cases.CategoryUseCases;
import com.mct.practical.practical3.domain.use_cases.ProductUseCases;
import com.mct.practical.practical3.domain.use_cases.category.*;
import com.mct.practical.practical3.domain.use_cases.product.*;
import org.jetbrains.annotations.NotNull;

public class DataInjection {

    private static final Object lock = new Object();
    private static volatile CategoryUseCases categoryUseCases;
    private static volatile ProductUseCases productUseCases;

    @NotNull
    public static CategoryUseCases provideCategoryUseCases() {
        if (categoryUseCases == null) {
            synchronized (lock) {
                if (categoryUseCases == null) {
                    CategoryDao categoryDao = ShopDatabase.getInstance().categoryDao();
                    CategoryRepository repository = new CategoryRepositoryImpl(categoryDao);
                    categoryUseCases = new CategoryUseCases(
                            new CreateCategory(repository),
                            new UpdateCategory(repository),
                            new RemoveCategory(repository),
                            new GetCategories(repository),
                            new GetCategory(repository),
                            new CheckExistsCategory(repository));
                }
            }
        }
        return categoryUseCases;
    }

    @NotNull
    public static ProductUseCases provideProductUseCases() {
        if (productUseCases == null) {
            synchronized (lock) {
                if (productUseCases == null) {
                    ProductDao productDao = ShopDatabase.getInstance().productDao();
                    ProductRepository repository = new ProductRepositoryImpl(productDao);
                    productUseCases = new ProductUseCases(
                            new CreateProduct(repository),
                            new UpdateProduct(repository),
                            new RemoveProduct(repository),
                            new GetProducts(repository),
                            new GetProduct(repository));
                }
            }
        }
        return productUseCases;
    }

    private DataInjection() {
        throw new RuntimeException("This class can't instance");
    }
}
