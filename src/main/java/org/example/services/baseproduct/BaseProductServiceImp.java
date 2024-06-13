package org.example.services.baseproduct;

import org.example.entity.products.BaseProduct;
import org.example.repository.baseproduct.BaseProductRepo;
import org.example.service.BaseEntityServiceImpl;

import java.util.List;

public class BaseProductServiceImp extends BaseEntityServiceImpl<BaseProduct,Long, BaseProductRepo>
        implements BaseProductService<BaseProduct> {

    public BaseProductServiceImp(BaseProductRepo baseRepository) {
        super(baseRepository);
    }



    @Override
    public List<BaseProduct> getAllProductsTogether() {
        return baseRepository.getAllProductsTogether();
    }
}
