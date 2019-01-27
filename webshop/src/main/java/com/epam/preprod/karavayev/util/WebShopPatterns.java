package com.epam.preprod.karavayev.util;

import java.util.regex.Pattern;

public class WebShopPatterns {

    public static final Pattern NAME_PATTERN = Pattern.compile("^([A-Z][a-z]+)((\\s|-)[A-Z][a-z]+)?$");
    public static final Pattern
            EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    public static final Pattern
            PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}");

    public static final Pattern CREDIT_CARD_PATTERN = Pattern.compile("^(\\d{4}-){3}(\\d{4})$");

    public static boolean isMatchField(String field, Pattern pattern) {
        return pattern.matcher(field).matches();
    }



    private WebShopPatterns(){}
}
