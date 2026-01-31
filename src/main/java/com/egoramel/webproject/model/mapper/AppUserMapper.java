package com.egoramel.webproject.model.mapper;

import com.egoramel.webproject.model.entity.AppUser;
import com.egoramel.webproject.model.entity.Role;
import com.egoramel.webproject.model.entity.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class AppUserMapper implements Mapper<AppUser> {
    private static final String USER_ID = "user_id";
    private static final String LOGIN = "login";
    private static final String USER_PASSWORD = "user_password";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String LOYALTY_POINTS = "loyalty_points";
    private static final String ROLE = "user_role";
    private static final String STATUS = "user_status";

    @Override
    public AppUser map(final ResultSet resultSet) throws SQLException {
        return AppUser.builder()
                .userId(resultSet.getLong(USER_ID))
                .login(resultSet.getString(LOGIN))
                .userPassword(resultSet.getString(USER_PASSWORD))
                .phoneNumber(resultSet.getString(PHONE_NUMBER))
                .loyaltyPoints(resultSet.getInt(LOYALTY_POINTS))
                .userRole(Role.valueOf(resultSet.getString(ROLE)))
                .userStatus(Status.valueOf(resultSet.getString(STATUS)))
                .build();
    }
}