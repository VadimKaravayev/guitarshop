package com.epam.preprod.karavayev.shop.command;

import com.epam.preprod.karavayev.shop.shopreceiver.ShopReceiver;

public class AddToCartCommand implements Command {

    private ShopReceiver shopReceiver;
    public AddToCartCommand(ShopReceiver shopReceiver) {
        this.shopReceiver = shopReceiver;
    }

    @Override
    public void execute() {
        this.shopReceiver.addToCart();
    }
}
