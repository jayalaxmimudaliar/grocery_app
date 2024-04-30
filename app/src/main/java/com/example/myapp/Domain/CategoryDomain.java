package com.example.myapp.Domain;

public class CategoryDomain {
    String imgPath;
    String Title;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public CategoryDomain(String imgPath, String title) {
        this.imgPath = imgPath;
        Title = title;


    }
}
