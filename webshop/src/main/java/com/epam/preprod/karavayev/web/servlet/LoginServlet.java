package com.epam.preprod.karavayev.web.servlet;

import com.epam.preprod.karavayev.constant.*;
import com.epam.preprod.karavayev.constant.Error;
import com.epam.preprod.karavayev.entity.User;
import com.epam.preprod.karavayev.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/" + Servlets.LOGIN)
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute(Attributes.CONTEXT_USER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Servlets.HOME).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attributes.USER_ENTITY);
        if (Objects.nonNull(user)) {
            return;
        }
        String email = request.getParameter(RequestParameters.LOGIN_EMAIL);
        String password = request.getParameter(RequestParameters.LOGIN_PASSWORD);
        user = userService.login(email, password);

        if (Objects.isNull(user)) {
            session.setAttribute(Error.LOGIN, ViewMessages.INCORRECT_LOGIN);
            session.setAttribute(Attributes.EMAIL, email);
            response.sendRedirect(Servlets.HOME);
        } else {
            session.setAttribute(Attributes.USER_ENTITY, user);
            response.sendRedirect(Servlets.HOME);
        }

    }
}
