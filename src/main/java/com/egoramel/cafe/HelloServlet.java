package com.egoramel.cafe;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public final class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World";
    }

    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        final PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");
        writer.println("<h1>" + message + "</h1>");
        writer.println("</body></html>");
    }
}