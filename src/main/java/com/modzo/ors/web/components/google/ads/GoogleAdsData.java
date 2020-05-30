package com.modzo.ors.web.components.google.ads;

public class GoogleAdsData {

    private final boolean enabled;

    private final String clientId;

    private final boolean mainPageEnabled;

    private final boolean radioStationTopEnabled;

    private final boolean radioStationDownEnabled;

    public GoogleAdsData(boolean enabled,
                         String clientId,
                         boolean mainPageEnabled,
                         boolean radioStationTopEnabled,
                         boolean radioStationDownEnabled) {
        this.enabled = enabled;
        this.clientId = clientId;
        this.mainPageEnabled = mainPageEnabled;
        this.radioStationTopEnabled = radioStationTopEnabled;
        this.radioStationDownEnabled = radioStationDownEnabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getClientId() {
        return clientId;
    }

    public boolean isMainPageEnabled() {
        return mainPageEnabled;
    }

    public boolean isRadioStationTopEnabled() {
        return radioStationTopEnabled;
    }

    public boolean isRadioStationDownEnabled() {
        return radioStationDownEnabled;
    }

}
