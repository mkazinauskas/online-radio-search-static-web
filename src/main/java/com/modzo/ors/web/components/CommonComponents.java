package com.modzo.ors.web.components;

import com.modzo.ors.web.components.google.ads.GoogleAdsComponent;
import com.modzo.ors.web.components.latest.searches.LatestSearchesComponent;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CommonComponents {

    private final LatestSearchesComponent latestSearchesComponent;

    private final GoogleAdsComponent googleAdsComponent;

    public CommonComponents(LatestSearchesComponent latestSearchesComponent,
                            GoogleAdsComponent googleAdsComponent) {
        this.latestSearchesComponent = latestSearchesComponent;
        this.googleAdsComponent = googleAdsComponent;
    }

    public Map<String, Object> load() {
        return Map.of(
                ComponentType.LATEST_SEARCHES.getType(), latestSearchesComponent.retrieve(),
                ComponentType.GOOGLE_ADS.getType(), googleAdsComponent.retrieve()
        );
    }
}
