package org.example.repository;

import org.example.entity.products.BaseProduct;
import org.example.repository.baseentitygenric.BaseEntityGenericRepository;

import java.util.List;

public interface BaseProductRepo<T extends BaseProduct>
        extends BaseEntityGenericRepository<T, Long> {

}
