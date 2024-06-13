package org.example.repository.bagitems;

import org.example.entity.BaseEntity;
import org.example.entity.enums.ElektroProductTypes;
import org.example.entity.enums.ShoeProductType;
import org.example.entity.products.ElektonicProducts;
import org.example.entity.products.ShoeProduct;
import org.example.repository.baseentitygenric.BaseEntityGenericRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BagItemsRepoImpl
        extends BaseEntityGenericRepositoryImpl
        implements BagItemRepo {
    public BagItemsRepoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public boolean addItemToBag(Long productId, Long shoppingBagId) {
        int currentQuatity = 0;
        currentQuatity = getItemCountInBag(productId, shoppingBagId);

//todo check if we have enough of that product

        String insertQuery = """
                            
                    INSERT INTO shopping_bag_items (bag_id, product_id, quantity)
                VALUES (?, ?, ?)
                ON CONFLICT (bag_id, product_id)
                DO UPDATE SET quantity =
                    EXCLUDED.quantity;
                            
                    """;
        insertQuery.formatted(getTableName());

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(3, (++currentQuatity));
            preparedStatement.setInt(1, shoppingBagId.intValue());
            preparedStatement.setInt(2, productId.intValue());


            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

        return false;
    }


    private ElektonicProducts mapResultSetToElectronicProduct(ResultSet resultSet) throws SQLException {
        long productId = resultSet.getLong("product_id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        int quantity = resultSet.getInt("quantity");
        String subProductType = resultSet.getString("sub_product_type");
        ElektroProductTypes elektroProductType;
        try {

            elektroProductType = ElektroProductTypes.valueOf(subProductType);
        } catch (Exception e) {
            elektroProductType = ElektroProductTypes.UNKNOWN;

        }
        ElektonicProducts elektonicProducts = new ElektonicProducts(name, price, quantity, elektroProductType);
        elektonicProducts.setId(productId);
        return elektonicProducts;
    }

    private ShoeProduct mapResultSetToShoeProduct(ResultSet resultSet) throws SQLException {
        long productId = resultSet.getLong("product_id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        int quantity = resultSet.getInt("quantity");
        String subProductType = resultSet.getString("sub_product_type");
        ShoeProductType shoeProductType;
        try {

            shoeProductType = ShoeProductType.valueOf(subProductType);

        } catch (Exception e) {
            shoeProductType = ShoeProductType.UNKNOWN;
        }
        ShoeProduct shoeProduct = new ShoeProduct(name, price, quantity, shoeProductType);
        shoeProduct.setId(productId);
        return shoeProduct;
    }


    @Override
    public boolean removeItemFromBag(Long productId, Long shoppingBagId) {
        //todo if quantity ==0
        int currentQuatity = 0;
        try {

            currentQuatity = getItemCountInBag(productId, shoppingBagId);
            if (currentQuatity <= 0) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

//todo check if we have enough of that product

        String insertQuery = """
                            
                    INSERT INTO shopping_bag_items (bag_id, product_id, quantity)
                VALUES (?, ?, ?)
                ON CONFLICT (bag_id, product_id)
                DO UPDATE SET quantity =
                    EXCLUDED.quantity;
                            
                    """;
        insertQuery.formatted(getTableName());

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(3, (--currentQuatity));
            preparedStatement.setInt(1, shoppingBagId.intValue());
            preparedStatement.setInt(2, productId.intValue());


            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

        return false;
    }

    @Override
    public Integer getItemCountInBag(Long productId, Long shoppingBagId) {
        String insertQuery = """
                SELECT * FROM %s WHERE product_id = ? AND bag_id = ?
                 """.formatted(getTableName());

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, productId.intValue());
            preparedStatement.setInt(2, shoppingBagId.intValue());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("quantity");
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

        return 0;
    }


    @Override
    protected String getTableName() {
        return "shopping_bag_items";
    }

    @Override
    protected BaseEntity mapResultSetToBaseEntity(ResultSet resultSet) {
        return null;
    }

    @Override
    protected BaseEntity[] getEntityArrayForFindAll() {
        return new BaseEntity[0];
    }

    @Override
    protected void fillIdParameter(PreparedStatement statement, int paramIndex, Object o) {

    }

    @Override
    public BaseEntity save(BaseEntity entity) {
        return null;
    }

    @Override
    public BaseEntity update(BaseEntity entity) {
        return null;
    }
}
