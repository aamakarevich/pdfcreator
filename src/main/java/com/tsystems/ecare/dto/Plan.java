package com.tsystems.ecare.dto;

import java.math.BigDecimal;

/**
 * JSON serializable DTO containing Plan data.
 */
public class Plan {

    private Long id;

    private String title;
    private String description;
    private BigDecimal monthlyFee;

    public Plan() {}

    public Plan(Long id, String title, String description, BigDecimal monthlyFee) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }
}
