package com.epam.preprod.karavayev.db.dao.mysql;

import com.epam.preprod.karavayev.converter.ProductFilterToCountQuery;
import com.epam.preprod.karavayev.converter.ProductFilterToListQuery;
import com.epam.preprod.karavayev.converter.ResultSetToProduct;
import com.epam.preprod.karavayev.db.ConnectionManager;
import com.epam.preprod.karavayev.db.dao.ProductDao;
import com.epam.preprod.karavayev.dto.ProductFilter;
import com.epam.preprod.karavayev.entity.Product;
import com.epam.preprod.karavayev.exception.DbException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MysqlProductDaoImpl implements ProductDao {

    private static final Logger LOGGER = Logger.getLogger(MysqlProductDaoImpl.class);
    private static final String GET_PRODUCT_BY_ID = "SELECT product.*, maker.name as maker_name, category.name as category_name FROM product INNER JOIN maker on product.maker_id = maker.id INNER JOIN category on product.category_id = category.id WHERE product.id = ? LIMIT 1";

    private ConnectionManager connectionManager;
    private ResultSetToProduct converter;
    private ProductFilterToCountQuery toCountQuery;
    private ProductFilterToListQuery toListQuery;

    public MysqlProductDaoImpl(ConnectionManager connectionManager) {
        toCountQuery = new ProductFilterToCountQuery();
        toListQuery = new ProductFilterToListQuery();
        this.connectionManager = connectionManager;
        converter = new ResultSetToProduct();
    }

    @Override
    public List<Product> getProducts(ProductFilter filter) {
        Connection connection = connectionManager.getConnection();
        List<Product> products = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(toListQuery.convert(filter))) {
            if (Objects.nonNull(filter.getName())) {
                preparedStatement.setString(1, "%" + filter.getName() + "%");
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(converter.convert(resultSet));
            }
            return products;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DbException("Cannot get product list by filter -> " + filter, e);
        }
    }

    @Override
    public int getCountProducts(ProductFilter filter) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(toCountQuery.convert(filter))) {
            if (Objects.nonNull(filter.getName())) {
                preparedStatement.setString(1, "%" + filter.getName() + "%");
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.first() ? resultSet.getInt(1) : 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DbException("Cannot get count products by filter -> " + filter, e);
        }
    }

    @Override
    public Product getProduct(int id) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.first() ? converter.convert(resultSet) : null;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DbException("Cannot get product by id -> " + id, e);
        }
    }
}
