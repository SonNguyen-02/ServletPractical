package com.mct.practical.demoapi.data.database;

import com.mct.practical.demoapi.data.dao.UserDao;
import com.mct.practical.demoapi.data.database.connection.ConnectionInfo;
import com.mct.practical.demoapi.data.database.connection.DatabaseConnection;

import com.mct.practical.demoapi.data.database.impl.MyDatabaseImpl;
import org.jetbrains.annotations.NotNull;

public abstract class MyDatabase extends DatabaseConnection {

    private static MyDatabase instance;

    protected MyDatabase(@NotNull ConnectionInfo info) {
        super(info);
    }

    public synchronized static MyDatabase getInstance() {
        if (instance == null) {
            instance = new MyDatabaseImpl();
        }
        return instance;
    }

    public abstract UserDao userDao();

}
