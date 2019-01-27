package com.epam.preprod.karavayev.shop.command;

import com.epam.preprod.karavayev.shop.inputproduct.director.InputStrategyDirector;
import com.epam.preprod.karavayev.shop.inputproduct.ProductInputStrategy;
import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import com.epam.preprod.karavayev.model.myexception.WrongInstrumentTypeException;
import com.epam.preprod.karavayev.model.productstock.Stock;
import com.epam.preprod.karavayev.shop.utils.UserInterface;

public class AddProductCommand implements Command {

    private UserInterface console;
    private InputStrategyDirector inputStrategyDirector;
    private Stock stock;

    public AddProductCommand(InputStrategyDirector inputStrategyDirector, Stock stock, UserInterface console) {
        this.inputStrategyDirector = inputStrategyDirector;
        this.stock = stock;
        this.console = console;
    }

    @Override
    public void execute() {
        try {
            console.promptMessage("Enter tasktype of a product");
            String product = console.takeInput().toLowerCase();
            ProductInputStrategy inputStrategy = inputStrategyDirector.getInputStrategy(product);
            StringInstrument instrument = inputStrategy.create();
            if (stock.addProductToStock(instrument)) {
                console.promptMessage("Added successfully");
            } else {
                console.promptMessage("Product adding failed");
            }
        } catch (WrongInstrumentTypeException e) {
            System.err.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Not a number");
        } catch (IllegalArgumentException e) {
            System.err.println("Wrong tasktype");
        }

    }
}
