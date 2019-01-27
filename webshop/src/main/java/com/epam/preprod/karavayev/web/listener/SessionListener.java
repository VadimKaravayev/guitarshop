package com.epam.preprod.karavayev.web.listener;

import com.epam.preprod.karavayev.cart.Cart;
import com.epam.preprod.karavayev.constant.Attributes;

import javax.servlet.annotation.WebListener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute(Attributes.CART, new Cart());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        se.getSession().removeAttribute(Attributes.CART);
    }
}
