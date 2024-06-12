package org.example.entity.products;

import org.example.entity.enums.ElektroProductTypes;

public class ElektonicProducts extends BaseProduct {
    private ElektroProductTypes elektroProductTypes;
    public ElektonicProducts(String name, double price, int quantity,ElektroProductTypes elektroProductTypes) {
        super(name, price, quantity);
        this.elektroProductTypes = elektroProductTypes;
    }

    @Override
    public String toString() {
        return "ElektonicProducts{" +
               "elektroProductTypes=" + elektroProductTypes +
               ", name='" + name + '\'' +
               ", price=" + price +
               ", quantity=" + quantity +
               '}';
    }
}
