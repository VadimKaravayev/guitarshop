package com.epam.preprod.karavayev.captcha.store.method.impl;

import com.epam.preprod.karavayev.captcha.Captcha;
import com.epam.preprod.karavayev.constant.Attributes;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionCaptchaStoreMethod extends AbstractStoreMethod {

    public SessionCaptchaStoreMethod(Map<String, Captcha> captchaMap, int timeout) {
        super(captchaMap, timeout);
    }

    @Override
    protected void storeUuid(HttpServletRequest request, HttpServletResponse response, String uuid) {
        request.getSession().setAttribute(Attributes.CAPTCHA_ID, uuid);
    }

    @Override
    protected String getUuid(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(Attributes.CAPTCHA_ID);
    }
}
