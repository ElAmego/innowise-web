package com.egoramel.cafe.controller.command.impl;

import com.egoramel.cafe.controller.command.Router;
import com.egoramel.cafe.controller.command.RouterType;
import com.egoramel.cafe.controller.constant.AuthParameter;
import com.egoramel.cafe.controller.command.Command;
import com.egoramel.cafe.controller.constant.PagePath;
import com.egoramel.cafe.model.entity.AppUser;
import com.egoramel.cafe.service.AppUserService;
import com.egoramel.cafe.service.impl.AppUserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String ERROR_MESSAGE_BLANK_VALUES =  "Fields cannot be empty.";
    public static final String ERROR_MESSAGE_INCORRECT_DATA =  "Incorrect login or password. Try one more time.";

    @Override
    public Router execute(final HttpServletRequest request) {
        LOGGER.info("Starting user login process.");

        final String login = request.getParameter(AuthParameter.LOGIN);
        final String password = request.getParameter(AuthParameter.PASSWORD);

        if (login == null || password == null || login.isBlank() || password.isBlank()) {
            LOGGER.warn("Login failed: login or password is null or blank.");
            request.setAttribute(AuthParameter.LOGIN_ERROR_MESSAGE, ERROR_MESSAGE_BLANK_VALUES);
            return new Router(PagePath.LOGIN_PATH, RouterType.FORWARD);
        }

        LOGGER.debug("Login attempt for user: {}.", login);

        final AppUserService appUserService = AppUserServiceImpl.getInstance();
        final AppUser foundAppUser = appUserService.authenticate(login, password);

        if (foundAppUser != null) {
            LOGGER.info("User '{}' successfully authenticated.", login);

            final HttpSession httpSession = request.getSession(true);
            httpSession.setAttribute(AuthParameter.USER_ID, foundAppUser.getUserId());
            httpSession.setAttribute(AuthParameter.LOGIN, foundAppUser.getLogin());
            httpSession.setAttribute(AuthParameter.USER_ROLE, foundAppUser.getUserRole());
            return new Router(PagePath.MAIN_PATH, RouterType.REDIRECT);
        }

        LOGGER.warn("Authentication failed for user '{}': incorrect credentials.", login);
        request.setAttribute(AuthParameter.LOGIN_ERROR_MESSAGE, ERROR_MESSAGE_INCORRECT_DATA);
        return new Router(PagePath.LOGIN_PATH, RouterType.FORWARD);
    }
}