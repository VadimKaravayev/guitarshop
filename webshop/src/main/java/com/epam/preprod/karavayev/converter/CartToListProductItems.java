package com.epam.preprod.karavayev.converter;

import com.epam.preprod.karavayev.cart.Cart;
import com.epam.preprod.karavayev.dto.ProductItem;
import com.epam.preprod.karavayev.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartToListProductItems implements Converter<Cart, List<ProductItem>> {

    @Override
    public List<ProductItem> convert(Cart cart) {
        List<ProductItem> productItems = new ArrayList<>();
        for (Map.Entry<Product, Integer> item : cart.getCartMap().entrySet()) {
            productItems.add(new ProductItem(item.getKey(), item.getKey().getPrice(), item.getValue()));
        }
        return productItems;
    }
}
