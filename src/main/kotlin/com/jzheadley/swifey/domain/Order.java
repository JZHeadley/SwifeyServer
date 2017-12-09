package com.jzheadley.swifey.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private Timestamp orderDate;
    private String specialRequest;

    @ManyToOne
    @JoinColumn(name = "checkInId")
    @JsonManagedReference(value = "checkInReference")
    private CheckIn checkIn;

    @ManyToMany
    @JoinTable(name = "ORDER_MEALS",
            joinColumns = {@JoinColumn(name = "orderId")},
            inverseJoinColumns = {@JoinColumn(name = "mealId")})
    private List<Meal> orderedMeals;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    public Order() {
    }

    public Order(Timestamp orderDate, String specialRequest, CheckIn checkIn, List<Meal> orderedMeals, User user) {
        this.orderDate = orderDate;
        this.specialRequest = specialRequest;
        this.checkIn = checkIn;
        this.orderedMeals = orderedMeals;
        this.user = user;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    public CheckIn getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(CheckIn checkIn) {
        this.checkIn = checkIn;
    }

    public List<Meal> getOrderedMeals() {
        return orderedMeals;
    }

    public void setOrderedMeals(List<Meal> orderedMeals) {
        this.orderedMeals = orderedMeals;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", specialRequest='" + specialRequest + '\'' +
                ", checkIn=" + checkIn +
                ", orderedMeals=" + orderedMeals +
                ", user=" + user +
                '}';
    }
}
