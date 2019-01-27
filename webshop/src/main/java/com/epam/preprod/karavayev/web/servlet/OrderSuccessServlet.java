package com.epam.preprod.karavayev.web.servlet;

import com.epam.preprod.karavayev.constant.Servlets;
import com.epam.preprod.karavayev.constant.UrlMapping;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/" + Servlets.ORDER_SUCCESS)
public class OrderSuccessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(UrlMapping.SUCCESS_JSP).forward(req, resp);
    }
}
