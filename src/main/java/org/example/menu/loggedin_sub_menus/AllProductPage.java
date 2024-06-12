package org.example.menu.loggedin_sub_menus;

import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.services.BaseProductService;

import java.util.Arrays;

public class AllProductPage {
    private final Message MESSAGE;
    private final Input INPUT;
    private final BaseProductService BASEPRODUCTSERVICE;


    public AllProductPage(Input input, Message message, BaseProductService baseProductService) {
        MESSAGE = message;
        INPUT = input;
        BASEPRODUCTSERVICE = baseProductService;
    }

    public void show() {
        System.out.println(BASEPRODUCTSERVICE.getAllProductsTogether());

        System.out.println("hey here is list of all products");
    }


}
