package com.modzo.ors.web.components.common.model;

import com.modzo.ors.web.api.radio.stations.RadioStationResponse;
import com.modzo.ors.web.api.search.radio.station.SearchRadioStationResultResponse;
import com.modzo.ors.web.utils.SeoText;

public class RadioStationModel {

    private final long id;

    private final String uniqueId;

    private final String title;

    private final String seoTitle;

    private final String website;

    public RadioStationModel(RadioStationResponse response) {
        this.id = response.getId();
        this.uniqueId = response.getUniqueId();
        this.title = response.getTitle();
        this.seoTitle = SeoText.from(response.getTitle());
        this.website = response.getWebsite();
    }

    public RadioStationModel(SearchRadioStationResultResponse response) {
        this.id = response.getId();
        this.uniqueId = response.getUniqueId();
        this.title = response.getTitle();
        this.seoTitle = SeoText.from(response.getTitle());
        this.website = response.getWebsite();
    }

    public long getId() {
        return id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getTitle() {
        return title;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public String getWebsite() {
        return website;
    }

}
