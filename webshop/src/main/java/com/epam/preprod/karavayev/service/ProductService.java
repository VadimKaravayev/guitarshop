package com.epam.preprod.karavayev.service;

import com.epam.preprod.karavayev.dto.ProductFilter;
import com.epam.preprod.karavayev.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductFilter filter);

    int getCountProducts(ProductFilter filter);

    Product getProduct(int id);

}
