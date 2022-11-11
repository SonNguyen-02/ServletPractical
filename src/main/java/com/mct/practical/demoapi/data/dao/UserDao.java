package com.mct.practical.demoapi.data.dao;

import com.mct.practical.demoapi.model.User;

public interface UserDao {

    long addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(User user);

    boolean deleteUser(long id);

}
