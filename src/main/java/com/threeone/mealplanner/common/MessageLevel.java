package com.threeone.mealplanner.common;

/**
 * 
 * @author bohan
 *
 */
public enum MessageLevel {
    success("success"),
    info("info"),
    warning("warning"),
    danger("danger");

    private String level;

    MessageLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
