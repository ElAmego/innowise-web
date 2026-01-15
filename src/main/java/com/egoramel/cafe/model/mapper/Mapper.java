package com.egoramel.cafe.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
    T map(final ResultSet resultSet) throws SQLException;
}