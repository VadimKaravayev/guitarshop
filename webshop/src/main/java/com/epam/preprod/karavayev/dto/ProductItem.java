package com.epam.preprod.karavayev.dto;

import com.epam.preprod.karavayev.entity.Category;
import com.epam.preprod.karavayev.entity.Maker;
import com.epam.preprod.karavayev.entity.Product;

import java.util.Objects;

public final class ProductItem {

    private Product product;
    private double price;
    private int countItems;

    public ProductItem(Product product, double price, int countItems) {
        this.product = product;
        this.price = price;
        this.countItems = countItems;
    }

    public Product getProduct() {
        Product tempProduct = new Product();
        tempProduct.setId(product.getId());
        tempProduct.setDescription(product.getDescription());
        tempProduct.setName(product.getName());

        Maker maker = new Maker();
        maker.setId(product.getMaker().getId());
        maker.setName(product.getMaker().getName());
        tempProduct.setMaker(maker);

        Category category = new Category();
        category.setId(product.getCategory().getId());
        category.setName(product.getCategory().getName());
        tempProduct.setCategory(category);

        return tempProduct;
    }

    public double getPrice() {
        return price;
    }

    public int getCountItems() {
        return countItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductItem)) return false;
        ProductItem that = (ProductItem) o;
        return Double.compare(that.getPrice(), getPrice()) == 0 &&
                getCountItems() == that.getCountItems() &&
                Objects.equals(getProduct(), that.getProduct());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getProduct(), getPrice(), getCountItems());
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "product=" + product +
                ", price=" + price +
                ", countItems=" + countItems +
                '}';
    }
}
