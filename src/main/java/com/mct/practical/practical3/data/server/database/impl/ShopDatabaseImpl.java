package com.mct.practical.practical3.data.server.database.impl;

import com.mct.practical.practical3.data.server.dao.CategoryDao;
import com.mct.practical.practical3.data.server.dao.ProductDao;
import com.mct.practical.practical3.data.server.dao.impl.CategoryDaoImpl;
import com.mct.practical.practical3.data.server.dao.impl.ProductDaoImpl;
import com.mct.practical.practical3.data.server.database.ShopDatabase;
import com.mct.practical.practical3.data.server.database.connection.ConnectionInfo;

import static com.mct.practical.practical3.utils.Config.*;

public class ShopDatabaseImpl extends ShopDatabase {

    private volatile CategoryDao categoryDao;

    private volatile ProductDao productDao;

    public ShopDatabaseImpl() {
        super(new ConnectionInfo(HOSTNAME, USERNAME, PASSWORD, DATABASE));
    }

    @Override
    public CategoryDao categoryDao() {
        if (categoryDao == null) {
            synchronized (this) {
                if (categoryDao == null) {
                    categoryDao = new CategoryDaoImpl(this);
                }
            }
        }
        return categoryDao;
    }

    @Override
    public ProductDao productDao() {
        if (productDao == null) {
            synchronized (this) {
                if (productDao == null) {
                    productDao = new ProductDaoImpl(this);
                }
            }
        }
        return productDao;
    }

}
