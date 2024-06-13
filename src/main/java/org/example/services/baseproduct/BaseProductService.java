package org.example.services.baseproduct;

import org.example.entity.BaseEntity;
import org.example.service.BaseEntityService;

import java.util.List;

public interface BaseProductService <BaseProduct extends BaseEntity<Long>>  extends BaseEntityService<BaseProduct,Long> {
List<BaseProduct> getAllProductsTogether();
}
