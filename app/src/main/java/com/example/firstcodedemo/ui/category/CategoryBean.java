package com.example.firstcodedemo.ui.category;

import android.support.annotation.NonNull;

import java.util.List;

public class CategoryBean {

    public String type;

    public List<Category> categoryList;

    public static class Category {
        public String img;
        public String title;

        @NonNull
        @Override
        public String toString() {
            return "(img==="+img+"+++++ title="+title+")";
        }

    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<categoryList.size();i++){
            Category category = categoryList.get(i);
            String s=category.toString();
            sb.append(s);
        }
        return "分类==="+type+"list==="+sb;
    }
}
