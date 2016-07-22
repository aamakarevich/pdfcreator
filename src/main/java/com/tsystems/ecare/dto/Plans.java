package com.tsystems.ecare.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON serializable DTO containing bench of plans data.
 */
public class Plans {

    private List<Plan> plans;

    public Plans() {}

    public List<Plan> getPlans() {
        if (plans == null) {
            plans = new ArrayList<>();
        }
        return plans;
    }
}
