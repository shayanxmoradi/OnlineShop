package org.example.repository.bagitems;

import org.example.entity.bag.ShoppingBag;
import org.example.repository.baseentitygenric.BaseEntityGenericRepository;

import java.sql.SQLException;

public interface BagItemRepo
        extends BaseEntityGenericRepository {
    boolean addItemToBag(Long productId, Long shoppingBagId, double price);

    boolean removeItemFromBag(Long productId);

    Integer getItemCountInBag(Long productId, Long shoppingBagId);

    ShoppingBag getShoppingBagItemsByBagId() throws SQLException;

}
