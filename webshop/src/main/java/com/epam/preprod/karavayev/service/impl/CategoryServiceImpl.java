package com.epam.preprod.karavayev.service.impl;

import com.epam.preprod.karavayev.db.TransactionManager;
import com.epam.preprod.karavayev.db.dao.CategoryDao;
import com.epam.preprod.karavayev.entity.Category;
import com.epam.preprod.karavayev.service.CategoryService;

import java.sql.Connection;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;
    private TransactionManager transactionManager;

    public CategoryServiceImpl(CategoryDao categoryDao, TransactionManager transactionManager) {
        this.categoryDao = categoryDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<Category> getAllCategories() {
        return transactionManager.manageTransaction(() -> categoryDao.getAllCategories(), Connection.TRANSACTION_READ_COMMITTED);
    }
}
