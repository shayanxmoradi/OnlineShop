package org.example.services.bagitems;

import org.example.entity.bag.ShoppingBag;

import java.sql.SQLException;

public interface BagItemService {
    boolean addItemToBag(Long productId, Long shoppingBagId, double price);

    boolean removeItemFromBag(Long productId);

    Integer getItemCountInBag(Long productId, Long shoppingBagId);

    ShoppingBag getShoppingBagItemsByBagId() throws SQLException;

}
