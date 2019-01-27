package com.epam.preprod.karavayev.shop.command;

import com.epam.preprod.karavayev.shop.shopreceiver.ShopReceiver;

public class ShowCartHistoryCommand implements Command {

    private ShopReceiver shopReceiver;

    public ShowCartHistoryCommand(ShopReceiver shopReceiver) {
        this.shopReceiver = shopReceiver;
    }

    @Override
    public void execute() {
        shopReceiver.viewRecentlyAddedToCartProducts();
    }
}
