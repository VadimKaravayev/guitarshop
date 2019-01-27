package com.epam.preprod.karavayev.shop.client;

import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import com.epam.preprod.karavayev.model.productstock.OrderDatabase;
import com.epam.preprod.karavayev.model.productstock.ShoppingCart;
import com.epam.preprod.karavayev.model.productstock.ShoppingCartHistory;
import com.epam.preprod.karavayev.model.productstock.Stock;
import com.epam.preprod.karavayev.shop.command.AddProductCommand;
import com.epam.preprod.karavayev.shop.command.AddToCartCommand;
import com.epam.preprod.karavayev.shop.command.Command;
import com.epam.preprod.karavayev.shop.command.ShowAllProductsCommand;
import com.epam.preprod.karavayev.shop.command.ShowCartCommand;
import com.epam.preprod.karavayev.shop.command.ShowCartHistoryCommand;
import com.epam.preprod.karavayev.shop.command.ShowOrderCommand;
import com.epam.preprod.karavayev.shop.command_invoker.ShopCommandInvoker;
import com.epam.preprod.karavayev.shop.inputproduct.director.InputStrategyDirector;
import com.epam.preprod.karavayev.shop.inputproduct.director.ManualInputDirector;
import com.epam.preprod.karavayev.shop.inputproduct.director.ManualReflexiveInputDirector;
import com.epam.preprod.karavayev.shop.inputproduct.director.RandomInputDirector;
import com.epam.preprod.karavayev.shop.inputproduct.director.RandomReflexiveInputDirector;
import com.epam.preprod.karavayev.shop.serializablelist.ListSaverReader;
import com.epam.preprod.karavayev.shop.shopreceiver.ShopReceiver;
import com.epam.preprod.karavayev.shop.task9.client.ServerApp;
import com.epam.preprod.karavayev.shop.task9.factory.HttpRequestHandlerFactory;
import com.epam.preprod.karavayev.shop.task9.factory.TCPRequestHandlerFactory;
import com.epam.preprod.karavayev.shop.task9.handler.RequestHandler;
import com.epam.preprod.karavayev.shop.task9.handler.http.HttpCountImpl;
import com.epam.preprod.karavayev.shop.task9.handler.http.HttpGetItemImpl;
import com.epam.preprod.karavayev.shop.task9.handler.tcp.TCPCountImpl;
import com.epam.preprod.karavayev.shop.task9.handler.tcp.TCPItemImpl;
import com.epam.preprod.karavayev.shop.utils.ConsoleHelper;
import com.epam.preprod.karavayev.shop.utils.InputProcessor;
import com.epam.preprod.karavayev.shop.utils.UserInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class ShopClient {

    private static final String EXIT = "0";
    private static final File fileToSerialize = new File("file.ser");
    private ShopCommandInvoker invoker;
    private UserInterface console;
    private InputProcessor processor;
    private Map<String, Command> commandList;
    private ListSaverReader saverReader;
    private Stock stock;
    private InputStrategyDirector inputStrategyDirector;

    private ShopClient() {
        invoker = new ShopCommandInvoker();
        saverReader = new ListSaverReader();
        List<StringInstrument> stringInstrumentList = saverReader.read(fileToSerialize);
        stock = new Stock(stringInstrumentList);
        console = new ConsoleHelper();
        processor = new InputProcessor();
        ShoppingCartHistory shoppingCartHistory = new ShoppingCartHistory();
        ShoppingCart shoppingCart = new ShoppingCart();
        OrderDatabase orderDatabase = new OrderDatabase();
        ShopReceiver shopReceiver =
                new ShopReceiver(stock, shoppingCart, shoppingCartHistory, orderDatabase, console, processor);
        commandList = new HashMap<>();
        commandList.put("1", new ShowAllProductsCommand(shopReceiver));
        commandList.put("2", new ShowCartCommand(shopReceiver));
        commandList.put("3", new AddToCartCommand(shopReceiver));
        commandList.put("4", new ShowCartHistoryCommand(shopReceiver));
        commandList.put("5", new ShowOrderCommand(shopReceiver));
    }

    public Stock getStock() {
        return this.stock;
    }

    public static void main(String[] args) {
        ShopClient shopClient =  new ShopClient();
        Map<String, RequestHandler> tcpHandlers = new HashMap<>();
        tcpHandlers.put("get count",  new TCPCountImpl(shopClient.getStock()));
        tcpHandlers.put("get item=", new TCPItemImpl(shopClient.getStock()));
        new ServerApp().runServer(3000, new TCPRequestHandlerFactory(tcpHandlers));

        Map<String, RequestHandler> httpHandlers = new HashMap<>();
        httpHandlers.put("/shop/count", new HttpCountImpl(shopClient.getStock()));
        httpHandlers.put("/shop/item", new HttpGetItemImpl(shopClient.getStock()));
        new ServerApp().runServer(3001, new HttpRequestHandlerFactory(httpHandlers));
        shopClient.runShop();
    }

    private void runShop() {
        console.promptMessage("Use reflection?(Y/N)");
        String confirmReflexionInput = console.takeInput();
        while (confirmReflexionInput != null) {
            if (confirmReflexionInput.equalsIgnoreCase("Y") || confirmReflexionInput.equalsIgnoreCase("N")) {
                break;
            } else {
                console.promptMessage("Wrong input. Try again.");
                confirmReflexionInput = console.takeInput();
            }
        }

        if (confirmReflexionInput.equalsIgnoreCase("Y")) {

            console.promptMessage("Choose your language |en|de|fr|ru");
            String language = console.takeInput();
            Locale locale = new Locale(language);
            ResourceBundle resourceBundle = ResourceBundle.getBundle("myResourceBundle", locale);

            console.promptMessage("Would you like to add products randomly(R) or manually(M)?");
            String howToAddProductInput = console.takeInput();
            if (howToAddProductInput.equalsIgnoreCase("R")) {
                inputStrategyDirector = new RandomReflexiveInputDirector(console, resourceBundle);
            } else if (howToAddProductInput.equalsIgnoreCase("M")) {
                inputStrategyDirector = new ManualReflexiveInputDirector(console, resourceBundle);
            }
            if (howToAddProductInput.equalsIgnoreCase("X")) {
                console.promptMessage("Good bye");
                return;
            }

        } else if (confirmReflexionInput.equalsIgnoreCase("N")) {
            console.promptMessage("Would you like to add products randomly(R) or manually(M)?");
            String howToAddProductInput = console.takeInput();
            while (howToAddProductInput != null) {
                if (howToAddProductInput.equalsIgnoreCase("R")) {
                    inputStrategyDirector = new RandomInputDirector();
                    break;
                } else if (howToAddProductInput.equalsIgnoreCase("M")) {
                    inputStrategyDirector = new ManualInputDirector(console);
                    break;
                }
                if (howToAddProductInput.equalsIgnoreCase("X")) {
                    console.promptMessage("Good bye");
                    return;
                }
                console.promptMessage("Wrong input. Try again");
                howToAddProductInput = console.takeInput();
            }
        }

        commandList.put("6", new AddProductCommand(inputStrategyDirector, stock, console));

        console.showMainMenu();
        String input;
        while (!(input = console.takeInput()).equals(EXIT)) {
            Command command = commandList.get(input);
            invoker.setCommand(command);
            if (command != null) {
                invoker.invokeCommand();
            } else {
                console.promptMessage("Wrong command. Try again");
            }
        }
        console.promptMessage("Good bye!");

        // save the container to file
        List<StringInstrument> productList = new ArrayList<>(stock.getProductsInStock().values());
        saverReader.save(fileToSerialize, productList, false);
    }
}
