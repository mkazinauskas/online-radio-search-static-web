package com.modzo.ors.web.components.analytics;

public class GoogleAnalyticsData {

    private final boolean enabled;

    private final String userId;

    public GoogleAnalyticsData(boolean enabled, String userId) {
        this.enabled = enabled;
        this.userId = userId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getUserId() {
        return userId;
    }
}
