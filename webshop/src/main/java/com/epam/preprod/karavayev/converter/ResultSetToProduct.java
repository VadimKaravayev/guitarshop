package com.epam.preprod.karavayev.converter;

import com.epam.preprod.karavayev.constant.DbColumn;
import com.epam.preprod.karavayev.entity.Category;
import com.epam.preprod.karavayev.entity.Maker;
import com.epam.preprod.karavayev.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToProduct implements Converter<ResultSet, Product> {

    @Override
    public Product convert(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt(DbColumn.PRODUCT_ID));
        product.setName(resultSet.getString(DbColumn.PRODUCT_NAME));
        product.setCategory(getCategory(resultSet));
        product.setDescription(DbColumn.PRODUCT_DESCRIPTION);
        product.setPrice(resultSet.getDouble(DbColumn.PRODUCT_PRICE));
        product.setMaker(getMaker(resultSet));
        return product;
    }

    private Category getCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt(DbColumn.CATEGORY_ID));
        category.setName(resultSet.getString(DbColumn.CATEGORY_NAME));
        return category;
    }

    private Maker getMaker(ResultSet resultSet) throws SQLException {
        Maker maker = new Maker();
        maker.setId(resultSet.getInt(DbColumn.MAKER_ID));
        maker.setName(resultSet.getString(DbColumn.MAKER_NAME));
        return maker;
    }
}
