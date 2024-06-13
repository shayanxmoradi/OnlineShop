package org.example.repository.shoppingbag;

import org.example.entity.bag.ShoppingBag;
import org.example.repository.baseentitygenric.BaseEntityGenericRepository;

import java.sql.SQLException;

public interface ShoppingBagRepo  extends BaseEntityGenericRepository<ShoppingBag, Long> {
    ShoppingBag getShoppingBagByUserId(Long id) throws SQLException;
}
