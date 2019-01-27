package com.epam.preprod.karavayev.shop.command;


import com.epam.preprod.karavayev.shop.shopreceiver.ShopReceiver;

public class ShowAllProductsCommand implements Command {

    private ShopReceiver shopReceiver;

    public ShowAllProductsCommand(ShopReceiver shopReceiver) {
        this.shopReceiver = shopReceiver;
    }

    @Override
    public void execute() {
        this.shopReceiver.showAllProducts();
    }
}
