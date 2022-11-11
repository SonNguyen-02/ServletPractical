package com.mct.practical.practical3.domain.model;

import org.intellij.lang.annotations.RegExp;

public class Gallery {

    // the folder save follow '/root/yyyy/mm/name.ext'
    public static final int TYPE_MONTH = 0;
    // the folder save follow '/root/yyyy/mm/dd/name.ext'
    public static final int TYPE_DATE = 1;

    @RegExp
    private static final String DATE_REG = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])";

    private int type;
    private String name;
    private String date; // yyyy-mm-dd

    public Gallery() {
    }

    public Gallery(int type, String name, String date) {
        this.type = type;
        this.name = name;
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPath(String splitPath) {
        if (type != TYPE_MONTH && type != TYPE_DATE) {
            throw new IllegalArgumentException("You need set type before call this function!");
        }
        if (date == null) {
            throw new IllegalArgumentException("You need set date before call this function!");
        }
        if (!date.matches(DATE_REG)) {
            throw new IllegalArgumentException("Date string invalid!");
        }
        String[] arr = date.split("-");
        String path = arr[0] + splitPath + arr[1];
        if (type == TYPE_DATE) {
            path += splitPath + arr[2];
        }
        return path;
    }

    @Override
    public String toString() {
        return "Gallery{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
