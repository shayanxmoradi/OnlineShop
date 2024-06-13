package org.example.menu;


import org.example.menu.loggedin_sub_menus.LoggedInMenu;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.service.UserService;
import org.example.services.shoppingbag.ShoppingBagService;
import org.example.util.AuthHolder;
import org.example.util.BagHolder;

import java.sql.SQLException;

public class LoginMenu {
    private final Input INPUT;
    private final Message MESSAGE;
    private final UserService USER_SERVICE;
    private final AuthHolder AUTH_HOLDER;
    private final LoggedInMenu LOGGED_IN_MENU;
    private final ShoppingBagService SHOPPING_BAG_Service;
    private final BagHolder BAG_HOLDER;

    public LoginMenu(Input input, Message message, UserService userService, AuthHolder AUTH_HOLDER, LoggedInMenu LOGGED_IN_MENU, ShoppingBagService shoppingBagService, BagHolder bagHolder) {
        this.INPUT = input;
        this.MESSAGE = message;
        this.USER_SERVICE = userService;
        this.AUTH_HOLDER = AUTH_HOLDER;
        this.LOGGED_IN_MENU = LOGGED_IN_MENU;
        SHOPPING_BAG_Service = shoppingBagService;
        BAG_HOLDER = bagHolder;
    }

    public void show() throws SQLException {
        login:
        while (true) {

            System.out.println("""
                    1 -> Enter Information
                    2 -> Previous Menu
                    """);
            switch (INPUT.scanner.next()) {
                case "1": {
                    System.out.println(MESSAGE.getInputMessage("userName"));
                    String username = INPUT.scanner.next();
                    System.out.println(MESSAGE.getInputMessage("password"));
                    String password = INPUT.scanner.next();
                    if (USER_SERVICE.login(username, password)) {
                        System.out.println(MESSAGE.getSuccessfulMessage("login "));
                        //todo sub menu
                        System.out.println("AUTH_HOLDER: " + AUTH_HOLDER.totkenUserId);
                        BAG_HOLDER.setShoppingBagId(
                                SHOPPING_BAG_Service.getShoppingBagByUserId(AUTH_HOLDER.totkenUserId).getId());
                        LOGGED_IN_MENU.show();
                        //todo watchout
                        AUTH_HOLDER.reset();
                        break login;
                    }
                    System.out.println(MESSAGE.getNotFoundMessage(username));
                    break;
                }
                case "2": {
                    break login;
                }
                default:
                    System.out.println(MESSAGE.getInvalidInputMessage());
            }

        }

    }
}
