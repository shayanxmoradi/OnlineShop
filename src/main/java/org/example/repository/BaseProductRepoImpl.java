package org.example.repository;

import org.example.entity.User;
import org.example.entity.enums.ElektroProductTypes;
import org.example.entity.enums.ShoeProductType;
import org.example.entity.products.BaseProduct;
import org.example.entity.products.ElektonicProducts;
import org.example.entity.products.ShoeProduct;
import org.example.repository.baseentitygenric.BaseEntityGenericRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        //todo delete here later
        BaseProduct baseProduct = null;
        try {
            baseProduct = new ElektonicProducts(resultSet.getString("name"),
                    1000, resultSet.getInt("quantity")
                    , ElektroProductTypes.COMPUTER);
            baseProduct.setId(resultSet.getLong("id"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return baseProduct;
    }


    private ElektonicProducts mapResultSetToElectronicProduct(ResultSet resultSet) throws SQLException {
        long productId = resultSet.getLong("product_id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        int quantity = resultSet.getInt("quantity");
        String subProductType = resultSet.getString("sub_product_type");
        System.out.println(subProductType);
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
        System.out.println(subProductType);
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

    @Override
    public List<BaseProduct> getAllProductsTogether() {
        String sql = """
                SELECT p.id AS product_id, p.name, p.quantity, p.product_type,
                       ep.id AS sub_product_id,
                       ep.elektro_product_type::text AS sub_product_type,
                       ep.volltage AS sub_product_detail,p.price
                FROM product p
                         LEFT JOIN elektro_product ep ON p.id = ep.product_id_fk
                WHERE p.product_type = 'ELEKTRO'
                UNION
                SELECT p.id AS product_id, p.name, p.quantity, p.product_type,
                       sp.id AS sub_product_id,
                       sp.shoe_product_type::text AS sub_product_type,
                       sp.size AS sub_product_detail,price
                FROM product p
                         LEFT JOIN shoe_product sp ON p.id = sp.product_id_fk
                WHERE p.product_type = 'SHOE';

                """;

        List<BaseProduct> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String productType = resultSet.getString("product_type");
                if ("ELEKTRO".equals(productType)) {
                    ElektonicProducts electronicProduct = mapResultSetToElectronicProduct(resultSet);
                    products.add(electronicProduct);
                } else if ("SHOE".equals(productType)) {
                    ShoeProduct shoeProduct = mapResultSetToShoeProduct(resultSet);
                    products.add(shoeProduct);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
