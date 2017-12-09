package com.jzheadley.swifey.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int discountId;
    private String discountCode;
    private int discountAmount;
    @ManyToMany
    @JoinTable(name = "meal_discounts",
            joinColumns = {@JoinColumn(name = "discountId")},
            inverseJoinColumns = {@JoinColumn(name = "mealId")}
    )
    private List<Meal> applicableMeals;

    public Discount() {
    }

    public Discount(String discountCode, int discountAmount, List<Meal> applicableMeals) {
        this.discountCode = discountCode;
        this.discountAmount = discountAmount;
        this.applicableMeals = applicableMeals;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public List<Meal> getApplicableMeals() {
        return applicableMeals;
    }

    public void setApplicableMeals(List<Meal> applicableMeals) {
        this.applicableMeals = applicableMeals;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discountId=" + discountId +
                ", discountCode='" + discountCode + '\'' +
                ", applicableMeals=" + applicableMeals +
                '}';
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }
}
