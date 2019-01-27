package com.epam.preprod.karavayev.web.filter;

import com.epam.preprod.karavayev.constant.Attributes;
import com.epam.preprod.karavayev.constant.Servlets;
import com.epam.preprod.karavayev.dto.Constraint;
import com.epam.preprod.karavayev.entity.User;
import com.epam.preprod.karavayev.util.XMLAccessParser;
import com.epam.preprod.karavayev.web.filter.access.AccessManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AccessFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AccessFilter.class);

    private AccessManager accessManager;

    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext sc = filterConfig.getServletContext();
        List<Constraint> constraints = new XMLAccessParser()
                .parseToConstraints(sc.getRealPath(filterConfig.getInitParameter("securityPath")));
        accessManager = new AccessManager(constraints);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!accessManager.isExistPermissionForURI(getURIWithoutContextPath(request.getRequestURI(),
                request.getContextPath()))) {
            chain.doFilter(request, servletResponse);
        } else {
            User user = (User) request.getSession().getAttribute(Attributes.USER_ENTITY);
            if (Objects.isNull(user)) {
                response.sendRedirect(Servlets.LOGIN);
            } else {
                if (accessManager.hasUserRolePermissionForURI(user.getRole(),
                        getURIWithoutContextPath(request.getRequestURI(), request.getContextPath()))) {
                    chain.doFilter(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            }
        }

    }

    @Override
    public void destroy() {

    }

    private String getURIWithoutContextPath(String uri, String contextPath) {
        return uri.replaceFirst(contextPath, StringUtils.EMPTY);
    }
}
