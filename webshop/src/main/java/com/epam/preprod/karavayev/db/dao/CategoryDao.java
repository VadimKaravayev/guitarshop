package com.epam.preprod.karavayev.db.dao;

import com.epam.preprod.karavayev.entity.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getAllCategories();
}
