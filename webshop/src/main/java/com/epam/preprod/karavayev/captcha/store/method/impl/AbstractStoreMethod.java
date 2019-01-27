package com.epam.preprod.karavayev.captcha.store.method.impl;

import com.epam.preprod.karavayev.captcha.Captcha;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;


public abstract class AbstractStoreMethod {

    protected Map<String, Captcha> captchaMap;
    protected int timeout;

    public AbstractStoreMethod(Map<String, Captcha> captchaMap, int timeout) {

        this.captchaMap = captchaMap;
        this.timeout = timeout;
    }

    public void store(HttpServletRequest request, HttpServletResponse response, Captcha captcha, String uuid) {
        captchaMap.put(uuid, captcha);
        storeUuid(request, response, uuid);
    }

    public Captcha getCaptcha(HttpServletRequest request) {
        String uuid = getUuid(request);
        if(StringUtils.isBlank(uuid)){
            return null;
        }
        return captchaMap.remove(uuid);
    }

    protected abstract void storeUuid(HttpServletRequest request, HttpServletResponse response, String uuid);

    protected abstract String getUuid(HttpServletRequest request);
}