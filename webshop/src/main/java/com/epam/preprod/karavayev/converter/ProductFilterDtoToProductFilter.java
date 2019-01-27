package com.epam.preprod.karavayev.converter;

import com.epam.preprod.karavayev.dto.ProductFilter;
import com.epam.preprod.karavayev.dto.ProductFilterDto;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductFilterDtoToProductFilter implements Converter<ProductFilterDto, ProductFilter> {

    @Override
    public ProductFilter convert(ProductFilterDto formData) {
        ProductFilter productFilter = new ProductFilter();
        productFilter.setName(formData.getName());
        if (Objects.nonNull(formData.getCategories())) {
            productFilter.setCategories(Arrays.stream(formData.getCategories()).map(Integer::parseInt).collect(Collectors.toList()));
        }
        if (Objects.nonNull(formData.getMakers())) {
            productFilter.setMakers(Arrays.stream(formData.getMakers()).map(Integer::parseInt).collect(Collectors.toList()));
        }
        productFilter.setCountItems(Integer.parseInt(formData.getCountItems()));
        productFilter.setPage(Integer.parseInt(formData.getPage()));
        productFilter.setSort(formData.getSort());
        productFilter.setPriceFrom(Double.parseDouble(formData.getPriceFrom()));
        productFilter.setPriceTo(Double.parseDouble(formData.getPriceTo()));
        return productFilter;
    }


}
