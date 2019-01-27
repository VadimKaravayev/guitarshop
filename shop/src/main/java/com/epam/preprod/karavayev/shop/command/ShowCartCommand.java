package com.epam.preprod.karavayev.shop.command;

import com.epam.preprod.karavayev.shop.shopreceiver.ShopReceiver;

public class ShowCartCommand implements Command {

    private ShopReceiver shopReceiver;

    public ShowCartCommand(ShopReceiver shopReceiver) {
        this.shopReceiver = shopReceiver;
    }

    @Override
    public void execute() {
        this.shopReceiver.showCart();
    }
}
