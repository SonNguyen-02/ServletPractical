package com.mct.practical.practical3.data.server.database;

import com.mct.practical.practical3.data.server.dao.CategoryDao;
import com.mct.practical.practical3.data.server.dao.ProductDao;
import com.mct.practical.practical3.data.server.database.connection.ConnectionInfo;
import com.mct.practical.practical3.data.server.database.connection.DatabaseConnection;
import com.mct.practical.practical3.data.server.database.impl.ShopDatabaseImpl;
import org.jetbrains.annotations.NotNull;

public abstract class ShopDatabase extends DatabaseConnection {

    private static ShopDatabase instance;

    protected ShopDatabase(@NotNull ConnectionInfo info) {
        super(info);
    }

    public synchronized static ShopDatabase getInstance() {
        if (instance == null) {
            instance = new ShopDatabaseImpl();
        }
        return instance;
    }

    public abstract CategoryDao categoryDao();

    public abstract ProductDao productDao();

}
