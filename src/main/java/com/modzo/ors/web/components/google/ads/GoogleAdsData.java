package com.modzo.ors.web.components.google.ads;

public class GoogleAdsData {

    private final boolean enabled;

    private final String clientId;

    public GoogleAdsData(boolean enabled,
                         String clientId) {
        this.enabled = enabled;
        this.clientId = clientId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getClientId() {
        return clientId;
    }

}
