package com.epam.preprod.karavayev.util.validator;

import com.epam.preprod.karavayev.dto.ProductFilterDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ProductFilterDtoValidator {

    private static final Pattern INTEGER = Pattern.compile("^\\d+$");
    private static final Pattern SORT = Pattern.compile("^(price\\+ASC)|(price\\+DESC)|(name\\+ASC)|(name\\+DESC)$");
    private static final List<Integer> PAGES = new ArrayList<>(Arrays.asList(6, 9, 12, 15, 18));
    private static final Integer DEFAULT_COUNT_ITEMS = PAGES.get(0);
    private static final int MAX_PRICE = 100_000;

    public void validate(ProductFilterDto dto) {

        dto.setCategories(pickupNumerics(dto.getCategories()));
        dto.setMakers(pickupNumerics(dto.getMakers()));

        String countItems = dto.getCountItems();
        if (INTEGER.matcher(StringUtils.defaultString(countItems)).matches()) {
            if (!PAGES.contains(Integer.parseInt(countItems))) {
                dto.setCountItems(String.valueOf(DEFAULT_COUNT_ITEMS));
            }
        } else {
            dto.setCountItems(String.valueOf(DEFAULT_COUNT_ITEMS));
        }

        if (!isPrice(dto.getPriceFrom())) {
            dto.setPriceFrom(String.valueOf(0));
        }

        if (!isPrice(dto.getPriceTo())) {
            dto.setPriceTo(String.valueOf(MAX_PRICE));
        }

        String page = dto.getPage();
        if (!(INTEGER.matcher(StringUtils.defaultString(page)).matches() && Integer.parseInt(page) > 0)) {
            dto.setPage(String.valueOf(1));
        }

        String sort = dto.getSort();
        if (Objects.nonNull(sort) && !SORT.matcher(sort).matches()) {
            dto.setSort(null);
        }
    }

    private String[] pickupNumerics(String[] categories) {
        String[] result;
        if (Objects.nonNull(categories)) {
            result = Stream.of(categories).filter(StringUtils::isNumeric).toArray(String[]::new);
            return result.length == 0 ? null : result;
        }
        return null;
    }

    private boolean isPrice(String param) {
        return StringUtils.isNumeric(StringUtils.defaultString(param)) && Double.parseDouble(param) > 0;
    }

}
