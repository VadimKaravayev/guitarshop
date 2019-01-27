package com.epam.preprod.karavayev.web.servlet;

import com.epam.preprod.karavayev.constant.Attributes;
import com.epam.preprod.karavayev.constant.Servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/" + Servlets.LOGOUT)
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (Objects.nonNull(session)) {
            session.removeAttribute(Attributes.USER_ENTITY);
        }
        resp.sendRedirect(Servlets.HOME);
    }
}
