package com.epam.preprod.karavayev.web.servlet;

import com.epam.preprod.karavayev.cart.Cart;
import com.epam.preprod.karavayev.constant.Attributes;
import com.epam.preprod.karavayev.constant.ContentType;
import com.epam.preprod.karavayev.constant.Servlets;
import com.epam.preprod.karavayev.constant.UrlMapping;
import com.epam.preprod.karavayev.service.ProductService;
import com.google.common.primitives.Ints;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

@WebServlet("/" + Servlets.CART)
public class CartServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CartServlet.class);

    private ProductService productService;

    @Override
    public void init() {
        productService = (ProductService) getServletContext().getAttribute(Attributes.CONTEXT_PRODUCT_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cart cart = (Cart) request.getSession().getAttribute(Attributes.CART);
        Integer id = Ints.tryParse(request.getParameter("id"));
        if (Objects.nonNull(id) && id > 0) {
            cart.addToCart(productService.getProduct(id));
            response.setContentType(ContentType.APPLICATION_JSON);
            response.getWriter().write("{\"count\":" + cart.getCount() + "}");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute(Attributes.CART);
        String param = StringUtils.defaultString(req.getParameter("id"));
        Integer id = Ints.tryParse(param);
        if (Objects.nonNull(id)) {
            cart.removeFromCart(id);
            resp.setContentType(ContentType.APPLICATION_JSON);
            double totalSum = Math.ceil(cart.getTotalSum() * 100) / 100;
            resp.getWriter().write("{\"totalPrice\":" + totalSum + "}");
        } else if (Objects.equals(param, StringUtils.EMPTY)) {
            cart.getCartMap().clear();
            resp.setContentType(ContentType.APPLICATION_JSON);
            resp.getWriter().write("{\"count\":" + cart.getCount() + "}");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute(Attributes.CART);
        Integer id = Ints.tryParse(req.getParameter("id"));
        Integer count = Ints.tryParse(req.getParameter("count"));
        if (Objects.nonNull(id) && id > 0 && Objects.nonNull(count) && count >= 0) {
            cart.setItemCount(id, count);
            resp.setContentType(ContentType.APPLICATION_JSON);
            resp.getWriter().write("{\"totalPrice\":" + (Math.ceil(cart.getTotalSum() * 100) / 100) + "}");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute(Attributes.CART);
        double totalSum = Math.ceil(cart.getTotalSum() * 100) / 100;
        session.setAttribute(Attributes.TOTAL_SUM, totalSum);

        req.getRequestDispatcher(UrlMapping.SHOPPING_CART_JSP).forward(req, resp);
    }
}
