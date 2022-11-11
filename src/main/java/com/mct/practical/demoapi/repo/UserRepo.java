package com.mct.practical.demoapi.repo;

import com.mct.practical.demoapi.model.User;

public interface UserRepo {

    long addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(User user);

    boolean deleteUser(long id);

}
