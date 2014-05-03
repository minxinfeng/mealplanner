package com.threeone.mealplanner.model.entity;

public class FoodType {
    private Integer foodtypeid;

    private String foodtypename;

    public Integer getFoodtypeid() {
        return foodtypeid;
    }

    public void setFoodtypeid(Integer foodtypeid) {
        this.foodtypeid = foodtypeid;
    }

    public String getFoodtypename() {
        return foodtypename;
    }

    public void setFoodtypename(String foodtypename) {
        this.foodtypename = foodtypename == null ? null : foodtypename.trim();
    }
}