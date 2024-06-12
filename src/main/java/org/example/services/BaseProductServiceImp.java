package org.example.services;

import org.example.entity.User;
import org.example.entity.products.BaseProduct;
import org.example.repository.BaseProductRepo;
import org.example.repository.user.UserRepository;
import org.example.service.BaseEntityService;
import org.example.service.BaseEntityServiceImpl;
import org.example.service.UserService;

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
