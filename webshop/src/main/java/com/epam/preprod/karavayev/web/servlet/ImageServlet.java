package com.epam.preprod.karavayev.web.servlet;

import com.epam.preprod.karavayev.constant.Attributes;
import com.epam.preprod.karavayev.constant.ContentType;
import com.epam.preprod.karavayev.constant.ContextParam;
import com.epam.preprod.karavayev.constant.FileExtension;
import com.epam.preprod.karavayev.constant.Servlets;
import com.epam.preprod.karavayev.entity.User;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/" + Servlets.IMAGE)
public class ImageServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ImageServlet.class);
    private static final String DEFAULT_PATH = "C:\\Users\\Vadym_Karavayev\\Pre_Prod_java_q1q2_2018\\webshop\\src\\main\\webapp\\images\\default_avatar.png";
    //private static final String DEFAULT_PATH = "/Users/vadimkaravayev/Documents/PreprodRepository/Pre_Prod_java_q1q2_2018/webshop/src/main/webapp/images/default_avatar.png";

    private String rootImgPath;

    @Override
    public void init() {
        rootImgPath = getServletContext().getInitParameter(ContextParam.AVATAR_PATH);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Attributes.USER_ENTITY);
        String imgPath = rootImgPath + File.separator + user.getId() + FileExtension.JPG_EXTENSION;

        response.setContentType(ContentType.IMAGE_JPEG);
        try (OutputStream outputStream = response.getOutputStream()) {
            File file = new File(imgPath);
            if (!file.exists()) {
                LOGGER.debug(DEFAULT_PATH);
                file = new File(DEFAULT_PATH);
            }
            ImageIO.write(ImageIO.read(file), "png", outputStream);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}

