package com.jzheadley.swifey.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "RestaurantHours")
public class RestaurantHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hoursId;
    private Time openTime;
    private Time closeTime;
    private String dayOfWeek;
    @ManyToOne
    @JoinColumn(name = "restaurantId")
    @JsonIgnore
    private Restaurant restaurant;

    public RestaurantHours(Time openTime, Time closeTime, String dayOfWeek, Restaurant restaurant) {
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.dayOfWeek = dayOfWeek;
        this.restaurant = restaurant;
    }

    public RestaurantHours() {

    }

    public Integer getHoursId() {
        return hoursId;
    }

    public void setHoursId(Integer hoursId) {
        this.hoursId = hoursId;
    }

    public Time getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "RestaurantHours{" +
                "hoursId=" + hoursId +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", restaurant=" + restaurant.getRestaurantId() +
                '}';
    }
}
