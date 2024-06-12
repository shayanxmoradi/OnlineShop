package org.example.menu.loggedin_sub_menus;

import org.example.menu.util.Input;
import org.example.menu.util.Message;

public class ShoppingBagPage {
    private final Message MESSAGE;
    private final Input INPUT;

    public ShoppingBagPage( Input input,Message message) {
        MESSAGE = message;
        INPUT = input;
    }
    public void show(){
        System.out.println("hey here is your shopping bag");
    }

}
