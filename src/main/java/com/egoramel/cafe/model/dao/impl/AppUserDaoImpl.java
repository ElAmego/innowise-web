package com.egoramel.cafe.model.dao.impl;

import com.egoramel.cafe.model.constant.AppUserSqlQuery;
import com.egoramel.cafe.model.dao.AppUserDao;
import com.egoramel.cafe.model.entity.AppUser;
import com.egoramel.cafe.exception.CustomException;
import com.egoramel.cafe.model.mapper.AppUserMapper;
import com.egoramel.cafe.model.mapper.Mapper;
import com.egoramel.cafe.util.ConnectionManager;
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

        try (final Connection connection = ConnectionManager.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(AppUserSqlQuery.FIND_BY_ID_SQL);
            preparedStatement.setLong(1,userId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            AppUser appUser = null;

            if (resultSet.next()) {
                final Mapper<AppUser> userMapper = new AppUserMapper();
                appUser = userMapper.map(resultSet);
                LOGGER.debug("User found by ID {}.", userId);
            }

            return Optional.ofNullable(appUser);
        } catch (final SQLException e) {
            LOGGER.error("Error finding user by ID {}: {}.", userId, e.getMessage());
            throw new CustomException(e);
        }
    }

    @Override
    public boolean existsByLogin(final String login) throws CustomException {
        LOGGER.debug("Checking if user exists by login: {}.", login);

        try (final Connection connection = ConnectionManager.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(AppUserSqlQuery.FIND_BY_LOGIN_SQL);
            preparedStatement.setString(1, login);
            final ResultSet resultSet = preparedStatement.executeQuery();

            LOGGER.debug("User existence by login '{}'.", login);
            return resultSet.next();
        } catch (final SQLException e) {
            LOGGER.error("Error checking user existence by login {}: {}.", login, e.getMessage());
            throw new CustomException(e);
        }
    }

    @Override
    public Optional<AppUser> findByLogin(final String login) throws CustomException {
        LOGGER.debug("Finding user by login: {}.", login);
        try (final Connection connection = ConnectionManager.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(AppUserSqlQuery.FIND_BY_LOGIN_SQL);
            preparedStatement.setString(1,login);
            final ResultSet resultSet = preparedStatement.executeQuery();
            AppUser appUser = null;

            if (resultSet.next()) {
                final Mapper<AppUser> userMapper = new AppUserMapper();
                appUser = userMapper.map(resultSet);
                LOGGER.debug("User found by login '{}'.", login);
            }

            return Optional.ofNullable(appUser);
        } catch (final SQLException e) {
            LOGGER.error("Error finding user by login {}: {}.", login, e.getMessage());
            throw new CustomException(e);
        }
    }

    @Override
    public List<AppUser> findAll() throws CustomException{
        LOGGER.debug("Finding all users");
        final List<AppUser> appUserList = new ArrayList<>();

        try (final Connection connection = ConnectionManager.getConnection()) {
            final Statement statement = connection.createStatement();
            final ResultSet result = statement.executeQuery(AppUserSqlQuery.FIND_ALL_SQL);
            final Mapper<AppUser> userMapper = new AppUserMapper();

            while (result.next()) {
                final AppUser appUser = userMapper.map(result);
                appUserList.add(appUser);
            }
        } catch (final SQLException e) {
            LOGGER.error("Error in all user finding.");
            throw new CustomException(e);
        }

        return appUserList;
    }

    @Override
    public boolean save(final AppUser appUser) throws CustomException {
        LOGGER.debug("Saving new user.");

        try (final Connection connection = ConnectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(AppUserSqlQuery.SAVE_SQL)) {
            preparedStatement.setString(1, appUser.getLogin());
            preparedStatement.setString(2, appUser.getUserPassword());
            preparedStatement.setInt(3, appUser.getLoyaltyPoints());
            preparedStatement.setString(4, appUser.getUserStatus().toString());
            preparedStatement.setString(5, appUser.getUserRole().toString());

            final int res = preparedStatement.executeUpdate();

            return res > 0;
        } catch (final SQLException e) {
            LOGGER.error("Error saving user '{}': {}.", appUser.getLogin(), e.getMessage());
            throw new CustomException(e);
        }
    }

    @Override
    public void delete(final AppUser appUser) throws CustomException {
        LOGGER.debug("Deleting user.");

        try (final Connection connection = ConnectionManager.getConnection()) {
            final Long appUserId = appUser.getUserId();
            final PreparedStatement preparedStatement = connection.prepareStatement(AppUserSqlQuery.DELETE_SQL);

            preparedStatement.setLong(1, appUserId);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            LOGGER.debug("Error deleting user.");
            throw new CustomException(e);
        }
    }

    @Override
    public AppUser update(final AppUser appUser) throws CustomException {
        LOGGER.debug("Updating user.");

        try (final Connection connection = ConnectionManager.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(AppUserSqlQuery.DELETE_SQL);
            preparedStatement.setString(1, appUser.getLogin());
            preparedStatement.setString(2, appUser.getUserPassword());
            preparedStatement.setString(3, appUser.getPhoneNumber());
            preparedStatement.setInt(4, appUser.getLoyaltyPoints());
            preparedStatement.setString(5, appUser.getUserStatus().toString());
            preparedStatement.setString(6, appUser.getUserRole().toString());
            preparedStatement.setLong(7, appUser.getUserId());

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            LOGGER.debug("Error updating user.");
            throw new CustomException(e);
        }

        return appUser;
    }
}