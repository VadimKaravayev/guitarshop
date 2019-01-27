package com.epam.preprod.karavayev.web.listener;

import com.epam.preprod.karavayev.captcha.Captcha;
import com.epam.preprod.karavayev.captcha.CaptchaCleaner;
import com.epam.preprod.karavayev.captcha.CaptchaGenerator;
import com.epam.preprod.karavayev.captcha.store.method.impl.AbstractStoreMethod;
import com.epam.preprod.karavayev.captcha.store.method.impl.CookieCaptchaStoreMethod;
import com.epam.preprod.karavayev.captcha.store.method.impl.HiddenFieldCaptchaStoreMethod;
import com.epam.preprod.karavayev.captcha.store.method.impl.SessionCaptchaStoreMethod;
import com.epam.preprod.karavayev.constant.Attributes;
import com.epam.preprod.karavayev.constant.ContextParam;
import com.epam.preprod.karavayev.db.ConnectionManager;
import com.epam.preprod.karavayev.db.TransactionManager;
import com.epam.preprod.karavayev.db.dao.*;
import com.epam.preprod.karavayev.db.dao.mysql.*;
import com.epam.preprod.karavayev.entity.Product;
import com.epam.preprod.karavayev.service.*;
import com.epam.preprod.karavayev.service.impl.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.File;
import java.util.Map;
import java.util.concurrent.*;

@WebListener
public class AppContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(AppContextListener.class);
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        initLog4j(servletContext);

        ConnectionManager connectionManager = new ConnectionManager(getDataSource());
        TransactionManager transactionManager = new TransactionManager(connectionManager);

        UserDao userDao = new MysqlUserDaoImpl(connectionManager);
        CategoryDao categoryDao = new MysqlCategoryDaoImpl(connectionManager);
        MakerDao makerDao = new MysqlMakerDaoImpl(connectionManager);
        ProductDao productDao = new MysqlProductDaoImpl(connectionManager);
        OrderDao orderDao = new MysqlOrderDaoImpl(connectionManager);

        UserService userService = new UserServiceImpl(userDao, transactionManager);
        CategoryService categoryService = new CategoryServiceImpl(categoryDao, transactionManager);
        MakerService makerService = new MakerServiceImpl(makerDao, transactionManager);
        ProductService productService = new ProductServiceImpl(productDao, transactionManager);
        OrderService orderService = new OrderServiceImpl(orderDao, transactionManager);


        Map<String, Captcha> captchaMap = new ConcurrentHashMap<>();
        servletContext.setAttribute(Attributes.CAPTCHA_MAP, captchaMap);

        long timeSleep = Long.parseLong(servletContext.getInitParameter(ContextParam.CAPTCHA_EXPIRATION_TIME));
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new CaptchaCleaner(captchaMap), timeSleep, timeSleep, TimeUnit.SECONDS);

        CaptchaGenerator captchaGenerator = new CaptchaGenerator(timeSleep);
        servletContext.setAttribute(Attributes.CAPTCHA_GENERATOR, captchaGenerator);
        servletContext.setAttribute(Attributes.CAPTCHA_STORE_METHOD, getCaptchaStoreMethod(servletContext, captchaMap, (int) timeSleep));
        servletContext.setAttribute(Attributes.CONTEXT_USER_SERVICE, userService);
        servletContext.setAttribute(Attributes.CONTEXT_PRODUCT_SERVICE, productService);
        servletContext.setAttribute(Attributes.CONTEXT_CATEGORY_SERVICE, categoryService);
        servletContext.setAttribute(Attributes.CONTEXT_MAKER_SERVICE, makerService);
        servletContext.setAttribute(Attributes.CONTEXT_ORDER_SERVICE, orderService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        scheduledExecutorService.shutdownNow();
    }

    private AbstractStoreMethod getCaptchaStoreMethod(ServletContext servletContext, Map<String, Captcha> captchaMap, int timeout) {
        String captchaStoreMethod = servletContext.getInitParameter(ContextParam.CAPTCHA_STORE_METHOD);
        servletContext.setAttribute(Attributes.STORE_METHOD_NAME, captchaStoreMethod);
        switch (captchaStoreMethod) {
            case ContextParam.SESSION:
                return new SessionCaptchaStoreMethod(captchaMap, timeout);
            case ContextParam.HIDDEN_FIELD:
                return new HiddenFieldCaptchaStoreMethod(captchaMap, timeout);
            case ContextParam.COOKIE:
                return new CookieCaptchaStoreMethod(captchaMap, timeout);
            default:
                servletContext.setAttribute(Attributes.STORE_METHOD_NAME, ContextParam.SESSION);
                return new SessionCaptchaStoreMethod(captchaMap, timeout);
        }
    }

    private DataSource getDataSource() {
        DataSource dataSource;
        try {
            Context context = new InitialContext();
            Context envContext = (Context) context.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/web_shop_db");
        } catch (NamingException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return dataSource;
    }

    private void initLog4j(ServletContext sc) {
        System.out.println("Log4JInitServlet is initializing log4j");
        String log4jLocation = sc.getInitParameter("log4j-properties-location");
        System.out.println(log4jLocation);

        if (log4jLocation == null) {
            System.err.println("*** No log4j-properties-location init param, so initializing log4j with BasicConfigurator");
            BasicConfigurator.configure();
        } else {
            String log4jProp = sc.getRealPath("WEB-INF/log4j.properties");
            File properties = new File(log4jProp);
            if (properties.exists()) {
                System.out.println("Initializing log4j with: " + log4jProp);
                PropertyConfigurator.configure(log4jProp);
            } else {
                System.err.println("*** " + log4jProp + " file not found, so initializing log4j with BasicConfigurator");
                BasicConfigurator.configure();
            }
        }
    }
}
