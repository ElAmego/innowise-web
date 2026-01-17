package com.egoramel.cafe.controller.command.impl;

import com.egoramel.cafe.controller.constant.AuthParam;
import com.egoramel.cafe.controller.command.Command;
import com.egoramel.cafe.controller.constant.ErrorMessage;
import com.egoramel.cafe.controller.constant.PagePath;
import com.egoramel.cafe.service.AppUserService;
import com.egoramel.cafe.service.impl.AppUserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(final HttpServletRequest request) {
        LOGGER.info("Starting user login process.");

        final String login = request.getParameter(AuthParam.LOGIN);
        final String password = request.getParameter(AuthParam.PASSWORD);

        if (login == null || password == null || login.isBlank() || password.isBlank()) {
            LOGGER.warn("Login failed: login or password is null or blank.");
            request.setAttribute(AuthParam.LOGIN_ERROR_MESSAGE, ErrorMessage.ERROR_MESSAGE_BLANK_VALUES);
            return PagePath.LOGIN_PATH;
        }

        LOGGER.debug("Login attempt for user: {}.", login);

        final AppUserService appUserService = AppUserServiceImpl.getInstance();
        final boolean isAuthenticate = appUserService.authenticate(login, password);

        if (isAuthenticate) {
            LOGGER.info("User '{}' successfully authenticated.", login);
            request.setAttribute(AuthParam.LOGIN, login);
            return PagePath.MAIN_PATH;
        }

        LOGGER.warn("Authentication failed for user '{}': incorrect credentials.", login);
        request.setAttribute(AuthParam.LOGIN_ERROR_MESSAGE, ErrorMessage.ERROR_MESSAGE_INCORRECT_DATA);
        return PagePath.LOGIN_PATH;
    }
}