package com.epam.preprod.karavayev.dto;

import java.util.Arrays;

public class ProductFilterDto {

    private String name;
    private String sort;
    private String priceFrom;
    private String priceTo;
    private String countItems;
    private String page;
    private String[] makers;
    private String[] categories;

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

    public String getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(String priceFrom) {
        this.priceFrom = priceFrom;
    }

    public String getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(String priceTo) {
        this.priceTo = priceTo;
    }

    public String getCountItems() {
        return countItems;
    }

    public void setCountItems(String countItems) {
        this.countItems = countItems;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String[] getMakers() {
        return makers;
    }

    public void setMakers(String[] makers) {
        this.makers = makers;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
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

        ProductFilterDto that = (ProductFilterDto) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (sort != null ? !sort.equals(that.sort) : that.sort != null) {
            return false;
        }
        if (priceFrom != null ? !priceFrom.equals(that.priceFrom) : that.priceFrom != null) {
            return false;
        }
        if (priceTo != null ? !priceTo.equals(that.priceTo) : that.priceTo != null) {
            return false;
        }
        if (countItems != null ? !countItems.equals(that.countItems) : that.countItems != null) {
            return false;
        }
        if (page != null ? !page.equals(that.page) : that.page != null) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(makers, that.makers)) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(categories, that.categories);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        result = 31 * result + (priceFrom != null ? priceFrom.hashCode() : 0);
        result = 31 * result + (priceTo != null ? priceTo.hashCode() : 0);
        result = 31 * result + (countItems != null ? countItems.hashCode() : 0);
        result = 31 * result + (page != null ? page.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(makers);
        result = 31 * result + Arrays.hashCode(categories);
        return result;
    }

    @Override
    public String toString() {
        return "ProductFilterDto{" +
               "name='" + name + '\'' +
               ", sort='" + sort + '\'' +
               ", priceFrom='" + priceFrom + '\'' +
               ", priceTo='" + priceTo + '\'' +
               ", countItems='" + countItems + '\'' +
               ", page='" + page + '\'' +
               ", makers=" + Arrays.toString(makers) +
               ", categories=" + Arrays.toString(categories) +
               '}';
    }
}
