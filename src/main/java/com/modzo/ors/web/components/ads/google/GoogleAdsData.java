package com.modzo.ors.web.components.ads.google;

public class GoogleAdsData {

    private final boolean enabled;

    private final String clientId;

    private final boolean mainPageEnabled;

    private final boolean radioStationTopEnabled;

    private final boolean radioStationDownEnabled;

    private final boolean searchPageTopEnabled;

    public GoogleAdsData(boolean enabled,
                         String clientId,
                         boolean mainPageEnabled,
                         boolean radioStationTopEnabled,
                         boolean radioStationDownEnabled,
                         boolean searchPageTopEnabled) {
        this.enabled = enabled;
        this.clientId = clientId;
        this.mainPageEnabled = mainPageEnabled;
        this.radioStationTopEnabled = radioStationTopEnabled;
        this.radioStationDownEnabled = radioStationDownEnabled;
        this.searchPageTopEnabled = searchPageTopEnabled;
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

    public boolean isSearchPageTopEnabled() {
        return searchPageTopEnabled;
    }
}
