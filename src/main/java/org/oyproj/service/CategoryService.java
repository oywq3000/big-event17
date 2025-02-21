package org.oyproj.service;

import org.oyproj.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    //list all category for user
    List<Category> list();

    Category findById(Integer id);

    void update(Category category);
}
