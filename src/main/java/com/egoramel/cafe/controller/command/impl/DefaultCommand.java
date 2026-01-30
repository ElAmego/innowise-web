package com.egoramel.cafe.controller.command.impl;

import com.egoramel.cafe.controller.command.Command;
import com.egoramel.cafe.controller.command.Router;
import com.egoramel.cafe.controller.command.RouterType;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.egoramel.cafe.controller.constant.PagePath.ERROR_404_PATH;

public final class DefaultCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(final HttpServletRequest request) {
        LOGGER.info("404 redirect.");
        return new Router(ERROR_404_PATH, RouterType.REDIRECT);
    }
}