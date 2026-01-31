package com.egoramel.webproject.controller.command.impl;

import com.egoramel.webproject.controller.command.Command;
import com.egoramel.webproject.controller.command.Router;
import com.egoramel.webproject.controller.command.RouterType;
import com.egoramel.webproject.controller.constant.PagePath;
import com.egoramel.webproject.controller.constant.RequestParameter;
import com.egoramel.webproject.service.AppUserService;
import com.egoramel.webproject.service.impl.AppUserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public final class DeleteCommand implements Command {
    @Override
    public Router execute(final HttpServletRequest request) {
        final HttpSession httpSession = request.getSession();

        if (httpSession != null) {
            final Object userIdObj = httpSession.getAttribute(RequestParameter.USER_ID);

            if (userIdObj != null) {
                final AppUserService appUserService = AppUserServiceImpl.getInstance();
                final boolean isDeleted = appUserService.deleteAppUser(userIdObj);

                if (isDeleted) {
                    httpSession.invalidate();
                    return new Router(PagePath.INDEX_PATH, RouterType.REDIRECT);
                }
            }
        }

        return new Router(PagePath.MAIN_PATH, RouterType.FORWARD);
    }
}
