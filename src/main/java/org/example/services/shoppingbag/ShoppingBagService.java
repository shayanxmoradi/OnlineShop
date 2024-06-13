package org.example.services.shoppingbag;

import org.example.entity.ShoppingBag;
import org.example.repository.baseentitygenric.BaseEntityGenericRepository;

import java.sql.SQLException;

public interface ShoppingBagService   extends BaseEntityGenericRepository<ShoppingBag, Long>{
    boolean createShoppingBag(ShoppingBag shoppingBag);
    void deleteShoppingBag(ShoppingBag shoppingBag);

    ShoppingBag getShoppingBagByUserId(Long id) throws SQLException;

}
