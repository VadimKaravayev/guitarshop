package com.epam.preprod.karavayev.converter;

import com.epam.preprod.karavayev.constant.RequestParameters;
import com.epam.preprod.karavayev.dto.ProductFilterDto;

import javax.servlet.http.HttpServletRequest;

public class RequestToProductFilterDto implements Converter<HttpServletRequest, ProductFilterDto> {

    @Override
    public ProductFilterDto convert(HttpServletRequest request) {
        ProductFilterDto dto = new ProductFilterDto();
        dto.setName(request.getParameter(RequestParameters.PRODUCT_FILTER_NAME));
        dto.setSort(request.getParameter(RequestParameters.PRODUCT_FILTER_SORT));
        dto.setCategories(request.getParameterValues(RequestParameters.PRODUCT_FILTER_CATEGORY));
        dto.setCountItems(request.getParameter(RequestParameters.PRODUCT_FILTER_COUNT_ITEMS));
        dto.setMakers(request.getParameterValues(RequestParameters.PRODUCT_FILTER_MAKER));
        dto.setPage(request.getParameter(RequestParameters.PRODUCT_FILTER_PAGE));  // take from pagination
        dto.setPriceFrom(request.getParameter(RequestParameters.PRODUCT_FILTER_PRICE_FROM));
        dto.setPriceTo(request.getParameter(RequestParameters.PRODUCT_FILTER_PRICE_TO));
        return dto;
    }
}
