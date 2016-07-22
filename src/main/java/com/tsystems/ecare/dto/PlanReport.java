package com.tsystems.ecare.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JSON serializable DTO containing Plan report data.
 */
public class PlanReport extends Plan {

    private List<Feature> allowedFeatures;
    private long totalContracts;
    private Map<Long, Long> featuresUsers;

    public PlanReport() {}

    public PlanReport(Long id, String title, String description, BigDecimal monthlyFee) {
        super(id, title, description, monthlyFee);
    }

    public List<Feature> getAllowedFeatures() {
        if (allowedFeatures == null) {
            allowedFeatures = new ArrayList<>();
        }
        return allowedFeatures;
    }

    public void setAllowedFeatures(List<Feature> allowedFeatures) {
        this.allowedFeatures = allowedFeatures;
    }

    public long getTotalContracts() {
        return totalContracts;
    }

    public void setTotalContracts(long totalContracts) {
        this.totalContracts = totalContracts;
    }

    public Map<Long, Long> getFeaturesUsers() {
        return featuresUsers;
    }

    public void setFeaturesUsers(Map<Long, Long> featuresUsers) {
        if (featuresUsers == null) {
            featuresUsers = new HashMap<>();
        }
        this.featuresUsers = featuresUsers;
    }


}
