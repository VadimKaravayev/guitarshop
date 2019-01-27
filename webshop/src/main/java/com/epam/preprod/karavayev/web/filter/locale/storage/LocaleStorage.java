package com.epam.preprod.karavayev.web.filter.locale.storage;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LocaleStorage {

    Locale getLocale(HttpServletRequest request);

    void setLocale(Locale locale, HttpServletRequest request, HttpServletResponse response);

}
