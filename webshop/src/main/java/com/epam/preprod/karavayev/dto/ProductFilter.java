package com.epam.preprod.karavayev.dto;

import java.util.List;

public class ProductFilter {

    private String name;
    private String sort;
    private double priceFrom;
    private double priceTo;
    private int countItems;
    private int page;
    private List<Integer> makers; // makers' ids
    private List<Integer> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(double priceTo) {
        this.priceTo = priceTo;
    }

    public int getCountItems() {
        return countItems;
    }

    public void setCountItems(int countItems) {
        this.countItems = countItems;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Integer> getMakers() {
        return makers;
    }

    public void setMakers(List<Integer> makers) {
        this.makers = makers;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductFilter that = (ProductFilter) o;

        if (Double.compare(that.priceFrom, priceFrom) != 0) {
            return false;
        }
        if (Double.compare(that.priceTo, priceTo) != 0) {
            return false;
        }
        if (countItems != that.countItems) {
            return false;
        }
        if (page != that.page) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (sort != null ? !sort.equals(that.sort) : that.sort != null) {
            return false;
        }
        if (makers != null ? !makers.equals(that.makers) : that.makers != null) {
            return false;
        }
        return categories != null ? categories.equals(that.categories) : that.categories == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        temp = Double.doubleToLongBits(priceFrom);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(priceTo);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + countItems;
        result = 31 * result + page;
        result = 31 * result + (makers != null ? makers.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductFilter{" +
               "name='" + name + '\'' +
               ", sort='" + sort + '\'' +
               ", priceFrom=" + priceFrom +
               ", priceTo=" + priceTo +
               ", countItems=" + countItems +
               ", page=" + page +
               ", makers=" + makers +
               ", categories=" + categories +
               '}';
    }
}
