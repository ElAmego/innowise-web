package com.egoramel.cafe.controller.command.impl;

import com.egoramel.cafe.controller.constant.AuthParam;
import com.egoramel.cafe.controller.constant.ErrorMessage;
import com.egoramel.cafe.controller.constant.PagePath;
import com.egoramel.cafe.controller.command.Command;
import com.egoramel.cafe.service.AppUserService;
import com.egoramel.cafe.service.impl.AppUserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class RegistrationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(final HttpServletRequest request) {
        LOGGER.info("Starting user registration process.");

        final String login = request.getParameter(AuthParam.LOGIN);
        final String password = request.getParameter(AuthParam.PASSWORD);

        if (login == null || password == null || login.isBlank() || password.isBlank()) {
            LOGGER.warn("Registration failed: login or password is null or blank.");
            request.setAttribute(AuthParam.REGISTRATION_ERROR_MESSAGE, ErrorMessage.ERROR_MESSAGE_BLANK_VALUES);
            return PagePath.REGISTRATION_PATH;
        }

        LOGGER.debug("Registration attempt for login: {}.", login);

        final AppUserService appUserService = AppUserServiceImpl.getInstance();
        final boolean isLoginExist = appUserService.isLoginExist(login);

        if (isLoginExist) {
            LOGGER.warn("Registration failed: login '{}' already exists.", login);
            request.setAttribute(AuthParam.REGISTRATION_ERROR_MESSAGE, ErrorMessage.ERROR_MESSAGE_LOGIN);
            return PagePath.REGISTRATION_PATH;
        }

        LOGGER.debug("Creating new user with login: {}.", login);
        final boolean isAppUserCreated = appUserService.createNewAppUser(login, password);

        if (isAppUserCreated) {
            LOGGER.info("User '{}' successfully registered.", login);
            return PagePath.LOGIN_PATH;
        } else {
            LOGGER.error("Failed to create user '{}' in database.", login);
            request.setAttribute(AuthParam.REGISTRATION_ERROR_MESSAGE, ErrorMessage.ERROR_MESSAGE);
            return PagePath.REGISTRATION_PATH;
        }
    }
}