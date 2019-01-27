package com.epam.preprod.karavayev.model.productstock;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Integer, Integer> cartMap;

    public ShoppingCart() {
        cartMap = new LinkedHashMap<>();
    }

    public void addToCart(int id, int amount) {
        if (cartMap.containsKey(id)) {
            cartMap.put(id, cartMap.get(id) + amount);
        } else {
            cartMap.put(id, amount);
        }
    }

    public void clearCart() {
        cartMap.clear();
    }

    public int amountOfAddedProduct() {
        return cartMap.size();
    }

    public Map<Integer, Integer> getCartMap() {
        return cartMap;
    }

    public String getTotalPrice(Stock stock) {
        return getCartMap().entrySet()
                .stream()
                .map(e-> stock.getProductById(e.getKey())
                        .getPrice()
                        .multiply(new BigDecimal(e.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add).toString();
    }
}
