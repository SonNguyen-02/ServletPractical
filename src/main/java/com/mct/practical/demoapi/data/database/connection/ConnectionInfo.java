package com.mct.practical.demoapi.data.database.connection;

public class ConnectionInfo {

    public final String hostname;

    public final String username;

    public final String password;


    public final String database;

    public ConnectionInfo(String hostname, String username, String password, String database) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.database = database;
    }

}
