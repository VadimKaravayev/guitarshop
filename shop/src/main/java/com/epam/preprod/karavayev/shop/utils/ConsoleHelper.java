package com.epam.preprod.karavayev.shop.utils;

import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import com.epam.preprod.karavayev.model.productstock.Order;
import com.epam.preprod.karavayev.model.productstock.ShoppingCart;
import com.epam.preprod.karavayev.model.productstock.Stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class ConsoleHelper implements UserInterface {

    @Override
    public void promptMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String takeInput() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    @Override
    public int takeInteger() {
        return Integer.parseInt(takeInput());
    }

    @Override
    public void printAllProducts(Map<Integer, StringInstrument> products) {
        products.forEach((k, v)-> {
            promptMessage(v.toString());
        });
    }

    @Override
    public void drawStars() {
        System.out.println("***********************************************************");
    }

    @Override
    public void showShoppingCartContents(ShoppingCart shoppingCart, Stock stock) {
        if (shoppingCart.amountOfAddedProduct() == 0) {
            drawStars();
            promptMessage("Cart is empty");
            drawStars();
        } else {
            drawStars();
            shoppingCart.getCartMap().forEach((k, v)-> {
                StringInstrument temp = stock.getProductById(k);
                printCartItem(temp.getName(), temp.getPrice().toString(), v);
            });
            drawStars();
            promptMessage("Total price: " + shoppingCart.getTotalPrice(stock));
            promptMessage("Check out(Y/N)");
        }
    }


    @Override
    public void printCartItem(String name, String price, int amount) {
        System.out.printf("name: %s, price per item: %s, ordered amount: %d\n",
                name, price, amount);
    }

    @Override
    public void printOrder(Order order, Stock stock) {
        order.getOrder().forEach((k,v)-> {
            StringInstrument temp = stock.getProductById(k);
            System.out.printf("* id: %d * name: %s * price: %s, amount: %d\n", k, temp.getName(), temp.getPrice(), v);
        });
    }

    @Override
    public void showMainMenu() {
        promptMessage("THE COOLEST MUSIC SHOP\n");
        promptMessage("Products(1) || Cart(2) || Add to cart(3) || Cart history(4)|| Orders(5) || Add product(6) || Exit(0)\n");
        promptMessage("Enter commands to navigate");
    }
}
