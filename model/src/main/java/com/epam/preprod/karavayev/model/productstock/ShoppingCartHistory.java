package com.epam.preprod.karavayev.model.productstock;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCartHistory {
    private Map<Integer, Item> cartHistoryMap;
    private int count;
    private static final int MAX_COUNT = 5;

    public ShoppingCartHistory() {
        cartHistoryMap = new LinkedHashMap<Integer, Item>(MAX_COUNT + 1, .075F, false) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Item> eldest) {
                return size() > MAX_COUNT;
            }
        };

    }
    public static class Item {
        int productId;
        int amount;

        public Item(int productId, int amount) {
            this.productId = productId;
            this.amount = amount;
        }

        public int getProductId() {
            return productId;
        }

        public int getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "productId=" + productId +
                    ", amount=" + amount +
                    '}';
        }
    }
    public void addToCartHistory(int id, int amount) {
        cartHistoryMap.put(count++, new Item(id, amount));
    }
    public Map<Integer, Item> getCartHistoryMap() {
        return cartHistoryMap;
    }
}
