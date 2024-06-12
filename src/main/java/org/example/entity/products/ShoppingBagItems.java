package org.example.entity.products;


import org.example.entity.BaseEntity;

public class ShoppingBagItems extends BaseEntity<Long> {
    private Long shoppingBagId;
    private Long productId;
    private int quantity;

}
