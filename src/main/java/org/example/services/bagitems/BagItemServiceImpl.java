package org.example.services.bagitems;

import org.example.repository.bagitems.BagItemRepo;

public class BagItemServiceImpl implements BagItemService {
    private final BagItemRepo bagItemRepo;

    public BagItemServiceImpl(BagItemRepo bagItemRepo) {
        this.bagItemRepo = bagItemRepo;
    }

    @Override
    public boolean addItemToBag(Long productId, Long shoppingBagId) {
        return bagItemRepo.addItemToBag(productId, shoppingBagId);
    }

    @Override
    public boolean removeItemFromBag(Long productId, Long shoppingBagId) {
        return bagItemRepo.removeItemFromBag(productId, shoppingBagId);
    }

    @Override
    public Integer getItemCountInBag(Long productId, Long shoppingBagId) {
        return bagItemRepo.getItemCountInBag(productId, shoppingBagId);
    }
}
