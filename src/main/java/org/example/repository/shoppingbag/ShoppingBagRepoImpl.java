package org.example.repository.shoppingbag;

import org.example.entity.bag.ShoppingBag;
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
        try {
            ShoppingBag shoppingBag = new ShoppingBag((long) resultSet.getInt("user_id_fk"));
            shoppingBag.setId(resultSet.getLong("id"));
            return shoppingBag;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected ShoppingBag[] getEntityArrayForFindAll() {
        return new ShoppingBag[0];
    }

    @Override
    protected void fillIdParameter(PreparedStatement statement, int paramIndex, Long aLong) {
        // This method is intentionally left blank
    }

    @Override
    public ShoppingBag save(ShoppingBag entity) {
        String insertQuery = """
                INSERT INTO shopping_bags(user_id_fk) VALUES (?)
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, entity.getUserId().intValue());

            if (preparedStatement.executeUpdate() > 0) {
                try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                    if (keys.next()) {
                        ShoppingBag shoppingBag = new ShoppingBag((long) keys.getInt("id"));
                        return shoppingBag;
                    }
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("Error saving shopping bag", sqlException);
        }
        return null;
    }

    @Override
    public ShoppingBag update(ShoppingBag entity) {
        // Implementation for updating ShoppingBag can be added here if required
        return null;
    }

    @Override
    public ShoppingBag getShoppingBagByUserId(Long id) throws SQLException {
        String query = String.format("""
                SELECT * FROM %s WHERE user_id_fk = ?
                """, getTableName());

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id.intValue());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ShoppingBag shoppingBag = mapResultSetToBaseEntity(resultSet);
                BAG_HOLDER.setShoppingBagId(shoppingBag.getId());
                return shoppingBag;
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
        return null;
    }
}
