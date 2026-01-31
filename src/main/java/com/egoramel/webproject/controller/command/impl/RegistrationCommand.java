package com.egoramel.webproject.controller.command.impl;

import com.egoramel.webproject.controller.command.Router;
import com.egoramel.webproject.controller.command.RouterType;
import com.egoramel.webproject.controller.constant.RequestParameter;
import com.egoramel.webproject.controller.constant.PagePath;
import com.egoramel.webproject.controller.command.Command;
import com.egoramel.webproject.service.AppUserService;
import com.egoramel.webproject.service.impl.AppUserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class RegistrationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String ERROR_MESSAGE_LOGIN =  "User with the specified login already exists. Specify new login.";
    public static final String ERROR_MESSAGE =  "An error occurred during registration. Try one more time.";
    public static final String ERROR_MESSAGE_BLANK_VALUES =  "Fields cannot be empty.";

    @Override
    public Router execute(final HttpServletRequest request) {
        LOGGER.info("Starting user registration process.");

        final String login = request.getParameter(RequestParameter.LOGIN);
        final String password = request.getParameter(RequestParameter.PASSWORD);

        if (login == null || password == null || login.isBlank() || password.isBlank()) {
            LOGGER.warn("Registration failed: login or password is null or blank.");
            request.setAttribute(RequestParameter.REGISTRATION_ERROR_MESSAGE, ERROR_MESSAGE_BLANK_VALUES);
            return new Router(PagePath.REGISTRATION_PATH, RouterType.FORWARD);
        }

        LOGGER.debug("Registration attempt for login: {}.", login);

        final AppUserService appUserService = AppUserServiceImpl.getInstance();
        final boolean isLoginExist = appUserService.isLoginExist(login);

        if (isLoginExist) {
            LOGGER.warn("Registration failed: login '{}' already exists.", login);
            request.setAttribute(RequestParameter.REGISTRATION_ERROR_MESSAGE, ERROR_MESSAGE_LOGIN);
            return new Router(PagePath.REGISTRATION_PATH, RouterType.FORWARD);
        }

        LOGGER.debug("Creating new user with login: {}.", login);
        final boolean isAppUserCreated = appUserService.createNewAppUser(login, password);

        if (isAppUserCreated) {
            LOGGER.info("User '{}' successfully registered.", login);
            return new Router(PagePath.LOGIN_PATH, RouterType.REDIRECT);
        } else {
            LOGGER.error("Failed to create user '{}' in database.", login);
            request.setAttribute(RequestParameter.REGISTRATION_ERROR_MESSAGE, ERROR_MESSAGE);
            return new Router(PagePath.REGISTRATION_PATH, RouterType.FORWARD);
        }
    }
}