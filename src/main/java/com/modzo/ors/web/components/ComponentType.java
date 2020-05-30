package com.modzo.ors.web.components;

public enum ComponentType {
    LATEST_SEARCHES("commonLatestSearches"),
    PAGE_TITLE("commonPageTitle"),
    DESCRIPTION("commonDescription"),
    KEYWORDS("commonKeywords"),
    GOOGLE_ADS("googleAds");

    private final String type;

    ComponentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
