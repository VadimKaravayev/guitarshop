package com.epam.preprod.karavayev.converter;

import com.epam.preprod.karavayev.constant.RequestParameters;
import com.epam.preprod.karavayev.dto.UserRegistrationDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HttpRequestToUserRegDto implements Converter<HttpServletRequest, UserRegistrationDto> {

    private static final Logger LOGGER = Logger.getLogger(HttpRequestToUserRegDto.class);

    @Override
    public UserRegistrationDto convert(HttpServletRequest httpRequest) {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setFirstName(StringUtils.defaultString(httpRequest.getParameter(RequestParameters.REG_NAME)));
        dto.setLastName(StringUtils.defaultString(httpRequest.getParameter(RequestParameters.REG_LASTNAME)));
        dto.setEmail(StringUtils.defaultString(httpRequest.getParameter(RequestParameters.REG_EMAIL)));
        dto.setPassword(StringUtils.defaultString(httpRequest.getParameter(RequestParameters.REG_PASSWORD)));
        dto.setConfirmPassword(StringUtils.defaultString(httpRequest.getParameter(RequestParameters.REG_PASSWORD_CONFIRM)));
        dto.setCaptchaValue(StringUtils.defaultString(httpRequest.getParameter(RequestParameters.CAPTCHA_VALUE)));
        dto.setSubscription(httpRequest.getParameter(RequestParameters.REG_FORM_SUBSCRIPTION));
        try {
            dto.setImage(httpRequest.getPart(RequestParameters.REG_IMAGE));
        } catch (IOException | ServletException e) {
            LOGGER.error(e);
        }
        return dto;
    }
}
