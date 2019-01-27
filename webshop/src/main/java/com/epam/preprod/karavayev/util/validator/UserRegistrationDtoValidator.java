package com.epam.preprod.karavayev.util.validator;

import com.epam.preprod.karavayev.captcha.Captcha;
import com.epam.preprod.karavayev.constant.ContentType;
import com.epam.preprod.karavayev.constant.Error;
import com.epam.preprod.karavayev.constant.RequestParameters;
import com.epam.preprod.karavayev.constant.ViewMessages;
import com.epam.preprod.karavayev.dto.UserRegistrationDto;
import com.epam.preprod.karavayev.util.WebShopPatterns;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import javax.servlet.http.Part;

public class UserRegistrationDtoValidator {

    private static final long MAX_SIZE = 1024 * 1024 * 2;

    public Map<String, String> validate(UserRegistrationDto dto, Captcha captcha)  {
        Map<String, String> errors = new HashMap<>();

        if (!WebShopPatterns.isMatchField(dto.getFirstName(), WebShopPatterns.NAME_PATTERN)) {
            errors.put(RequestParameters.REG_NAME, ViewMessages.WRONG_NAME_FORMAT);
        }

        if (!WebShopPatterns.isMatchField(dto.getLastName(), WebShopPatterns.NAME_PATTERN)) {
            errors.put(RequestParameters.REG_LASTNAME, ViewMessages.WRONG_LASTNAME_FORMAT);
        }

        if (!WebShopPatterns.isMatchField(dto.getEmail(), WebShopPatterns.EMAIL_PATTERN)) {
            errors.put(RequestParameters.REG_EMAIL, ViewMessages.WRONG_EMAIL_FORMAT);
        }

        if (!WebShopPatterns.isMatchField(dto.getPassword(), WebShopPatterns.PASSWORD_PATTERN)) {
            errors.put(RequestParameters.REG_PASSWORD, ViewMessages.WRONG_PASSWORD_FORMAT);
        }

        if (!Objects.equals(dto.getPassword(), dto.getConfirmPassword())) {
            errors.put(RequestParameters.REG_PASSWORD_CONFIRM, ViewMessages.WRONG_CONFIRMPASSWORD);
        }

        if (!checkCaptcha(captcha, dto.getCaptchaValue())) {
            errors.put(RequestParameters.CAPTCHA_VALUE, ViewMessages.WRONG_CAPTCHA);
        }

        if ((dto.getImage().getSize() > 0)) {
            if (Objects.nonNull(dto.getImage()) && !validateImage(dto.getImage())) {
                errors.put(RequestParameters.REG_IMAGE, ViewMessages.INVALID_IMAGE);
            }

            if (Objects.nonNull(dto.getImage()) && !validateImageSize(dto.getImage(), MAX_SIZE)) {
                errors.put(Error.IMAGE_MAX_SIZE, ViewMessages.MORE_THAN_MAX_SIZE);
            }
        }

        return errors;
    }

    private boolean checkCaptcha(Captcha captcha, String captchaValue) {
        if (Objects.isNull(captcha)) {
            return false;
        } else if (!Objects.equals(captcha.getValue(), captchaValue)) {
            return false;
        } else if (!captcha.getExpirationDate().isAfter(LocalDateTime.now())) {
            return false;
        }
        captcha.makeInvalid();
        return true;
    }


    private boolean validateImage(Part image) {
        String contentType = StringUtils.defaultString(image.getContentType().trim());
        String[] types = {ContentType.IMAGE_JPEG, ContentType.IMAGE_PNG, ContentType.IMAGE_BMP, ContentType.IMAGE_GIF};
        return Stream.of(types).anyMatch(contentType::equals);
    }

    private boolean validateImageSize(Part image, long targetSize) {
        return image.getSize() <= targetSize;
    }

}
























