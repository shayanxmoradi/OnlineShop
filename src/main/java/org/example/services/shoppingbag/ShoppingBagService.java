package org.example.services.shoppingbag;

import org.example.entity.ShoppingBag;
import org.example.repository.baseentitygenric.BaseEntityGenericRepository;

public interface ShoppingBagService   extends BaseEntityGenericRepository<ShoppingBag, Long>{
    boolean createShoppingBag(ShoppingBag shoppingBag);
    void deleteShoppingBag(ShoppingBag shoppingBag);
}
