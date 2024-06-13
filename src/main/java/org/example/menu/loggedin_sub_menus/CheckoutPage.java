package org.example.menu.loggedin_sub_menus;

import org.example.menu.util.Input;
import org.example.menu.util.Message;

public class CheckoutPage {
    private final Message MESSAGE;
    private final Input INPUT;

    public CheckoutPage( Input input,Message message) {
        MESSAGE = message;
        INPUT = input;
    }
    public void show(){
        System.out.println("SORRY  PAGE NOT FOUND !" +
                           "-404 ");
    }
}
