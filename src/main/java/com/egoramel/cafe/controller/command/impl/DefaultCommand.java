package com.egoramel.cafe.controller.command.impl;

import com.egoramel.cafe.controller.command.Command;
import com.egoramel.cafe.controller.command.Router;
import com.egoramel.cafe.controller.command.RouterType;
import com.egoramel.cafe.controller.constant.PagePath;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DefaultCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(final HttpServletRequest request) {
        LOGGER.info("Index redirect.");
        return new Router(PagePath.INDEX_PATH, RouterType.REDIRECT);
    }
}