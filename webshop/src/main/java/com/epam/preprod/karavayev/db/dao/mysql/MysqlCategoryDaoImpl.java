package com.epam.preprod.karavayev.db.dao.mysql;

import com.epam.preprod.karavayev.converter.ResultSetToCategory;
import com.epam.preprod.karavayev.db.ConnectionManager;
import com.epam.preprod.karavayev.db.dao.CategoryDao;
import com.epam.preprod.karavayev.entity.Category;
import com.epam.preprod.karavayev.exception.DbException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlCategoryDaoImpl implements CategoryDao {
    private static final Logger LOGGER = Logger.getLogger(MysqlCategoryDaoImpl.class);
    private static final String GET_ALL_CATEGORIES = "SELECT * FROM category";
    private ConnectionManager connectionManager;
    private ResultSetToCategory converter;

    public MysqlCategoryDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        converter = new ResultSetToCategory();

    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_CATEGORIES);
            while (resultSet.next()) {
                categoryList.add(converter.convert(resultSet));
            }
            return categoryList;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DbException("Cannot get categories");
        }
    }
}
