package com.egoramel.webproject.controller.filter;

import com.egoramel.webproject.controller.constant.PagePath;
import com.egoramel.webproject.controller.constant.RequestParameter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public final class AuthFilter implements Filter {
    private static final String PREVIOUS = "../";

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession(false);
        final String path = req.getServletPath();
        final String command = req.getParameter("command");

        if ("/controller".equals(path) && "login".equals(command)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (session != null && session.getAttribute(RequestParameter.USER_ID) != null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (req.getServletPath().equals("/controller")) {
            if ("all_users".equals(command) || "delete".equals(command)) {
                resp.sendRedirect(PREVIOUS + PagePath.INDEX_PATH);
                return;
            }
        }

        resp.sendRedirect(PREVIOUS + PagePath.INDEX_PATH);
    }
}