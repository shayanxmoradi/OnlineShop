package org.example.services.bagitems;

import org.example.entity.ShoppingBag;
import org.example.repository.bagitems.BagItemRepo;

import java.sql.SQLException;

public class BagItemServiceImpl implements BagItemService {
    private final BagItemRepo bagItemRepo;

    public BagItemServiceImpl(BagItemRepo bagItemRepo) {
        this.bagItemRepo = bagItemRepo;
    }

    @Override
    public boolean addItemToBag(Long productId, Long shoppingBagId, double price) {
        return bagItemRepo.addItemToBag(productId, shoppingBagId, price);
    }

    @Override
    public boolean removeItemFromBag(Long productId) {
        return bagItemRepo.removeItemFromBag(productId);
    }

    @Override
    public Integer getItemCountInBag(Long productId, Long shoppingBagId) {
        return bagItemRepo.getItemCountInBag(productId, shoppingBagId);
    }

    @Override
    public ShoppingBag getShoppingBagItemsByBagId() throws SQLException {
        return bagItemRepo.getShoppingBagItemsByBagId();
    }
}
