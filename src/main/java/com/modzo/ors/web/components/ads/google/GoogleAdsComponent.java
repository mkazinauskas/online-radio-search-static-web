package com.modzo.ors.web.components.ads.google;

import com.modzo.ors.web.ApplicationProperties;
import com.modzo.ors.web.components.CommonComponent;
import com.modzo.ors.web.components.ComponentType;
import org.springframework.stereotype.Component;

@Component
public class GoogleAdsComponent implements CommonComponent {
    private final ApplicationProperties.GoogleAds properties;

    public GoogleAdsComponent(ApplicationProperties applicationProperties) {
        this.properties = applicationProperties.getGoogleAds();
    }

    @Override
    public ComponentType type() {
        return ComponentType.GOOGLE_ADS;
    }

    @Override
    public GoogleAdsData data() {
        return new GoogleAdsData(
                properties.isEnabled(),
                properties.getClientId(),
                properties.isMainPageEnabled(),
                properties.isRadioStationTopEnabled(),
                properties.isRadioStationDownEnabled(),
                properties.isSearchPageTopEnabled()
        );
    }
}
