package org.example.services;

import org.example.entity.BaseEntity;
import org.example.entity.products.BaseProduct;
import org.example.service.BaseEntityService;

public interface BaseProductService <BaseProduct extends BaseEntity<Long>>  extends BaseEntityService<BaseProduct,Long> {
}