package com.epam.preprod.karavayev.db.dao.mysql;

import com.epam.preprod.karavayev.db.ConnectionManager;
import com.epam.preprod.karavayev.db.dao.OrderDao;
import com.epam.preprod.karavayev.dto.ProductItem;
import com.epam.preprod.karavayev.entity.Order;
import com.epam.preprod.karavayev.exception.DbException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class MysqlOrderDaoImpl implements OrderDao {

    private static final Logger LOGGER = Logger.getLogger(MysqlOrderDaoImpl.class);
    private static final String CREATE_ORDER = "INSERT INTO `order` (user_id, address, credit_card) VALUES (?,?,?)";
    private static final String CREATE_PRODUCT_ITEM = "INSERT INTO item_list VALUES (?,?,?,?)";

    private ConnectionManager connectionManager;

    public MysqlOrderDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public boolean create(Order order) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        LOGGER.debug(order);
        try {
            preparedStatement = connection.prepareStatement(CREATE_ORDER, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getUser().getId());
            preparedStatement.setString(2, order.getAddress());
            preparedStatement.setString(3, order.getCreditCard());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.first()) {
                order.setId(resultSet.getInt(1));
            }
            LOGGER.debug(order);
            preparedStatement = connection.prepareStatement(CREATE_PRODUCT_ITEM);
            for (ProductItem item : order.getProductItems()) {
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, item.getProduct().getId());
                preparedStatement.setInt(3, item.getCountItems());
                preparedStatement.setDouble(4, item.getPrice());
                preparedStatement.addBatch();
            }
            return preparedStatement.executeBatch().length > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DbException("Cannot create order -> " + order, e);
        } finally {
            close(preparedStatement);
        }
    }

    private void close(AutoCloseable autoCloseable) {
        if (Objects.nonNull(autoCloseable)) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
    }
}
