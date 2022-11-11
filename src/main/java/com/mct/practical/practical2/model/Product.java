package com.mct.practical.practical2.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Product {
    @NotBlank(message = "Code can be empty!")
    @Pattern(regexp = "[A-Z]{2}[0-9]{4}", message = "Code format invalid! (ex: AA1234)")
    private String code;

    @NotBlank(message = "Name can be empty!")
    @Pattern(regexp = "[a-zA-Z0-9\\s]{1,255}", message = "The name contains only characters, number and spaces!")
    private String name;

    @NotNull(message = "Price invalid!")
    @Min(value = 0, message = "Price must be positive!")
    private Float price;

    @NotBlank(message = "Description can be empty!")
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
