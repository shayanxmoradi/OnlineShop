package org.example.entity;

import java.util.List;

public class ShoppingCart {
    private static List<BaseProduct> productsInShoppingCart;

    public static void addProduct(BaseProduct product) {
        productsInShoppingCart.add(product);
    }
    public static void removeProduct(BaseProduct product) {
        productsInShoppingCart.remove(product);
    }

}
