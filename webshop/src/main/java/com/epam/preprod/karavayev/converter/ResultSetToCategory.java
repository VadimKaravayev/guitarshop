package com.epam.preprod.karavayev.converter;

import com.epam.preprod.karavayev.constant.DbColumn;
import com.epam.preprod.karavayev.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToCategory implements Converter<ResultSet, Category> {

    @Override
    public Category convert(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt(DbColumn.CATEGORY_ID));
        category.setName(resultSet.getString(DbColumn.CATEGORY_NAME));
        return category;
    }
}
