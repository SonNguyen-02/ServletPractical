package com.mct.practical.demoapi.data.database.impl;

import com.mct.practical.demoapi.data.dao.UserDao;
import com.mct.practical.demoapi.data.dao.impl.UserDaoImpl;
import com.mct.practical.demoapi.data.database.MyDatabase;
import com.mct.practical.demoapi.data.database.connection.ConnectionInfo;

public class MyDatabaseImpl extends MyDatabase {

    private UserDao userDao;

    public MyDatabaseImpl() {
        super(new ConnectionInfo("localhost", "sonnc", "123456", "shop"));
    }

    @Override
    public UserDao userDao() {
        if(userDao == null){
            userDao = new UserDaoImpl(this);
        }
        return userDao;
    }
}
