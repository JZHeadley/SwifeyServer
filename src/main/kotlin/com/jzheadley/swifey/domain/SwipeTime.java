package com.jzheadley.swifey.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "SwipeTimes")
public class SwipeTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int swipeTimeId;
    private Time startTime;
    private Time endTime;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "restaurantId")
    private Restaurant swipeRestaurant;

    public SwipeTime() {
    }

    public SwipeTime(Time startTime, Time endTime, Restaurant swipeRestaurant) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.swipeRestaurant = swipeRestaurant;
    }

    public int getSwipeTimeId() {
        return swipeTimeId;
    }

    public void setSwipeTimeId(int swipeTimeId) {
        this.swipeTimeId = swipeTimeId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Restaurant getSwipeRestaurant() {
        return swipeRestaurant;
    }

    public void setSwipeRestaurant(Restaurant swipeRestaurant) {
        this.swipeRestaurant = swipeRestaurant;
    }

    @Override
    public String toString() {
        return "SwipeTime{" +
                "swipeTimeId=" + swipeTimeId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", swipeRestaurant=" + swipeRestaurant +
                '}';
    }
}
