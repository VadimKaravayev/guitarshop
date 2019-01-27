package com.epam.preprod.karavayev.captcha;

import java.time.LocalDateTime;
import java.util.Map;

public class CaptchaCleaner implements Runnable {

    private Map<String, Captcha> captchaMap;

    public CaptchaCleaner(Map<String, Captcha> captchaMap) {
        this.captchaMap = captchaMap;
    }

    @Override
    public void run() {
        captchaMap.entrySet().removeIf(captchaEntry -> captchaEntry.getValue().getExpirationDate().isBefore(LocalDateTime.now()));
    }
}
