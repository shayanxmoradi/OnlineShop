package org.example.repository.bagitems;

import org.example.entity.BaseEntity;
import org.example.entity.bag.ShoppingBag;
import org.example.entity.bag.ShoppingBagItems;
import org.example.entity.enums.ElektroProductTypes;
import org.example.entity.enums.ShoeProductType;
import org.example.entity.products.ElektonicProducts;
import org.example.entity.products.ShoeProduct;
import org.example.repository.baseentitygenric.BaseEntityGenericRepositoryImpl;
import org.example.util.BagHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BagItemsRepoImpl extends BaseEntityGenericRepositoryImpl implements BagItemRepo {
    private final BagHolder BAG_HOLDER;

    public BagItemsRepoImpl(Connection connection, BagHolder bagHolder) {
        super(connection);
        BAG_HOLDER = bagHolder;
    }

    @Override
    public boolean addItemToBag(Long productId, Long shoppingBagId, double price) {
        int currentQuantity = getItemCountInBag(productId, shoppingBagId);

        if (currentQuantity + 1 > getMaxAvailableProduct(productId)) {
            System.out.println("Sorry, but you can't add more of this item because we are out of stock.");
            return false;
        }

        String insertQuery = """
                INSERT INTO shopping_bag_items (bag_id, product_id, quantity, price_per_unit) 
                VALUES (?, ?, ?, ?)
                ON CONFLICT (bag_id, product_id)
                DO UPDATE SET quantity = EXCLUDED.quantity;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, shoppingBagId.intValue());
            preparedStatement.setInt(2, productId.intValue());
            preparedStatement.setInt(3, ++currentQuantity);
            preparedStatement.setDouble(4, price);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
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

        ElektonicProducts electronicProducts = new ElektonicProducts(name, price, quantity, elektroProductType);
        electronicProducts.setId(productId);
        return electronicProducts;
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
    public boolean removeItemFromBag(Long productId) {
        int currentQuantity = getItemCountInBag(productId, BAG_HOLDER.getShoppingBagId());

        if (currentQuantity <= 0) {
            return false;
        }

        if (currentQuantity == 1) {
            return executeDeleteQuery(productId);
        }

        String insertQuery = """
                INSERT INTO shopping_bag_items (bag_id, product_id, quantity)
                VALUES (?, ?, ?)
                ON CONFLICT (bag_id, product_id)
                DO UPDATE SET quantity = EXCLUDED.quantity;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, BAG_HOLDER.ShoppingBagId.intValue());
            preparedStatement.setInt(2, productId.intValue());
            preparedStatement.setInt(3, --currentQuantity);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    private boolean executeDeleteQuery(Long productId) {
        String deleteQuery = "DELETE FROM shopping_bag_items WHERE bag_id = ? AND product_id = ?";

        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery)) {
            deleteStmt.setInt(1, BAG_HOLDER.ShoppingBagId.intValue());
            deleteStmt.setInt(2, productId.intValue());
            deleteStmt.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    public Integer getItemCountInBag(Long productId, Long shoppingBagId) {
        String selectQuery = """
                SELECT quantity FROM shopping_bag_items
                WHERE bag_id = ? AND product_id = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, shoppingBagId.intValue());
            preparedStatement.setInt(2, productId.intValue());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("quantity");
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

        return 0;
    }

    public Integer getMaxAvailableProduct(Long productId) {
        String selectQuery = """
                SELECT quantity FROM product
                WHERE id = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, productId.intValue());

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
    public ShoppingBag getShoppingBagItemsByBagId() throws SQLException {
        List<ShoppingBagItems> shoppingBagItemsList = new ArrayList<>();
        String selectQuery = """
                SELECT * FROM shopping_bag_items
                JOIN product ON product.id = shopping_bag_items.product_id
                WHERE bag_id = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, BAG_HOLDER.ShoppingBagId.intValue());

            ResultSet resultSet = preparedStatement.executeQuery();
            double totalPrice = 0;

            while (resultSet.next()) {
                ShoppingBagItems shoppingBagItems = mapResultSetToShoppingBagItems(resultSet);
                shoppingBagItemsList.add(shoppingBagItems);
                totalPrice += shoppingBagItems.getPricePerUnit() * shoppingBagItems.getQuantity();
            }

            ShoppingBag shoppingBag = new ShoppingBag();
            shoppingBag.setProductsInShoppingCart(shoppingBagItemsList);
            shoppingBag.setTotalPrice(totalPrice);

            return shoppingBag;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    private ShoppingBagItems mapResultSetToShoppingBagItems(ResultSet resultSet) throws SQLException {
        ShoppingBagItems shoppingBagItems = new ShoppingBagItems();
        shoppingBagItems.setId(resultSet.getLong("bag_id"));
        shoppingBagItems.setProductId(resultSet.getLong("product_id"));
        shoppingBagItems.setQuantity(resultSet.getInt("quantity"));
        shoppingBagItems.setPricePerUnit(resultSet.getDouble("price_per_unit"));
        shoppingBagItems.setProductName(resultSet.getString("name"));
        return shoppingBagItems;
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
