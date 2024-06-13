package org.example.menu.loggedin_sub_menus;

import org.example.entity.bag.ShoppingBag;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.services.bagitems.BagItemService;

import java.sql.SQLException;

public class ShoppingBagPage {
    private final Message MESSAGE;
    private final Input INPUT;
    private final BagItemService BAG_ITEM_SERVICE;

    public ShoppingBagPage(Input input, Message message, BagItemService bagItemService) {
        MESSAGE = message;
        INPUT = input;
        this.BAG_ITEM_SERVICE = bagItemService;
    }

    public void show() {


        shoppinBagPage:
        while (true) {
            System.out.println("""
                    1->showing all shopping bags
                    2-> previous Menu
                    """);
            switch (INPUT.scanner.next()) {

                case "1" -> {
                    System.out.println("here is List of shopping bags:  ");
                    try {
                        ShoppingBag shoppingBag = BAG_ITEM_SERVICE.getShoppingBagItemsByBagId();
                        System.out.println("Total price of Bag : " + shoppingBag.getTotalPrice());
                        for (int i = 1; i <= shoppingBag.getProductsInShoppingCart().size(); i++) {
                            System.out.println("item number : " + i);
                            System.out.println(shoppingBag.getProductsInShoppingCart().get(i - 1));
                        }
                        System.out.println("if you want to remove reduce Enter R");
                        if (INPUT.scanner.next().toLowerCase().equals("r")) {
                            System.out.println("ENTER Item Nummber wich you want to reduce");
                            int itemNumber = INPUT.scanner.nextInt();
                            while (itemNumber >= shoppingBag.getProductsInShoppingCart().size() + 1 ||
                                   itemNumber <= 0) {
                                System.out.println(MESSAGE.getInvalidInputMessage());
                                System.out.println("enter Item Nummber wich you want to reduce");
                                itemNumber = INPUT.scanner.nextInt();
                            }
                            BAG_ITEM_SERVICE.removeItemFromBag(shoppingBag.getProductsInShoppingCart().get(itemNumber - 1).getProductId());


                        }

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }
                case "2" -> {
                    break shoppinBagPage;
                }
                default -> System.out.println(MESSAGE.getInvalidInputMessage());
            }


        }
    }

}
