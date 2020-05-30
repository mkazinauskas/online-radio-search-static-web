package com.modzo.ors.web.components.google.ads;

import com.modzo.ors.web.ApplicationProperties;
import org.springframework.stereotype.Component;

@Component
public class GoogleAdsComponent {
    private final ApplicationProperties.GoogleAds properties;

    public GoogleAdsComponent(ApplicationProperties applicationProperties) {
        this.properties = applicationProperties.getGoogleAds();
    }

    public GoogleAdsData retrieve() {
        return new GoogleAdsData(
                properties.isEnabled(),
                properties.getClientId(),
                properties.isMainPageEnabled(),
                properties.isRadioStationTopEnabled(),
                properties.isRadioStationDownEnabled()
        );
    }
}
