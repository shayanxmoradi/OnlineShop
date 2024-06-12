package org.example.repository;

import org.example.entity.User;
import org.example.entity.enums.ElektroProductTypes;
import org.example.entity.products.BaseProduct;
import org.example.entity.products.ElektonicProducts;
import org.example.repository.baseentitygenric.BaseEntityGenericRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseProductRepoImpl
        extends BaseEntityGenericRepositoryImpl<BaseProduct, Long>
        implements BaseProductRepo {

    public BaseProductRepoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "product";
    }


    @Override
    protected BaseProduct mapResultSetToBaseEntity(ResultSet resultSet) {
        //todo hier ist wirklich gefickt
        BaseProduct baseProduct = null;
        try {
            baseProduct = new ElektonicProducts(resultSet.getString("name"),
                    1000,resultSet.getInt("quantity")
            , ElektroProductTypes.COMPUTER);
            baseProduct.setId(resultSet.getLong("id"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return baseProduct;
    }

    @Override
    protected BaseProduct[] getEntityArrayForFindAll() {
        return new BaseProduct[(int) count()];
    }

    @Override
    protected void fillIdParameter(PreparedStatement statement, int paramIndex, Long aLong) {

    }


    @Override
    public BaseProduct save(BaseProduct entity) {
        return null;
    }

    @Override
    public BaseProduct update(BaseProduct entity) {
        return null;
    }
}
