package com.epam.preprod.karavayev.converter;

import com.epam.preprod.karavayev.db.Join;
import com.epam.preprod.karavayev.db.SqlBuilder;
import com.epam.preprod.karavayev.dto.ProductFilter;

import java.util.List;
import java.util.Objects;

public class ProductFilterToCountQuery implements Converter<ProductFilter, String> {

    @Override
    public String convert(ProductFilter productFilter) {
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.select("count(*)")
                .from("product")
                .join(Join.INNER, "maker", "product.maker_id = maker.id")
                .join(Join.INNER, "category", "product.category_id = category.id")
                .where("price BETWEEN " + productFilter.getPriceFrom() + " AND " + productFilter.getPriceTo());
        List<Integer> ids = productFilter.getCategories();
        if (Objects.nonNull(ids) && !ids.isEmpty()) {
            sqlBuilder.and("")
                    .in("product.category_id", ids.stream().map(String::valueOf).toArray(String[]::new));
        }
        ids = productFilter.getMakers();
        if (Objects.nonNull(ids) && !ids.isEmpty()) {
            sqlBuilder.and("")
                    .in("product.maker_id", ids.stream().map(String::valueOf).toArray(String[]::new));
        }
        if (Objects.nonNull(productFilter.getName())) {
            sqlBuilder.and("product.name like ?");
        }
        return sqlBuilder.toQuery();
    }
}
