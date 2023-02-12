package com.servlets;

import java.io.IOException;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Servlet for URL pattern /fluctuations
public class Fluctuations extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String api_URL = "";
    private String base = "";

    // Public Class Constructor
    public Fluctuations() {
        super();
    }

    // Handling request from client
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        base = req.getParameter("base");
        Calendar today_date = Calendar.getInstance();
        Calendar yest_date = Calendar.getInstance();
        yest_date.add(Calendar.DATE, -1);
        SimpleDateFormat format_date = new SimpleDateFormat("yyyy-MM-dd");
        api_URL = "https://api.exchangerate.host/" + format_date.format(yest_date.getTime()) + "?places=3&base=" + base;
        System.out.println("date: " + format_date.format(today_date.getTime()) + " " + format_date.format(yest_date.getTime()));
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.setStatus(200);
        res.sendRedirect(api_URL);
    }
}
