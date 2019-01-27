package com.epam.preprod.karavayev.shop.command;

import com.epam.preprod.karavayev.shop.shopreceiver.ShopReceiver;

public class ShowOrderCommand implements Command {

    private ShopReceiver shopReceiver;

    public ShowOrderCommand(ShopReceiver shopReceiver) {
        this.shopReceiver = shopReceiver;
    }

    @Override
    public void execute() {
        shopReceiver.showOrder();
    }
}
