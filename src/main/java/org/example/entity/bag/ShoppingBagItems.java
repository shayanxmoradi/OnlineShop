package org.example.entity.bag;


import org.example.entity.BaseEntity;

public class ShoppingBagItems extends BaseEntity<Long> {
    private Long shoppingBagId;
    private Long productId;
    private int quantity;
    private Double pricePerUnit;
    private String productName;

    public Long getShoppingBagId() {
        return shoppingBagId;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public void setShoppingBagId(Long shoppingBagId) {
        this.shoppingBagId = shoppingBagId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "ShoppingBagItems{" +
               ", productId=" + productId +
               ", quantity=" + quantity +
               ", pricePerUnit=" + pricePerUnit +
               ", productName='" + productName +

               '}';
    }
}
