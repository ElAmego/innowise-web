package com.egoramel.cafe.controller.command.impl;

import com.egoramel.cafe.controller.command.Command;
import com.egoramel.cafe.controller.command.Router;
import com.egoramel.cafe.controller.command.RouterType;
import com.egoramel.cafe.controller.constant.PagePath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public final class LogoutCommand implements Command {
    @Override
    public Router execute(final HttpServletRequest request) {
        final HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return new Router(PagePath.INDEX_PATH, RouterType.REDIRECT);
    }
}