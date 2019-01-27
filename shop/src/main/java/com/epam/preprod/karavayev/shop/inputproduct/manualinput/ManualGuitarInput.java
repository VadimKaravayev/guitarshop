package com.epam.preprod.karavayev.shop.inputproduct.manualinput;

import com.epam.preprod.karavayev.shop.inputproduct.ProductInputStrategy;
import com.epam.preprod.karavayev.model.instrument.Guitar;
import com.epam.preprod.karavayev.model.instrument.GuitarType;
import com.epam.preprod.karavayev.shop.productbuilder.GuitarBuilder;
import com.epam.preprod.karavayev.shop.utils.UserInterface;

import java.math.BigDecimal;

public class ManualGuitarInput implements ProductInputStrategy<Guitar> {

    private String name;
    private BigDecimal price;
    private GuitarType guitarType;
    private UserInterface console;

    public ManualGuitarInput(UserInterface console) {
        this.console = console;
    }

    @Override
    public Guitar create() {
        console.promptMessage("Enter guitar name");
        this.name = console.takeInput();
        console.promptMessage("Enter guitar price");
        this.price = new BigDecimal(console.takeInput());
        console.promptMessage("Enter guitar tasktype");
        this.guitarType = GuitarType.valueOf(console.takeInput().toUpperCase());
        return new GuitarBuilder().buildName(name).buildPrice(price).buildGuitarType(guitarType).build();
    }
}
