package com.egoramel.webproject.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
    T map(final ResultSet resultSet) throws SQLException;
}