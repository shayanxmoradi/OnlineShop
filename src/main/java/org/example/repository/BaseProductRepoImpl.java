package org.example.repository;

import org.example.entity.products.BaseProduct;
import org.example.repository.baseentitygenric.BaseEntityGenericRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BaseProductRepoImpl<T extends BaseProduct>
        extends BaseEntityGenericRepositoryImpl<T, Long>
        implements BaseProductRepo<T> {

    public BaseProductRepoImpl(Connection connection) {
        super(connection);
    }



    @Override
    protected String getTableName() {
        return "product";
    }

    @Override
    protected T mapResultSetToBaseEntity(ResultSet resultSet) {
        return null;
    }

    @Override
    protected T[] getEntityArrayForFindAll() {
        return null;
    }

    @Override
    protected void fillIdParameter(PreparedStatement statement, int paramIndex, Long aLong) {

    }

    @Override
    public T save(T entity) {
        return null;
    }

    @Override
    public T update(T entity) {
        return null;
    }
}
