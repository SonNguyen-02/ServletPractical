package com.mct.practical.practical3.domain.model;

import javax.validation.constraints.NotBlank;

public class Category {

    private long id;
    @NotBlank(message = "Name is required!")
    private String name;
    @NotBlank(message = "Description is required!")
    private String desc;

    public Category() {
        id = -1;
    }

    public Category(long id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
