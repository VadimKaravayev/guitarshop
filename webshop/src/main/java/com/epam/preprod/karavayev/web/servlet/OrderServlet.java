package com.epam.preprod.karavayev.web.servlet;

import com.epam.preprod.karavayev.cart.Cart;
import com.epam.preprod.karavayev.constant.*;
import com.epam.preprod.karavayev.constant.Error;
import com.epam.preprod.karavayev.constant.RequestParameters;
import com.epam.preprod.karavayev.constant.Servlets;
import com.epam.preprod.karavayev.constant.UrlMapping;
import com.epam.preprod.karavayev.constant.ViewMessages;
import com.epam.preprod.karavayev.converter.CartToListProductItems;
import com.epam.preprod.karavayev.entity.Order;
import com.epam.preprod.karavayev.entity.User;
import com.epam.preprod.karavayev.service.OrderService;
import com.epam.preprod.karavayev.util.WebShopPatterns;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/" + Servlets.ORDER)
public class OrderServlet extends HttpServlet {

    private CartToListProductItems converter;
    private OrderService orderService;

    @Override
    public void init() {
        converter = new CartToListProductItems();
        orderService = (OrderService) getServletContext().getAttribute(Attributes.CONTEXT_ORDER_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute(Attributes.CART);
        String address = StringUtils.defaultString(req.getParameter(RequestParameters.CHECKOUT_FORM_ADDRESS));
        String creditCard = StringUtils.defaultString(req.getParameter(RequestParameters.CHECKOUT_FORM_CARDNUMBER));

        Map<String, String> errors = validate(address, creditCard, cart);

        if (errors.isEmpty()) {
            Order order = new Order();
            order.setAddress(address);
            order.setCreditCard(creditCard);
            order.setUser((User) session.getAttribute(Attributes.USER_ENTITY));
            order.setProductItems(converter.convert(cart));
            orderService.create(order);
            cart.getCartMap().clear();
            resp.sendRedirect(Servlets.ORDER_SUCCESS); // to successful page
            return;
        }
        session.setAttribute(Attributes.USER_CHECKOUT_ERRORS, errors);
        resp.sendRedirect(Servlets.ORDER);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute(Attributes.USER_CHECKOUT_ERRORS, session.getAttribute(Attributes.USER_CHECKOUT_ERRORS));
        session.removeAttribute(Attributes.USER_CHECKOUT_ERRORS);
        req.getRequestDispatcher(UrlMapping.ORDER_JSP).forward(req, resp);
    }

    private Map<String, String> validate(String address, String creditCard, Cart cart) {
        Map<String, String> errors = new HashMap<>();
        if (StringUtils.isBlank(address)) {
            errors.put(Error.ADDRESS, ViewMessages.SHIPPING_ADDRESS);
        }

        if (!WebShopPatterns.isMatchField(creditCard, WebShopPatterns.CREDIT_CARD_PATTERN)) {
            errors.put(Error.CREDIT_CARD, ViewMessages.WRONG_CREDIT_CARD);
        }

        if (cart.getCartMap().isEmpty()) {
            errors.put(Error.CART, ViewMessages.EMPTY_CART);
        }

        return errors;
    }
}