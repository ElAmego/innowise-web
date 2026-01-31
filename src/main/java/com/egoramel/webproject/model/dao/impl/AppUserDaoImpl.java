package com.egoramel.webproject.model.dao.impl;

import com.egoramel.webproject.model.constant.AppUserSqlQuery;
import com.egoramel.webproject.model.dao.AppUserDao;
import com.egoramel.webproject.model.entity.AppUser;
import com.egoramel.webproject.exception.CustomException;
import com.egoramel.webproject.model.mapper.AppUserMapper;
import com.egoramel.webproject.model.mapper.Mapper;
import com.egoramel.webproject.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class AppUserDaoImpl implements AppUserDao {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<AppUser> findById(final Long userId) throws CustomException {
        LOGGER.debug("Finding user by ID: {}.", userId);
        final Connection connection = ConnectionPool.getConnection();

        try {
            try (final PreparedStatement ps = connection.prepareStatement(AppUserSqlQuery.FIND_BY_ID_SQL)) {
                ps.setLong(1,userId);

                try (final ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        final Mapper<AppUser> userMapper = new AppUserMapper();
                        final AppUser appUser = userMapper.map(rs);
                        LOGGER.debug("User found by ID {}.", userId);

                        return Optional.of(appUser);
                    }

                    return Optional.empty();
                }
            }
        } catch (final SQLException e) {
            LOGGER.error("Error finding user by ID {}: {}.", userId, e.getMessage());
            throw new CustomException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public boolean existsByLogin(final String login) throws CustomException {
        LOGGER.debug("Checking if user exists by login: {}.", login);
        final Connection connection = ConnectionPool.getConnection();

        try {
            try (final PreparedStatement ps = connection.prepareStatement(AppUserSqlQuery.FIND_BY_LOGIN_SQL)) {
                ps.setString(1, login);

                try (final ResultSet rs = ps.executeQuery()) {
                    LOGGER.debug("User existence by login '{}'.", login);
                    return rs.next();
                }
            }
        } catch (final SQLException e) {
            LOGGER.error("Error checking user existence by login {}: {}.", login, e.getMessage());
            throw new CustomException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<AppUser> findByLogin(final String login) throws CustomException {
        LOGGER.debug("Finding user by login: {}.", login);
        final Connection connection = ConnectionPool.getConnection();

        try {
            try (final PreparedStatement ps = connection.prepareStatement(AppUserSqlQuery.FIND_BY_LOGIN_SQL)) {
                ps.setString(1,login);

                try (final ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        final Mapper<AppUser> userMapper = new AppUserMapper();
                        final AppUser appUser = userMapper.map(rs);
                        LOGGER.debug("User found by login '{}'.", login);

                        return Optional.of(appUser);
                    }

                    return Optional.empty();
                }
            }
        } catch (final SQLException e) {
            LOGGER.error("Error finding user by login {}: {}.", login, e.getMessage());
            throw new CustomException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<AppUser> findAll() throws CustomException{
        LOGGER.debug("Finding all users");
        final List<AppUser> appUserList = new ArrayList<>();
        final Connection connection = ConnectionPool.getConnection();

        try {
            try (final Statement st = connection.createStatement();
                 final ResultSet rs = st.executeQuery(AppUserSqlQuery.FIND_ALL_SQL))
            {
                final Mapper<AppUser> userMapper = new AppUserMapper();

                while (rs.next()) {
                    final AppUser appUser = userMapper.map(rs);
                    appUserList.add(appUser);
                }
            }
        } catch (final SQLException e) {
            LOGGER.error("Error in all user finding.");
            throw new CustomException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }

        return appUserList;
    }

    @Override
    public boolean save(final AppUser appUser) throws CustomException {
        LOGGER.debug("Saving new user.");
        final Connection connection = ConnectionPool.getConnection();

        try {
            try (final PreparedStatement ps = connection.prepareStatement(AppUserSqlQuery.SAVE_SQL)) {
                ps.setString(1, appUser.getLogin());
                ps.setString(2, appUser.getUserPassword());
                ps.setInt(3, appUser.getLoyaltyPoints());
                ps.setString(4, appUser.getUserStatus().toString());
                ps.setString(5, appUser.getUserRole().toString());

                final int res = ps.executeUpdate();

                return res > 0;
            }
        } catch (final SQLException e) {
            LOGGER.error("Error saving user '{}': {}.", appUser.getLogin(), e.getMessage());
            throw new CustomException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(final AppUser appUser) throws CustomException {
        LOGGER.debug("Deleting user.");
        final Connection connection = ConnectionPool.getConnection();

        try {
            final Long appUserId = appUser.getUserId();

            try (final PreparedStatement ps = connection.prepareStatement(AppUserSqlQuery.DELETE_SQL)) {
                ps.setLong(1, appUserId);
                ps.executeUpdate();
            }
        } catch (final SQLException e) {
            LOGGER.debug("Error deleting user.");
            throw new CustomException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public AppUser update(final AppUser appUser) throws CustomException {
        LOGGER.debug("Updating user.");
        final Connection connection = ConnectionPool.getConnection();

        try {
            try (final PreparedStatement ps = connection.prepareStatement(AppUserSqlQuery.UPDATE_SQL)) {
                ps.setString(1, appUser.getLogin());
                ps.setString(2, appUser.getUserPassword());
                ps.setString(3, appUser.getPhoneNumber());
                ps.setInt(4, appUser.getLoyaltyPoints());
                ps.setString(5, appUser.getUserStatus().toString());
                ps.setString(6, appUser.getUserRole().toString());
                ps.setLong(7, appUser.getUserId());

                ps.executeUpdate();
            }
        } catch (final SQLException e) {
            LOGGER.debug("Error updating user.");
            throw new CustomException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }

        return appUser;
    }
}