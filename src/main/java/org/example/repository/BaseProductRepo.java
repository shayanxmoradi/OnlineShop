package org.example.repository;

import org.example.entity.products.BaseProduct;
import org.example.repository.baseentitygenric.BaseEntityGenericRepository;

import java.util.List;

public interface BaseProductRepo
        extends BaseEntityGenericRepository<BaseProduct, Long> {
    List<BaseProduct> getAllProductsTogether();

}
