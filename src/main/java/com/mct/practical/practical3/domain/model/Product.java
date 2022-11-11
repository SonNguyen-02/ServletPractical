package com.mct.practical.practical3.domain.model;

import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class Product {

    private long id;
    private long categoryId;
    @NotBlank(message = "Code can be empty!")
    @Pattern(regexp = "[A-Z]{2}[0-9]{4}", message = "Code format invalid! (ex: AA1234)")
    private String code;
    @NotBlank(message = "Name can be empty!")
    @Pattern(regexp = "[a-zA-Z0-9\\s]{1,255}", message = "The name contains only characters, number and spaces!")
    private String name;
    @NotBlank(message = "Description can be empty!")
    private String desc;
    @NotNull(message = "Price invalid!")
    @Min(value = 0, message = "Price must be positive!")
    private Float price;
    private String image;

    private List<Gallery> galleries;

    @Nullable
    private String categoryName;

    public Product() {
        id = -1;
    }

    public Product(long id, long categoryId, String code, String name, String desc, Float price, String image) {
        this.id = id;
        this.categoryId = categoryId;
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Gallery> getGalleries() {
        return galleries;
    }

    public void setGalleries(List<Gallery> galleries) {
        this.galleries = galleries;
    }

    public void setGalleries(String galleries) {

    }

    @Nullable
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(@Nullable String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}' + '\n';
    }
}
