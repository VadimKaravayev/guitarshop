package com.epam.preprod.karavayev.cart;

import com.epam.preprod.karavayev.entity.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> cartMap;

    public Cart() {
        cartMap = new LinkedHashMap<>();
    }

    public void addToCart(Product product) {
        cartMap.put(product, 1);
    }

    public void removeFromCart(Product product) {
        cartMap.remove(product);
    }

    public void removeFromCart(int id) {
        cartMap.entrySet().removeIf(entry -> entry.getKey().getId() == id);
    }

    public void setItemCount(int id, int count) {
        if (contains(id)) {
            Product product = cartMap.entrySet().stream()
                    .filter(entry -> entry.getKey().getId() == id)
                    .findFirst().get().getKey();
            cartMap.replace(product, count);
        }
    }

    public Map<Product, Integer> getCartMap() {
        return cartMap;
    }

    public int getCount() {
        return cartMap.values().stream().mapToInt(value -> value).sum();
    }

    public double getTotalSum() {
        return cartMap.entrySet().stream().mapToDouble(e -> e.getKey().getPrice() * e.getValue()).sum();
    }

    public boolean contains(int id) {
        return cartMap.keySet().stream().anyMatch(product -> product.getId() == id);
    }
}
