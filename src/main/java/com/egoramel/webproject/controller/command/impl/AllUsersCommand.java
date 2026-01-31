package com.egoramel.webproject.controller.command.impl;

import com.egoramel.webproject.controller.command.Command;
import com.egoramel.webproject.controller.command.Router;
import com.egoramel.webproject.controller.command.RouterType;
import com.egoramel.webproject.controller.constant.RequestParameter;
import com.egoramel.webproject.exception.CustomException;
import com.egoramel.webproject.model.dao.AppUserDao;
import com.egoramel.webproject.model.dao.impl.AppUserDaoImpl;
import com.egoramel.webproject.model.entity.AppUser;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.egoramel.webproject.controller.constant.PagePath.SHOW_USERS_PATH;

public final class AllUsersCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(final HttpServletRequest request) {
        final Router router = new Router(SHOW_USERS_PATH, RouterType.FORWARD);
        final AppUserDao appUserDao = new AppUserDaoImpl();
        List<AppUser> appUserList = null;

        try {
            appUserList = appUserDao.findAll();
        } catch (final CustomException e) {
            LOGGER.error("An error occurred while finding all AppUser-s.");
        }

        request.setAttribute(RequestParameter.USERS, appUserList);

        return router;
    }
}