package com.egoramel.webproject.controller.command;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    Router execute(final HttpServletRequest request);
}