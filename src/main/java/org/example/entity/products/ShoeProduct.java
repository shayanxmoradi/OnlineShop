package org.example.entity.products;

import org.example.entity.enums.ShoeProductType;

public class ShoeProduct extends BaseProduct {
    private ShoeProductType shoeProductTypes;

    public ShoeProduct(String name, double price, int quantity, ShoeProductType shoeProductType) {
        super(name, price, quantity);
        this.shoeProductTypes = shoeProductType;
    }

    @Override
    public String toString() {
        return "ShoeProduct{" +
               "name='" + name + '\'' +
               ", price=" + price +
               ", quantity=" + quantity +
               '}';
    }
}
