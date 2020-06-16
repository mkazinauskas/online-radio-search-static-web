package com.modzo.ors.web.components;

public enum ComponentType {
    LATEST_SEARCHES("commonLatestSearches"),
    PAGE_TITLE("commonPageTitle"),
    DESCRIPTION("commonDescription"),
    KEYWORDS("commonKeywords"),
    GOOGLE_ADS("googleAds"),
    GOOGLE_ANALYTICS("googleAnalytics"),
    MOBILE_APP("mobileApp");

    private final String name;

    ComponentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
