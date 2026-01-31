package com.egoramel.webproject.service.impl;

import com.egoramel.webproject.exception.CustomException;
import com.egoramel.webproject.model.dao.AppUserDao;
import com.egoramel.webproject.model.dao.impl.AppUserDaoImpl;
import com.egoramel.webproject.model.entity.AppUser;
import com.egoramel.webproject.service.AppUserService;
import com.egoramel.webproject.util.PasswordEncryptor;
import com.egoramel.webproject.util.impl.PasswordEncryptorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static com.egoramel.webproject.model.entity.Role.USER;
import static com.egoramel.webproject.model.entity.Status.ACTIVATED;

public final class AppUserServiceImpl implements AppUserService {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final AppUserServiceImpl INSTANCE = new AppUserServiceImpl();
    private static final Integer INITIAL_LOYALTY_POINTS = 0;

    private AppUserServiceImpl() {
        LOGGER.debug("AppUserServiceImpl instance created.");
    }

    public static AppUserServiceImpl getInstance() {
        LOGGER.trace("Getting AppUserServiceImpl instance.");
        return INSTANCE;
    }

    @Override
    public boolean isLoginExist(final String login) {
        LOGGER.debug("Checking if login exists: {}.", login);
        final AppUserDao appUserDao = new AppUserDaoImpl();
        boolean isExist = false;

         try {
             if (appUserDao.existsByLogin(login)) {
                 isExist = true;
                 LOGGER.debug("Login '{}' already exists.", login);
             }
         } catch (final CustomException e) {
             LOGGER.error("Error checking login existence for '{}': {}.", login, e.getMessage());
         }

         return isExist;
    }

    @Override
    public boolean createNewAppUser(final String login, final String password) {
        LOGGER.info("Creating new user with login: {}.", login);
        final PasswordEncryptor passwordEncryptor = new PasswordEncryptorImpl();
        boolean isAppUserCreated = false;

        try {
            final String encryptedPassword = passwordEncryptor.encrypt(password);
            final AppUserDao appUserDao = new AppUserDaoImpl();
            final AppUser newAppUser = buildNewAppUser(login, encryptedPassword);

            LOGGER.debug("Attempting to save new user: {}.", login);
            isAppUserCreated = appUserDao.save(newAppUser);
        } catch (final NoSuchAlgorithmException | CustomException e) {
            LOGGER.error("Encryption error while creating user '{}': {}", login, e.getMessage());
        }

        return isAppUserCreated;
    }

    @Override
    public boolean deleteAppUser(final Object userIdObj) {
        final String userIdStr = userIdObj.toString();
        final Long userId = Long.valueOf(userIdStr);
        final AppUserDao appUserDao = new AppUserDaoImpl();

        try {
            final Optional<AppUser> appUserOptional = appUserDao.findById(userId);

            if (appUserOptional.isPresent()) {
                final AppUser appUser = appUserOptional.get();
                appUserDao.delete(appUser);

                return true;
            }

            return false;
        } catch (final CustomException e) {
            LOGGER.error("An error occurred while deleting user account.");
        }

        return false;
    }

    @Override
    public AppUser authenticate(final String login, final String password) {
        LOGGER.debug("Authenticating user: {}.", login);
        final PasswordEncryptor passwordEncryptor = new PasswordEncryptorImpl();

        try {
            final String encryptedPassword = passwordEncryptor.encrypt(password);
            LOGGER.trace("Password encrypted for authentication of user: {}.", login);

            final AppUserDao appUserDao = new AppUserDaoImpl();
            final Optional<AppUser> foundAppUserOptional = appUserDao.findByLogin(login);

            if (foundAppUserOptional.isPresent()) {
                final AppUser foundAppUser = foundAppUserOptional.get();
                final String foundAppUserPassword = foundAppUser.getUserPassword();

                if (encryptedPassword.equals(foundAppUserPassword)) {
                    return foundAppUser;
                }
            }
        } catch (final NoSuchAlgorithmException | CustomException e) {
            LOGGER.error("Encryption error during authentication for user '{}': {}.", login, e.getMessage());
        }

        return null;
    }

    private AppUser buildNewAppUser(final String login, final String encryptedPassword) {
        return AppUser.builder()
                .login(login)
                .userPassword(encryptedPassword)
                .loyaltyPoints(INITIAL_LOYALTY_POINTS)
                .userStatus(ACTIVATED)
                .userRole(USER)
                .build();
    }
}