package org.example.menu.loggedin_sub_menus;

import org.example.menu.util.Input;
import org.example.menu.util.Message;

public class LoggedInMenu {
    private final Message MESSAGE;
    private final Input INPUT;
    private final ShoppingBagPage SHOPPINGBAG;
    private  final CheckoutPage CHECKOUT;
    private final AllProductPage ALL_PRODUCT;




    public LoggedInMenu(Input input, Message message, ShoppingBagPage shoppingBagPage, AllProductPage allProductPage, CheckoutPage checkoutPage) {
        MESSAGE = message;
        INPUT = input;
        SHOPPINGBAG = shoppingBagPage;
        CHECKOUT = checkoutPage;
        ALL_PRODUCT = allProductPage;

    }

    public void show() {
       loggedInMenu: while (true) {
            System.out.println("""
                    1 -> Show ALL Products
                    2 -> Show Shopping bag
                    3 -> checkout
                    4 -> Logout
                    """);
            switch (Input.scanner.next()) {
                case "1" -> ALL_PRODUCT.show();
                case "2" -> SHOPPINGBAG.show();
                case "3" -> CHECKOUT.show();
                case "4" -> {
                    break loggedInMenu;
                }
                default -> System.out.println(MESSAGE.getInvalidInputMessage());

            }
        }
    }
}
