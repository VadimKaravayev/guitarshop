package com.epam.preprod.karavayev.util;

import com.epam.preprod.karavayev.captcha.Captcha;
import com.epam.preprod.karavayev.constant.ContentType;
import com.epam.preprod.karavayev.dto.UserRegistrationDto;
import com.epam.preprod.karavayev.util.validator.UserRegistrationDtoValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import javax.servlet.http.Part;


@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationDtoValidatorTest {

    private UserRegistrationDto dto;

    private Captcha captcha;

    private UserRegistrationDtoValidator validator;

    @Mock
    Part part;

    @Before
    public void setUp() {
        validator = new UserRegistrationDtoValidator();
        dto = new UserRegistrationDto();
        dto.setFirstName("Bob");
        dto.setLastName("Bee");
        dto.setEmail("bob@bee.be");
        dto.setSubscription("yes");
        dto.setPassword("N@s@rog123");
        dto.setConfirmPassword("N@s@rog123");
        dto.setCaptchaValue("123456");
        dto.setImage(part);
        captcha = new Captcha();
        captcha.setValue("123456");
        captcha.setExpirationDate(LocalDateTime.now().plusDays(1));

        when(part.getSize()).thenReturn((long)(1024 * 1024));
        when(part.getContentType()).thenReturn(ContentType.IMAGE_JPEG);
    }

    @Test
    public void shouldReturnEmptyErrorsMapWhenAllFieldsAreCorrect() {

        assertEquals(new HashMap<String, String>(), validator.validate(dto, captcha));
    }

    @Test
    public void shouldReturnOneErrorWhenUserNameIsInvalid() {
        dto.setFirstName("bob");
        assertEquals(1, validator.validate(dto, captcha).size());
    }

    @Test
    public void shouldReturnOneErrorWhenUserLastNameIsInvalid() {
        dto.setLastName("bee");
        assertEquals(1, validator.validate(dto, captcha).size());
    }

    @Test
    public void shouldReturnOneErrorWhenEmailIsInvaldi() {
        dto.setEmail("mail");
        assertEquals(1, validator.validate(dto, captcha).size());
    }

    @Test
    public void shouldReturnOneErrorWhenPasswordIsInvalid() {
        dto.setPassword("123");
        dto.setConfirmPassword("123");
        assertEquals(1, validator.validate(dto, captcha).size());
    }

    @Test
    public void shouldReturnOneErrorWhenConfirmPasswordNotEqualToPassword() {
        dto.setConfirmPassword("123");
        assertEquals(1, validator.validate(dto, captcha).size());
    }

}