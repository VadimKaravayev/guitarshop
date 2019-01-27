package com.epam.preprod.karavayev.service.impl;

import com.epam.preprod.karavayev.db.TransactionManager;
import com.epam.preprod.karavayev.db.dao.ProductDao;
import com.epam.preprod.karavayev.dto.ProductFilter;
import com.epam.preprod.karavayev.entity.Product;
import com.epam.preprod.karavayev.service.ProductService;

import java.sql.Connection;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    private TransactionManager transactionManager;

    public ProductServiceImpl(ProductDao productDao, TransactionManager transactionManager) {
        this.productDao = productDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<Product> getProducts(ProductFilter filter) {
        return transactionManager.manageTransaction(() -> productDao.getProducts(filter), Connection.TRANSACTION_READ_COMMITTED);
    }

    @Override
    public int getCountProducts(ProductFilter filter) {
        return transactionManager.manageTransaction(() -> productDao.getCountProducts(filter), Connection.TRANSACTION_READ_COMMITTED);
    }

    @Override
    public Product getProduct(int id) {
        return transactionManager.manageTransaction(() -> productDao.getProduct(id), Connection.TRANSACTION_READ_COMMITTED);
    }
}
