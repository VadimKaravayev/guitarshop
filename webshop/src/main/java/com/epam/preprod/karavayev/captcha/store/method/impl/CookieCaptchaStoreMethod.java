package com.epam.preprod.karavayev.captcha.store.method.impl;

import com.epam.preprod.karavayev.captcha.Captcha;
import com.epam.preprod.karavayev.constant.Attributes;
import com.epam.preprod.karavayev.constant.ContextParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class CookieCaptchaStoreMethod extends AbstractStoreMethod {

    public CookieCaptchaStoreMethod(Map<String, Captcha> captchaMap, int timeout) {
        super(captchaMap, timeout);
    }

    @Override
    protected void storeUuid(HttpServletRequest request, HttpServletResponse response, String uuid) {
        Cookie cookie = new Cookie(Attributes.CAPTCHA_ID, uuid);
        cookie.setMaxAge(timeout);
        response.addCookie(cookie);
    }

    @Override
    protected String getUuid(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return null;
        }
        return Arrays.stream(cookies).filter(cookie -> Objects.equals(cookie.getName(), Attributes.CAPTCHA_ID))
                .map(Cookie::getValue).findFirst().orElse(null);
    }
}