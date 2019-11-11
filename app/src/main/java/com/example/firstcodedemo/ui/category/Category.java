package com.example.firstcodedemo.ui.category;

import android.support.annotation.NonNull;

public class Category {

    public String img;
    public String title;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    @Override
    public String toString() {
        return "(img==="+img+"+++++ title="+title+")";
    }

}
