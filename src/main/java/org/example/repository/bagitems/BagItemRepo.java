package org.example.repository.bagitems;

import org.example.repository.baseentitygenric.BaseEntityGenericRepository;

public interface BagItemRepo
        extends BaseEntityGenericRepository {
    boolean addItemToBag(Long productId, Long shoppingBagId);

    boolean removeItemFromBag(Long productId, Long shoppingBagId);

    Integer getItemCountInBag(Long productId, Long shoppingBagId);
}
