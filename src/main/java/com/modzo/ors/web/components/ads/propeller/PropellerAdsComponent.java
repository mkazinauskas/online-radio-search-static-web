package com.modzo.ors.web.components.ads.propeller;

import com.modzo.ors.web.ApplicationProperties;
import com.modzo.ors.web.components.CommonComponent;
import com.modzo.ors.web.components.ComponentType;
import org.springframework.stereotype.Component;

@Component
public class PropellerAdsComponent implements CommonComponent {
    private final ApplicationProperties.PropellerAds properties;

    public PropellerAdsComponent(ApplicationProperties applicationProperties) {
        this.properties = applicationProperties.getPropellerAds();
    }

    @Override
    public ComponentType type() {
        return ComponentType.PROPELLER_ADS;
    }

    @Override
    public PropellerAdsData data() {
        return new PropellerAdsData(
                properties.isEnabled(),
                properties.getDomainVerificationCode()
        );
    }
}
