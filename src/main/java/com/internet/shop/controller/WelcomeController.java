package com.internet.shop.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String timeValue = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
        req.setAttribute("time", timeValue);
        req.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(req, resp);
    }
}
