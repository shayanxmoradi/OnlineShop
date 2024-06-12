package org.example.entity;

import org.example.entity.products.BaseProduct;

import java.util.List;

public class ShoppingBag {
    private static List<BaseProduct> productsInShoppingCart;

    public static void addProduct(BaseProduct product) {
        productsInShoppingCart.add(product);
    }
    public static void removeProduct(BaseProduct product) {
        productsInShoppingCart.remove(product);
    }

}
