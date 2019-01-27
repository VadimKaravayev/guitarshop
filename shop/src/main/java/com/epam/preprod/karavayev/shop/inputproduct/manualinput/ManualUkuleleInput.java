package com.epam.preprod.karavayev.shop.inputproduct.manualinput;

import com.epam.preprod.karavayev.shop.inputproduct.ProductInputStrategy;
import com.epam.preprod.karavayev.model.instrument.GuitarType;
import com.epam.preprod.karavayev.model.instrument.Ukulele;
import com.epam.preprod.karavayev.model.instrument.UkuleleType;
import com.epam.preprod.karavayev.shop.productbuilder.UkuleleBuilder;
import com.epam.preprod.karavayev.shop.utils.UserInterface;

import java.math.BigDecimal;

public class ManualUkuleleInput implements ProductInputStrategy<Ukulele> {

    private String name;
    private BigDecimal price;
    private GuitarType guitarType;
    private UkuleleType ukuleleType;
    private UserInterface console;

    public ManualUkuleleInput(UserInterface console) {
        this.console = console;
    }

    @Override
    public Ukulele create() {
        console.promptMessage("Enter ukulele name");
        this.name = console.takeInput();
        console.promptMessage("Enter ukulele price");
        this.price = new BigDecimal(console.takeInput());
        console.promptMessage("Enter guitar tasktype");
        this.guitarType = GuitarType.valueOf(console.takeInput().toUpperCase());
        console.promptMessage("Enter ukulele tasktype");
        this.ukuleleType = UkuleleType.valueOf(console.takeInput().toUpperCase());
        return new UkuleleBuilder().buildName(name).buildPrice(price)
                .buildGuitarType(guitarType).buildUkuleleType(ukuleleType).build();
    }
}


