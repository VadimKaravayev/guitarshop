package com.epam.preprod.karavayev.web.servlet;

import com.epam.preprod.karavayev.captcha.Captcha;
import com.epam.preprod.karavayev.captcha.CaptchaGenerator;
import com.epam.preprod.karavayev.captcha.store.method.impl.AbstractStoreMethod;
import com.epam.preprod.karavayev.constant.Attributes;
import com.epam.preprod.karavayev.constant.ContentType;
import com.epam.preprod.karavayev.constant.Servlets;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/" + Servlets.CAPTCHA)
public class CaptchaServlet extends HttpServlet {

    private CaptchaGenerator captchaGenerator;
    private AbstractStoreMethod storeMethod;

    @Override
    public void init() {
        storeMethod = (AbstractStoreMethod) getServletContext().getAttribute(Attributes.CAPTCHA_STORE_METHOD);
        captchaGenerator = (CaptchaGenerator) getServletContext().getAttribute(Attributes.CAPTCHA_GENERATOR);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Captcha captcha = captchaGenerator.generateCaptcha();
        response.setContentType(ContentType.IMAGE_PNG);
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(captcha.getImage(), "png", outputStream);
        captcha.setImage(null);
        String uuid = request.getParameter(Attributes.CAPTCHA_ID);
        if (StringUtils.isNoneBlank(uuid)){
            storeMethod.store(request, response, captcha, uuid);
        }
    }
}
