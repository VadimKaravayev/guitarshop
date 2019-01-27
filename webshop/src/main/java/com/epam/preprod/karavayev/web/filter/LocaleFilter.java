package com.epam.preprod.karavayev.web.filter;

import com.epam.preprod.karavayev.constant.ContextParam;
import com.epam.preprod.karavayev.constant.RequestParameters;
import com.epam.preprod.karavayev.web.filter.locale.storage.CookieLocaleStorage;
import com.epam.preprod.karavayev.web.filter.locale.storage.LocaleStorage;
import com.epam.preprod.karavayev.web.filter.locale.storage.SessionLocaleStorage;
import com.google.common.primitives.Ints;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class LocaleFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(LocaleFilter.class);
    private static final Pattern LOCALE_PATTERN = Pattern.compile("^([a-z]{2})(_[A-Z]{2})?(_[A-Z]{2})?$");
    private static final String COOKIE = "cookie";
    private static final String SESSION = "session";
    private List<Locale> locales = new ArrayList<>();
    private Locale defaultLocale;
    private LocaleStorage localeStorage;

    @Override
    public void init(FilterConfig filterConfig)  {
        defaultLocale = Locale.forLanguageTag(filterConfig.getInitParameter(ContextParam.DEFAULT_LOCALE));
        localeStorage = getLocaleStorage(filterConfig);
        String[] localeTags = filterConfig.getInitParameter(ContextParam.LOCALES).split(StringUtils.SPACE);
        locales.addAll(Arrays.stream(localeTags).map(Locale::forLanguageTag).collect(Collectors.toList()));
        if (checkDeployerLocaleParam(locales.toArray(new Locale[0])) || checkDeployerLocaleParam(defaultLocale)) {
            throw new IllegalArgumentException("Illegal locale param");
        }
    }

    private LocaleStorage getLocaleStorage(FilterConfig filterConfig) {
        String storageImpl = filterConfig.getInitParameter(ContextParam.LOCALE_STORAGE);
        switch (storageImpl) {
            case COOKIE:
                return new CookieLocaleStorage(Ints.tryParse(filterConfig.getInitParameter(ContextParam.COOKIE_EXPIRY)));
            case SESSION:
                return new SessionLocaleStorage();
            default:
                throw new IllegalArgumentException("Illegal locale storage");
        }
    }

    private boolean checkDeployerLocaleParam(Locale... param) {
        return Arrays.stream(param).anyMatch(locale -> !LOCALE_PATTERN.matcher(locale.toLanguageTag()).matches());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String lang = req.getParameter(RequestParameters.LANGUAGE);
        Locale locale;
        if (Objects.nonNull(lang) && locales.contains((locale = Locale.forLanguageTag(lang)))) {
            localeStorage.setLocale(locale, req, resp);
        } else {
            locale = getAcceptableLocale(req, resp);
        }

        RequestLocaleWrapper requestLocaleWrapper = new RequestLocaleWrapper(req, locale);
        chain.doFilter(requestLocaleWrapper, response);
    }

    private Locale getAcceptableLocale(HttpServletRequest request, HttpServletResponse response) {
        Locale locale = localeStorage.getLocale(request);
        if (!locales.contains(locale)) {
            locale = getLocaleFromRequest(request, locales);
            if (Objects.isNull(locale)) {
                locale = defaultLocale;
            }
            localeStorage.setLocale(locale, request, response);
        }
        return locale;
    }

    private Locale getLocaleFromRequest(HttpServletRequest request, List<Locale> locales) {
        Enumeration<Locale> clientLocales = request.getLocales();
        while (clientLocales.hasMoreElements()) {
            Locale temp = clientLocales.nextElement();
            if (locales.contains(temp)) {
                return temp;
            }
        }
        return null;
    }

    @Override
    public void destroy() {

    }

    private class RequestLocaleWrapper extends HttpServletRequestWrapper {

        private Locale locale;

        /**
         * Constructs a request object wrapping the given request.
         *
         * @throws IllegalArgumentException if the request is null
         */
        public RequestLocaleWrapper(HttpServletRequest request, Locale locale) {
            super(request);
            this.locale = locale;
        }

        @Override
        public Locale getLocale() {
            return locale;
        }

        @Override
        public Enumeration<Locale> getLocales() {
            return Collections.enumeration(Arrays.asList(locale));
        }
    }


}
