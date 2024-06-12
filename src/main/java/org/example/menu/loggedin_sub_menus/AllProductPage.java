package org.example.menu.loggedin_sub_menus;

import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.services.BaseProductService;

import java.util.List;

public class AllProductPage {
    private final Message MESSAGE;
    private final Input INPUT;
    private final BaseProductService BASEPRODUCTSERVICE;
    private final ShoppingBagPage SHOPPINGBAG;
    private final CheckoutPage CHECKOUT;


    public AllProductPage(Input input, Message message, BaseProductService baseProductService, ShoppingBagPage shoppingBagPage, CheckoutPage checkout) {
        MESSAGE = message;
        INPUT = input;
        BASEPRODUCTSERVICE = baseProductService;
        SHOPPINGBAG = shoppingBagPage;
        CHECKOUT = checkout;
    }

    public void show() {
        System.out.println("->Here is the list of all products : ");
        allProductPage:
        while (true) {

            int counter = 1;
            List allProductsTogether = BASEPRODUCTSERVICE.getAllProductsTogether();
            for (Object o : allProductsTogether) {
                System.out.println("item Number: " + counter);
                System.out.println(o.toString());
                counter++;
            }
            System.out.println("""
                                        
                                        
                    Chosen product number to add it to Shopping Bag
                    Enter 'S' to go to shoppingBag Page
                    Enter 'C' to go to Checkout Page
                    Enter 'X' previous Menu
                    """);
            if (INPUT.scanner.hasNextInt()) {
                int num = Input.scanner.nextInt();
                if (num >= 1 && num < allProductsTogether.size()) {
                    //todo add this product to shopping BAG
                } else System.out.println(MESSAGE.getInvalidInputMessage());


            } else {
                String text = Input.scanner.next().toLowerCase();
                if (text.equals("x")) {
                    break allProductPage;
                } else if (text.equals("c")) {
                    CHECKOUT.show();
                } else if (text.equals("s")) {
                    SHOPPINGBAG.show();
                } else System.out.println(MESSAGE.getInvalidInputMessage());
            }

        }
    }

}
