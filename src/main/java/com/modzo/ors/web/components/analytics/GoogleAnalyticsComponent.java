package com.modzo.ors.web.components.analytics;

import com.modzo.ors.web.ApplicationProperties;
import com.modzo.ors.web.components.CommonComponent;
import com.modzo.ors.web.components.ComponentType;
import org.springframework.stereotype.Component;

@Component
class GoogleAnalyticsComponent implements CommonComponent {

    private final ApplicationProperties.GoogleAnalytics properties;

    GoogleAnalyticsComponent(ApplicationProperties applicationProperties) {
        this.properties = applicationProperties.getGoogleAnalytics();
    }

    @Override
    public ComponentType type() {
        return ComponentType.GOOGLE_ANALYTICS;
    }

    @Override
    public GoogleAnalyticsData data() {
        return new GoogleAnalyticsData(properties.isEnabled(), properties.getUserId());
    }

}
