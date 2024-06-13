package org.example.entity;

import org.example.entity.products.ShoppingBagItems;

import java.util.List;

public class ShoppingBag extends BaseEntity<Long> {
    private List<ShoppingBagItems> productsInShoppingCart;
    private Long userId;
    private Double totalPrice;

    public ShoppingBag(Long userId) {
        this.userId = userId;
    }

    public ShoppingBag() {

    }


    public Long getUserId() {
        return userId;
    }


    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ShoppingBagItems> getProductsInShoppingCart() {
        return productsInShoppingCart;
    }

    public void setProductsInShoppingCart(List<ShoppingBagItems> productsInShoppingCart) {
        this.productsInShoppingCart = productsInShoppingCart;
    }
}
