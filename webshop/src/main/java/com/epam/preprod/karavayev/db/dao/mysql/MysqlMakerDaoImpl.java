package com.epam.preprod.karavayev.db.dao.mysql;

import com.epam.preprod.karavayev.converter.ResultSetToMaker;
import com.epam.preprod.karavayev.db.ConnectionManager;
import com.epam.preprod.karavayev.db.dao.MakerDao;
import com.epam.preprod.karavayev.entity.Maker;
import com.epam.preprod.karavayev.exception.DbException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlMakerDaoImpl implements MakerDao {

    private static final Logger LOGGER = Logger.getLogger(MysqlMakerDaoImpl.class);
    private static final String GET_ALL_MAKERS = "SELECT * FROM maker";
    private ConnectionManager connectionManager;
    private ResultSetToMaker converter;

    public MysqlMakerDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        converter = new ResultSetToMaker();
    }

    @Override
    public List<Maker> getAllMakers() {
        List<Maker> makerList = new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_MAKERS);
            while (resultSet.next()) {
                makerList.add(converter.convert(resultSet));
            }
            return makerList;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DbException("Cannot get makers");
        }
    }
}
