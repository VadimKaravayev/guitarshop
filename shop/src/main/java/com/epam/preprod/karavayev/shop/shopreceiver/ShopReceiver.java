package com.epam.preprod.karavayev.shop.shopreceiver;
import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import com.epam.preprod.karavayev.model.productstock.*;
import com.epam.preprod.karavayev.shop.utils.InputProcessor;
import com.epam.preprod.karavayev.shop.utils.UserInterface;

import java.text.ParseException;
import java.util.*;

public class ShopReceiver {

    private Stock stock;
    private ShoppingCart shoppingCart;
    private ShoppingCartHistory shoppingCartHistory;
    private OrderDatabase orderDatabase;
    private UserInterface console;
    private InputProcessor processor;

    public ShopReceiver(Stock stock, ShoppingCart shoppingCart, ShoppingCartHistory shoppingCartHistory,
                        OrderDatabase orderDatabase, UserInterface userInterface, InputProcessor processor) {
        this.stock = stock;
        this.shoppingCart = shoppingCart;
        this.shoppingCartHistory = shoppingCartHistory;
        this.orderDatabase = orderDatabase;
        this.console = userInterface;
        this.processor = processor;
    }

    public void showAllProducts() {
        console.promptMessage("You are in products\n");
        Map<Integer, StringInstrument> productsInStock = stock.getProductsInStock();
        if (productsInStock.isEmpty()) {
            console.promptMessage("No products so far");
        } else {
            console.printAllProducts(productsInStock);
        }
    }

    public void addToCart() {
        console.promptMessage("Cancel(X)");
        console.promptMessage("Type in product id");
        String input = processor.checkInput(console.takeInput());

        if (input.equalsIgnoreCase(InputProcessor.WRONG_INPUT)) {
            console.promptMessage(input);
            console.promptMessage("Try again");
            return;
        }
        if (processor.isInputExit(input)) {
            console.promptMessage("You left \"add to cart\"");
            return;
        }
        int id = Integer.parseInt(input);

        console.promptMessage("Type in product amount");
        input = processor.checkInput(console.takeInput());

        if (input.equalsIgnoreCase(InputProcessor.WRONG_INPUT)) {
            console.promptMessage(input);
            console.promptMessage("Try again");
            return;
        }
        if (processor.isInputExit(input)) {
            console.promptMessage("You left \"add to cart\"");
            return;
        }
        int amount = Integer.parseInt(input);

        if (stock.getProductsInStock().containsKey(id)) {
            shoppingCart.addToCart(id, amount);
            shoppingCartHistory.addToCartHistory(id, amount);
            console.promptMessage("You added to cart:");
            console.promptMessage(stock.getProductById(id).getName() + ", amount: " + amount);
        } else {
            console.promptMessage("Error! Try again.");
        }
        console.promptMessage("You left \"add to cart\"");
    }

    public void showCart() {
        console.promptMessage("You are in cart");
        console.showShoppingCartContents(shoppingCart, stock);
        if (shoppingCart.amountOfAddedProduct() != 0) {
            String input = processor.checkInput(console.takeInput(), "Y", "N");
            if (input.equalsIgnoreCase("Y")) {
                checkoutOrder();
            } else if (input.equalsIgnoreCase("N")) {
                return;
            } else {
                console.promptMessage(input);
                console.promptMessage("Try again");
            }
            console.promptMessage("You left cart");
        }
    }

    private void checkoutOrder() {
        console.promptMessage("Enter date in format dd-MM-yyyy HH-mm");
        String input = processor.checkDateInput(console.takeInput());
        Date keyDate = null;
        if (input.equalsIgnoreCase(InputProcessor.WRONG_DATE_FORMAT)) {
            console.promptMessage(input);
            console.promptMessage("Try again");
            return;
        }
        if (processor.isInputExit(input)) {
            return;
        }
        try {
            keyDate = processor.convertStringToDate(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Map<Integer, Integer> orderMap = new LinkedHashMap<>(shoppingCart.getCartMap());
        orderDatabase.addToOrderDatabase(keyDate, new Order(orderMap));
        shoppingCart.clearCart();
        console.promptMessage("Thank you for your order.");
    }

    public void viewRecentlyAddedToCartProducts() {
        console.promptMessage("You are in cart history");
        ShoppingCartHistory.Item[] items = shoppingCartHistory.getCartHistoryMap()
                .values()
                .toArray(new ShoppingCartHistory.Item[0]);
        console.drawStars();
        if (items.length == 0) {
            console.promptMessage("History is empty");
        } else {
            for (ShoppingCartHistory.Item item: items) {
                StringInstrument instrument = stock.getProductById(item.getProductId());
                console.printCartItem(instrument.getName(), instrument.getPrice().toString(), item.getAmount());
            }
        }
        console.drawStars();
    }

    private void showByPeriod(String input) {
        console.promptMessage("Enter date one (dd-MM-yyyy HH-mm)");
        input = processor.checkDateInput(console.takeInput());
        if (processor.isInputExit(input)) {
            console.promptMessage("You left orders");
            return;
        }
        if (input.equalsIgnoreCase(InputProcessor.WRONG_DATE_FORMAT)) {
            console.promptMessage(input);
            return;
        }
        Date date1 = null;
        try {
            date1 = processor.convertStringToDate(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        console.promptMessage("Enter date two (dd-MM-yyyy HH-mm)");
        input = processor.checkDateInput(console.takeInput());
        if (processor.isInputExit(input)) {
            console.promptMessage("You left orders");
            return;
        }
        if (input.equalsIgnoreCase(InputProcessor.WRONG_DATE_FORMAT)) {
            console.promptMessage(input);
            return;
        }
        Date date2 = null;
        try {
            date2 = processor.convertStringToDate(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Map<Date, Order> ordersByPeriod = getOrdersByPeriod(date1, date2);
        if (ordersByPeriod.size() == 0) {
            console.promptMessage("No orders found.");
        } else {
            ordersByPeriod.forEach((k,v)-> {
                console.promptMessage(processor.getStringFromDate(k));
                console.printOrder(v, stock);
            });
        }
    }

    private void showByClosest(String input) {
        console.promptMessage("Enter date (dd-MM-yyyy HH-mm)");
        input = processor.checkDateInput(console.takeInput());
        if (processor.isInputExit(input)) {
            console.promptMessage("You left orders");
            return;
        }
        if (input.equalsIgnoreCase(InputProcessor.WRONG_DATE_FORMAT)) {
            console.promptMessage(input);
            return;
        }
        Date date = null;
        try {
            date = processor.convertStringToDate(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Optional<Order> orderByClosestDate = getOrderByClosestDate(date);
        if (orderByClosestDate.isPresent()) {
            console.promptMessage("The closest date: " +
                    processor.getStringFromDate(orderByClosestDate.get().getOrderDate()));
            console.printOrder(orderByClosestDate.get(), stock);
        } else {
            console.promptMessage("No order has been placed yet.");
        }
    }

    public void showOrder() {
        console.promptMessage("You are in orders");
        console.promptMessage("Orders by period(BP) || Orders by closest date(BC) Cancel(X)");
        String input = processor.checkInput(console.takeInput(), "BP", "BC", "X");
        if (processor.isInputExit(input)) {
            console.promptMessage("You left orders");
            return;
        }
        if (input.equalsIgnoreCase("BP")) {
            showByPeriod(input);

        } else if (input.equalsIgnoreCase("BC")) {
            showByClosest(input);
        }
        showOrder();
    }

    Optional<Order> getOrderByClosestDate(Date date) {
        TreeMap<Date, Order> orders = orderDatabase.getOrders();
        if (orders.isEmpty()) {
            return Optional.empty();
        }
        Date closestDate;
        Date d1 = orders.floorKey(date);
        Date d2 = orders.ceilingKey(date);

        if (d1 == null) {
            closestDate = d2;
        } else if (d2 == null) {
            closestDate = d1;
        } else {
            int dif1 = processor.findTimeDifference(date, d1);
            int dif2 = processor.findTimeDifference(date, d2);
            closestDate = dif1 >= dif2 ? d2 : d1;
        }
        return Optional.of(orders.get(closestDate));
    }

    Map<Date, Order> getOrdersByPeriod(Date date1, Date date2) {
        TreeMap<Date, Order> orders = orderDatabase.getOrders();
        return orders.subMap(date1, date2);
    }
}