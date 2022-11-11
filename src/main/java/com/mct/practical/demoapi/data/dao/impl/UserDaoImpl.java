package com.mct.practical.demoapi.data.dao.impl;

import com.mct.database.query.StatementFactory;
import com.mct.practical.demoapi.model.User;
import com.mct.practical.demoapi.data.dao.UserDao;
import com.mct.practical.demoapi.data.database.connection.DatabaseConnection;
import org.jetbrains.annotations.NotNull;

public class UserDaoImpl extends Dao implements UserDao {

    public UserDaoImpl(DatabaseConnection connection) {
        super(connection);
    }

    @Override
    public long addUser(@NotNull User user) {

        String sql = StatementFactory.createInsertStatement()
                .from("user")
                .set("username", user.getUsername())
                .set("password", user.getPassword())
                .set("fullname", user.getFullname())
                .buildStatement();

        return execInsertAndClose(sql);
    }

    @Override
    public boolean updateUser(@NotNull User user) {

        String sql = StatementFactory.createUpdateStatement()
                .from("user")
                .set("username", user.getUsername())
                .set("password", user.getPassword())
                .set("fullname", user.getFullname())
                .where("id", user.getId())
                .buildStatement();

        return execUpdateAndClose(sql);
    }

    @Override
    public boolean deleteUser(@NotNull User user) {
        return deleteUser(user.getId());
    }

    @Override
    public boolean deleteUser(long id) {

        String sql = StatementFactory.createDeleteStatement()
                .from("user")
                .where("id", id)
                .buildStatement();

        return execUpdateAndClose(sql);
    }
}
