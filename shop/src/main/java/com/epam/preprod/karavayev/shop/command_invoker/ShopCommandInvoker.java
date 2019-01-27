package com.epam.preprod.karavayev.shop.command_invoker;

import com.epam.preprod.karavayev.shop.command.Command;

public class ShopCommandInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void invokeCommand() {
        command.execute();
    }
}
