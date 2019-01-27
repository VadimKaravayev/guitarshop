package com.epam.preprod.karavayev.entity;

import com.epam.preprod.karavayev.dto.ProductItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order {

    private int id;
    private LocalDateTime date;
    private User user;
    private String note;
    private OrderStatus status;
    private String address;
    private String creditCard;
    private List<ProductItem> productItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId() == order.getId() &&
                Objects.equals(getDate(), order.getDate()) &&
                Objects.equals(getUser(), order.getUser()) &&
                Objects.equals(getNote(), order.getNote()) &&
                getStatus() == order.getStatus() &&
                Objects.equals(getAddress(), order.getAddress()) &&
                Objects.equals(getCreditCard(), order.getCreditCard()) &&
                Objects.equals(getProductItems(), order.getProductItems());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDate(), getUser(), getNote(), getStatus(), getAddress(), getCreditCard(), getProductItems());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", user=" + user +
                ", note='" + note + '\'' +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", creditCard='" + creditCard + '\'' +
                ", productItems=" + productItems +
                '}';
    }
}
