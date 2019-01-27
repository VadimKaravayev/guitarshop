package com.epam.preprod.karavayev.converter;

import com.epam.preprod.karavayev.constant.DbColumn;
import com.epam.preprod.karavayev.entity.Maker;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToMaker implements Converter<ResultSet, Maker> {

    @Override
    public Maker convert(ResultSet resultSet) throws SQLException {
        Maker maker = new Maker();
        maker.setId(resultSet.getInt(DbColumn.MAKER_ID));
        maker.setName(resultSet.getString(DbColumn.MAKER_NAME));
        return maker;
    }

}
