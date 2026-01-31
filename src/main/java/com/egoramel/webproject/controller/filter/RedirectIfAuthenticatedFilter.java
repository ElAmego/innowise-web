package com.egoramel.webproject.controller.filter;

import com.egoramel.webproject.controller.constant.PagePath;
import com.egoramel.webproject.controller.constant.RequestParameter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public final class RedirectIfAuthenticatedFilter implements Filter {
    private static final String PREVIOUS = "../";

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute(RequestParameter.USER_ID) != null) {
            resp.sendRedirect(PREVIOUS + PagePath.MAIN_PATH);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}