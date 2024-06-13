package org.example.repository.shoppingbag;

import org.example.entity.ShoppingBag;
import org.example.repository.baseentitygenric.BaseEntityGenericRepositoryImpl;
import org.example.util.AuthHolder;
import org.example.util.BagHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingBagRepoImpl extends BaseEntityGenericRepositoryImpl<ShoppingBag, Long>
        implements ShoppingBagRepo {
    private final AuthHolder AUTH_HOLDER;
    private final BagHolder BAG_HOLDER;

    public ShoppingBagRepoImpl(Connection connection, AuthHolder authHolder, BagHolder bagHolder) {
        super(connection);
        AUTH_HOLDER = authHolder;
        BAG_HOLDER = new BagHolder();
    }

    @Override
    protected String getTableName() {
        return "shopping_bags";
    }

    @Override
    protected ShoppingBag mapResultSetToBaseEntity(ResultSet resultSet) {
        ShoppingBag shoppingBag = null;
        try {
            shoppingBag = new ShoppingBag((long) resultSet.getInt("user_id_fk"));
            shoppingBag.setId(resultSet.getLong("id"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return shoppingBag;
    }


    @Override
    protected ShoppingBag[] getEntityArrayForFindAll() {
        return new ShoppingBag[0];
    }

    @Override
    protected void fillIdParameter(PreparedStatement statement, int paramIndex, Long aLong) {

    }


    @Override
    public ShoppingBag save(ShoppingBag entity) {
        ShoppingBag shoppingBag = null;

        String insertQuery = """
                insert into shopping_bags(user_id_fk) values (?)
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, entity.getUserId().intValue());


            if (preparedStatement.executeUpdate() > 0) {
                try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                    if (keys.next()) {
                        shoppingBag = new ShoppingBag((long) keys.getInt("id"));
                        return shoppingBag;
                    }
                }
            }
        } catch (SQLException SqlE) {
            SqlE.printStackTrace();
            throw new RuntimeException("Error saving tweet", SqlE);
        }
        return null;
    }


    @Override
    public ShoppingBag update(ShoppingBag entity) {
        return null;
    }

    @Override
    public ShoppingBag getShoppingBagByUserId(Long id) throws SQLException {
        ShoppingBag shoppingBag = null;

        String query = String.format("""
                SELECT * from %s where user_id_fk = ?
                """, getTableName());

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id.intValue());

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            shoppingBag = mapResultSetToBaseEntity(resultSet);
            BAG_HOLDER.setShoppingBagId((Long) shoppingBag.getId());
        }
        resultSet.close();
        preparedStatement.close();
        return shoppingBag;
    }
}