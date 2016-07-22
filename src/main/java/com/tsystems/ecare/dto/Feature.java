package com.tsystems.ecare.dto;

import java.math.BigDecimal;

/**
 * JSON serializable DTO containing Feature data.
 */
public class Feature {

    private Long id;

    private String title;
    private String description;
    private BigDecimal additionFee;
    private BigDecimal monthlyFee;

    public Feature() {
        // empty constructor to instantiate object from JSON
    }

    public Feature(Long id, String title, String description, BigDecimal additionFee, BigDecimal monthlyFee) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.additionFee = additionFee;
        this.monthlyFee = monthlyFee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAdditionFee() {
        return additionFee;
    }

    public void setAdditionFee(BigDecimal additionFee) {
        this.additionFee = additionFee;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }
}
