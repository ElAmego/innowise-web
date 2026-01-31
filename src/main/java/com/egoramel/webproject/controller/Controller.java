package com.egoramel.webproject.controller;

import com.egoramel.webproject.controller.command.Command;
import com.egoramel.webproject.controller.command.CommandType;
import com.egoramel.webproject.controller.command.Router;
import com.egoramel.webproject.controller.command.RouterType;
import com.egoramel.webproject.util.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(urlPatterns = "/controller")
public final class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    public void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException, ServletException {
        handleRequest(req, resp);
    }

    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException, ServletException {
        handleRequest(req, resp);
    }

    public void handleRequest(final HttpServletRequest req, final HttpServletResponse resp) throws IOException, ServletException {
        LOGGER.info("Processing GET request to controller.");
        resp.setContentType("text/html");
        final String commandStr = req.getParameter("command");
        final Command command = CommandType.define(commandStr);
        final Router page = command.execute(req);
        final String pagePath = page.pagePath();

        LOGGER.debug("Forwarding to page: {}.", page);
        if (page.routerType() == RouterType.FORWARD) {
            req.getRequestDispatcher(pagePath).forward(req, resp);
        } else {
            resp.sendRedirect(pagePath);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.destroyPool();
    }
}