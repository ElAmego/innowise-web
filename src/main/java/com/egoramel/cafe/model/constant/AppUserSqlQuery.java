package com.egoramel.cafe.model.constant;

public final class AppUserSqlQuery {
    public static final String FIND_BY_ID_SQL = """
            SELECT *
            FROM app_user
            WHERE user_id = ?
            """;

    public static final String FIND_ALL_SQL = """
            SELECT *
            FROM app_user
            """;

    public static final String FIND_BY_LOGIN_SQL = """
            SELECT *
            FROM app_user
            WHERE login = ?
            """;

    public static final String SAVE_SQL = """
            INSERT INTO app_user (login, user_password, loyalty_points, user_status, user_role)
            VALUES (?, ?, ?, ?, ?);
            """;

    private AppUserSqlQuery() {

    }
}