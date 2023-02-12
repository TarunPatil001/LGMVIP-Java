package com.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Servlet for URL pattern /rates
public class Rates extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String api_URL = "";
    private String base = "";
       
    // Public Class Constructor
    public Rates() {
        super();
    }

    // Handling requests from client
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        base = req.getParameter("base");
        api_URL = "https://api.exchangerate.host/latest?base=" + base;
    	res.setContentType("application/json");
    	res.setCharacterEncoding("UTF-8");
    	res.setStatus(200);
    	res.sendRedirect(api_URL);
    }
}
