package com.epam.preprod.karavayev.web.filter.locale.storage;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieLocaleStorage implements LocaleStorage {

    private int expiry;

    public CookieLocaleStorage(int expiry) {
        this.expiry = expiry;
    }

    @Override
    public Locale getLocale(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return null;
        }
        return Locale.forLanguageTag(Stream.of(cookies)
                                             .filter(cookie -> Objects.equals(cookie.getName(), "locale"))
                                             .map(Cookie::getValue).findFirst().orElse(StringUtils.EMPTY));

    }

    @Override
    public void setLocale(Locale locale, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("locale", locale.toLanguageTag());
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);
    }
}
