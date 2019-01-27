package com.epam.preprod.karavayev.captcha.store.method.impl;

import com.epam.preprod.karavayev.captcha.Captcha;
import com.epam.preprod.karavayev.constant.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class HiddenFieldCaptchaStoreMethod extends AbstractStoreMethod {

    public HiddenFieldCaptchaStoreMethod(Map<String, Captcha> captchaMap, int timeout) {
        super(captchaMap, timeout);
    }

    @Override
    protected void storeUuid(HttpServletRequest request, HttpServletResponse response, String uuid) {
    }

    @Override
    protected String getUuid(HttpServletRequest request) {
        return request.getParameter(Attributes.CAPTCHA_ID);
    }
}
