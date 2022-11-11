package com.mct.practical.demoapi.data;

import com.mct.practical.demoapi.data.dao.UserDao;
import com.mct.practical.demoapi.model.User;
import com.mct.practical.demoapi.repo.UserRepo;

public class UserRepoImpl implements UserRepo {

    private final UserDao userDao;

    public UserRepoImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public long addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public boolean deleteUser(User user) {
        return userDao.deleteUser(user);
    }

    @Override
    public boolean deleteUser(long id) {
        return userDao.deleteUser(id);
    }
}
