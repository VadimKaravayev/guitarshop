package com.epam.preprod.karavayev.web.servlet;

import com.epam.preprod.karavayev.constant.Attributes;
import com.epam.preprod.karavayev.constant.Servlets;
import com.epam.preprod.karavayev.constant.UrlMapping;
import com.epam.preprod.karavayev.converter.ProductFilterDtoToProductFilter;
import com.epam.preprod.karavayev.converter.RequestToProductFilterDto;
import com.epam.preprod.karavayev.dto.ProductFilter;
import com.epam.preprod.karavayev.dto.ProductFilterDto;
import com.epam.preprod.karavayev.entity.Product;
import com.epam.preprod.karavayev.service.CategoryService;
import com.epam.preprod.karavayev.service.MakerService;
import com.epam.preprod.karavayev.service.ProductService;
import com.epam.preprod.karavayev.util.validator.ProductFilterDtoValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/" + Servlets.PRODUCT)
public class ProductServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ProductServlet.class);

    private ProductService productService;
    private MakerService makerService;
    private CategoryService categoryService;
    private ProductFilterDtoValidator validator;
    private RequestToProductFilterDto requestToProductFilterDto;
    private ProductFilterDtoToProductFilter filterDtoToProductFilter;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductFilterDto dto = requestToProductFilterDto.convert(request);
        validator.validate(dto);
        ProductFilter productFilter = filterDtoToProductFilter.convert(dto);

        List<Product> products = productService.getProducts(productFilter);

        int countProducts = productService.getCountProducts(productFilter);
        int pageCount = pageCount(countProducts, productFilter.getCountItems());
        String requestUrl = getUrl(request);
        request.setAttribute(Attributes.PRODUCT_FILTER_DTO, dto);
        request.setAttribute(Attributes.PRODUCTS, products);
        request.setAttribute(Attributes.COUNT_PRODUCTS, countProducts);
        request.setAttribute(Attributes.MAKERS, makerService.getAllMakers());
        request.setAttribute(Attributes.CATEGORIES, categoryService.getAllCategories());
        request.setAttribute(Attributes.PRODUCT_PAGE_COUNT, pageCount);
        request.setAttribute(Attributes.PRODUCT_FILTER, productFilter);
        request.setAttribute(Attributes.PRODUCT_REQUEST_URL, requestUrl);

        request.getRequestDispatcher(UrlMapping.PRODUCT_JSP).forward(request, response);

    }

    @Override
    public void init() {
        validator = new ProductFilterDtoValidator();
        requestToProductFilterDto = new RequestToProductFilterDto();
        filterDtoToProductFilter = new ProductFilterDtoToProductFilter();
        categoryService = (CategoryService) getServletContext().getAttribute(Attributes.CONTEXT_CATEGORY_SERVICE);
        makerService = (MakerService) getServletContext().getAttribute(Attributes.CONTEXT_MAKER_SERVICE);
        productService = (ProductService) getServletContext().getAttribute(Attributes.CONTEXT_PRODUCT_SERVICE);
    }

    private int pageCount(int productCount, int pageItems) {
        return productCount / pageItems + (productCount % pageItems > 0 ? 1 : 0);
    }

    private String getUrl(HttpServletRequest request) {
        String url = (Objects.nonNull(request.getQueryString())) ?
               String.join("", request.getRequestURL(), "?" , request.getQueryString()):
               request.getRequestURL().toString();

        return refreshUrl(url);
    }

    private String refreshUrl(String url) {
        Matcher matcher = Pattern.compile("([\\?,&]?page=\\d+&?)").matcher(url);
        if (matcher.find()) {
            String result = matcher.group(1);
            LOGGER.debug(result);
            if (result.contains("&")) {
                result = result.replaceFirst("\\?", "");
            }
            return url.replace(result, "");
        }
        return url;
    }
}
