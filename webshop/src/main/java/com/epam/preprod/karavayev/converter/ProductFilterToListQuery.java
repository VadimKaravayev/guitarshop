package com.epam.preprod.karavayev.converter;

import com.epam.preprod.karavayev.db.Join;
import com.epam.preprod.karavayev.db.SqlBuilder;
import com.epam.preprod.karavayev.dto.ProductFilter;

import java.util.List;
import java.util.Objects;

public class ProductFilterToListQuery implements Converter<ProductFilter, String> {

    @Override
    public String convert(ProductFilter filter) {
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.select("product.*, maker.name as maker_name, category.name as category_name")
                .from("product")
                .join(Join.INNER, "maker", "product.maker_id = maker.id")
                .join(Join.INNER, "category", "product.category_id = category.id")
                .where("price BETWEEN " + filter.getPriceFrom() + " AND " + filter.getPriceTo());
        List<Integer> ids = filter.getCategories();
        if (Objects.nonNull(ids) && !ids.isEmpty()) {
            sqlBuilder.and("")
                    .in("product.category_id", ids.stream().map(String::valueOf).toArray(String[]::new));
        }
        ids = filter.getMakers();
        if (Objects.nonNull(ids) && !ids.isEmpty()) {
            sqlBuilder.and("")
                    .in("product.maker_id", ids.stream().map(String::valueOf).toArray(String[]::new));
        }
        if (Objects.nonNull(filter.getName())) {
            sqlBuilder.and("product.name like ?");
        }
        if (Objects.nonNull(filter.getSort())) {
            sqlBuilder.orderBy(filter.getSort().replace("+", " "));
        }
        String query = sqlBuilder.limit((filter.getPage() - 1) * filter.getCountItems(), filter.getCountItems()).toQuery();
        return query;
    }
}
