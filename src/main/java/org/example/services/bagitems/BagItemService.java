package org.example.services.bagitems;

public interface BagItemService {
    boolean addItemToBag(Long productId, Long shoppingBagId);

    boolean removeItemFromBag(Long productId, Long shoppingBagId);

    Integer getItemCountInBag(Long productId, Long shoppingBagId);
}
