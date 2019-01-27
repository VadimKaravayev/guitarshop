package com.epam.preprod.karavayev.web.servlet;

import com.epam.preprod.karavayev.captcha.store.method.impl.AbstractStoreMethod;
import com.epam.preprod.karavayev.constant.*;
import com.epam.preprod.karavayev.converter.HttpRequestToUserRegDto;
import com.epam.preprod.karavayev.converter.UserRegDtoToUser;
import com.epam.preprod.karavayev.dto.UserRegistrationDto;
import com.epam.preprod.karavayev.entity.User;
import com.epam.preprod.karavayev.exception.UserAlreadyExistsException;
import com.epam.preprod.karavayev.service.UserService;
import com.epam.preprod.karavayev.util.ImageManager;
import com.epam.preprod.karavayev.util.validator.UserRegistrationDtoValidator;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/" + Servlets.REGISTER)
@MultipartConfig
public class RegistrationServlet extends HttpServlet {

    private UserRegistrationDtoValidator validator;
    private UserService userService;
    private HttpRequestToUserRegDto converterToDto;
    private UserRegDtoToUser converterToUser;
    private AbstractStoreMethod storeMethod;
    private String imgPath;

    @Override
    public void init() {
        imgPath = getServletContext().getInitParameter(ContextParam.AVATAR_PATH);
        converterToDto = new HttpRequestToUserRegDto();
        converterToUser = new UserRegDtoToUser();
        validator = new UserRegistrationDtoValidator();
        userService = (UserService) getServletContext().getAttribute(Attributes.CONTEXT_USER_SERVICE);
        storeMethod = (AbstractStoreMethod) getServletContext().getAttribute(Attributes.CAPTCHA_STORE_METHOD);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Attributes.USER_ENTITY);
        if (Objects.nonNull(user)) {
            response.sendRedirect(Servlets.HOME);
            return;
        }

        UserRegistrationDto userRegistrationDto = converterToDto.convert(request);
        Map<String, String> errors = validator.validate(userRegistrationDto, storeMethod.getCaptcha(request));

        if (errors.isEmpty()) {
            user = converterToUser.convert(userRegistrationDto);
            try {
                userService.register(user);
                session.setAttribute(Attributes.USER_ENTITY, user);
                if (Objects.nonNull(userRegistrationDto.getImage())) {
                    ImageManager.saveImage(imgPath + File.separator + user.getId() + FileExtension.JPG_EXTENSION, userRegistrationDto.getImage());
                }
                response.sendRedirect(Servlets.HOME);
                return;
            } catch (UserAlreadyExistsException e) {
                errors.put(RequestParameters.REG_EMAIL, ViewMessages.EMAIL_ALREADY_EXISTS);
            }
        }
        session.setAttribute(Attributes.USER_REG_DTO, userRegistrationDto);
        session.setAttribute(Attributes.USER_REG_ERRORS, errors);
        response.sendRedirect(Servlets.REGISTER);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = UUID.randomUUID().toString();
        request.setAttribute(Attributes.CAPTCHA_ID, uuid);

        HttpSession session = request.getSession();
        request.setAttribute(Attributes.USER_REG_DTO, session.getAttribute(Attributes.USER_REG_DTO));
        session.removeAttribute(Attributes.USER_REG_DTO);
        request.setAttribute(Attributes.USER_REG_ERRORS, session.getAttribute(Attributes.USER_REG_ERRORS));
        session.removeAttribute(Attributes.USER_REG_ERRORS);
        request.getRequestDispatcher(UrlMapping.SIGNUP_JSP).forward(request, response);
    }

}
