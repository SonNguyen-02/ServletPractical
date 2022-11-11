package com.mct.practical.demoapi.di;

import com.mct.practical.demoapi.data.UserRepoImpl;
import com.mct.practical.demoapi.data.database.MyDatabase;
import com.mct.practical.demoapi.repo.UserRepo;

public class DataInjection {

    private static final Object lock = new Object();

    private static volatile UserRepo userRepo;

    public static UserRepo provideUserRepo() {
        if (userRepo == null) {
            synchronized (lock) {
                if (userRepo == null) {
                    MyDatabase database = MyDatabase.getInstance();
                    userRepo = new UserRepoImpl(database.userDao());
                }
            }
        }
        return userRepo;
    }

}
