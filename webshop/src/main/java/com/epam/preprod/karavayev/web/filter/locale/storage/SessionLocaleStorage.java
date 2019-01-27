package com.epam.preprod.karavayev.web.filter.locale.storage;

import com.epam.preprod.karavayev.constant.Attributes;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionLocaleStorage implements LocaleStorage {

    @Override
    public Locale getLocale(HttpServletRequest request) {
        return (Locale) request.getSession().getAttribute(Attributes.LOCALE);
    }

    @Override
    public void setLocale(Locale locale, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(Attributes.LOCALE, locale);
    }
}
