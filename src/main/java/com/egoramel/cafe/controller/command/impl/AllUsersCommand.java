package com.egoramel.cafe.controller.command.impl;

import com.egoramel.cafe.controller.command.Command;
import com.egoramel.cafe.controller.command.Router;
import com.egoramel.cafe.controller.command.RouterType;
import com.egoramel.cafe.exception.CustomException;
import com.egoramel.cafe.model.dao.AppUserDao;
import com.egoramel.cafe.model.dao.impl.AppUserDaoImpl;
import com.egoramel.cafe.model.entity.AppUser;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.egoramel.cafe.controller.constant.PagePath.SHOW_USERS_PATH;

public final class AllUsersCommand implements Command {
    @Override
    public Router execute(final HttpServletRequest request) {
        final Router router = new Router(SHOW_USERS_PATH, RouterType.FORWARD);
        final AppUserDao appUserDao = new AppUserDaoImpl();
        final List<AppUser> appUserList;
        try {
            appUserList = appUserDao.findAll();
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("users", appUserList);

        return router;
    }
}
