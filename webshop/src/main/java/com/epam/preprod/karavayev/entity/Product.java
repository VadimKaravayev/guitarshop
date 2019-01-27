package com.epam.preprod.karavayev.entity;

public class Product {

    private int id;
    private String name;
    private Category category;
    private String description;
    private double price;
    private Maker maker;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Maker getMaker() {
        return maker;
    }

    public void setMaker(Maker maker) {
        this.maker = maker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;

        if (id != product.id) {
            return false;
        }
        if (Double.compare(product.price, price) != 0) {
            return false;
        }
        if (name != null ? !name.equals(product.name) : product.name != null) {
            return false;
        }
        if (category != null ? !category.equals(product.category) : product.category != null) {
            return false;
        }
        if (description != null ? !description.equals(product.description) : product.description != null) {
            return false;
        }
        return maker != null ? maker.equals(product.maker) : product.maker == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (maker != null ? maker.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", category=" + category +
               ", description='" + description + '\'' +
               ", price=" + price +
               ", maker=" + maker +
               '}';
    }
}
