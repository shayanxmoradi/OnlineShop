package org.example.menu;


import org.example.entity.ShoppingBag;
import org.example.entity.User;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.service.UserService;
import org.example.services.shoppingbag.ShoppingBagService;

import java.sql.SQLException;

public class SignUpMenu {
    private final Input INPUT;
    private final Message MESSAGE;
    private final UserService USER_SERVICE;
    private final ShoppingBagService SHOPPING_BAG_SERVICE;

    public SignUpMenu(Input input, Message message, UserService userService, ShoppingBagService shoppingBagService) {
        this.INPUT = input;
        this.MESSAGE = message;
        this.USER_SERVICE = userService;
        this.SHOPPING_BAG_SERVICE = shoppingBagService;
    }

    public  void show() throws SQLException {
        signup:
        while (true) {
            System.out.println("""
                    1 -> Enter Information
                    2 -> Previous Menu
                    """);

            switch (INPUT.scanner.next()) {
                case "1" : {
                    System.out.println(MESSAGE.getInputMessage("Username"));
                    String username = INPUT.scanner.next();
                    System.out.println(MESSAGE.getInputMessage("password"));
                    String password = INPUT.scanner.next();
                    System.out.println(MESSAGE.getInputMessage("firstname"));
                    String firstname = INPUT.scanner.next();
                    System.out.println(MESSAGE.getInputMessage("lastname"));
                    String lastname = INPUT.scanner.next();

                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setFirstName(firstname);
                    user.setLastName(lastname);
                    if (USER_SERVICE.signUp(user)) {
                        System.out.println(MESSAGE.getSuccessfulMessage("sign up"));
                        //todo imp shopping bag
                        ShoppingBag shoppingBag = new ShoppingBag(user.getId());
                    SHOPPING_BAG_SERVICE.createShoppingBag(shoppingBag);
                        break signup;
                    }
                    System.out.println(MESSAGE.getExistMessage("username"));
                    break ;
                }
                case "2" : {
                    break signup;
                }
                default : System.out.println(MESSAGE.getInvalidInputMessage());

            }

        }
    }
}
