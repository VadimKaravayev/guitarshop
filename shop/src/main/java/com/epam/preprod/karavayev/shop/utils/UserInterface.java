package com.epam.preprod.karavayev.shop.utils;

import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import com.epam.preprod.karavayev.model.productstock.Order;
import com.epam.preprod.karavayev.model.productstock.ShoppingCart;
import com.epam.preprod.karavayev.model.productstock.Stock;

import java.util.Map;

public interface UserInterface {

    void promptMessage(String message);

    String takeInput();

    int takeInteger();

    void printAllProducts(Map<Integer, StringInstrument> products);

    void drawStars();

    void printCartItem(String name, String price, int amount);

    void showMainMenu();

    void showShoppingCartContents(ShoppingCart shoppingCart, Stock stock);

    void printOrder(Order order, Stock stock);
}
