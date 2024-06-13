package org.example.repository.bagitems;

import org.example.entity.ShoppingBag;
import org.example.repository.baseentitygenric.BaseEntityGenericRepository;

import java.sql.SQLException;

public interface BagItemRepo
        extends BaseEntityGenericRepository {
    boolean addItemToBag(Long productId, Long shoppingBagId, double price);

    boolean removeItemFromBag(Long productId, Long shoppingBagId);

    Integer getItemCountInBag(Long productId, Long shoppingBagId);

    ShoppingBag getShoppingBagItemsByBagId() throws SQLException;

}
