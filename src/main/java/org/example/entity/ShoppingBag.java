package org.example.entity;

import org.example.entity.products.BaseProduct;

import java.util.List;

public class ShoppingBag extends BaseEntity<Long> {
    private static List<BaseProduct> productsInShoppingCart;
    private  final Long userId;

    public ShoppingBag(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public static void addProduct(BaseProduct product) {
        productsInShoppingCart.add(product);
    }
    public static void removeProduct(BaseProduct product) {
        productsInShoppingCart.remove(product);
    }

}
