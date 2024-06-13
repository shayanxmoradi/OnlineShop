package org.example.services.shoppingbag;

import org.example.entity.ShoppingBag;
import org.example.repository.shoppingbag.ShoppingBagRepo;
import org.example.service.BaseEntityServiceImpl;

import java.sql.SQLException;

public class ShoppingBagServiceImpl extends BaseEntityServiceImpl<ShoppingBag,Long, ShoppingBagRepo>
        implements ShoppingBagService {


    public ShoppingBagServiceImpl(ShoppingBagRepo baseRepository) {
        super(baseRepository);
    }

    @Override
    public boolean createShoppingBag(ShoppingBag shoppingBag) {
        return baseRepository.save(shoppingBag)!=null;
    }

    @Override
    public void deleteShoppingBag(ShoppingBag shoppingBag) {
         baseRepository.deleteById(shoppingBag.getId());
    }

    @Override
    public ShoppingBag getShoppingBagByUserId(Long id) throws SQLException {
        return baseRepository.getShoppingBagByUserId(id);
    }
}
