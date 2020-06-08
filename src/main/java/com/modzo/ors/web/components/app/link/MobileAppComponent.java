package com.modzo.ors.web.components.app.link;

import com.modzo.ors.web.ApplicationProperties;
import com.modzo.ors.web.components.CommonComponent;
import com.modzo.ors.web.components.ComponentType;
import org.springframework.stereotype.Component;

@Component
public class MobileAppComponent implements CommonComponent {

    private final ApplicationProperties.MobileApp properties;

    public MobileAppComponent(ApplicationProperties applicationProperties) {
        this.properties = applicationProperties.getMobileApp();
    }

    @Override
    public ComponentType type() {
        return ComponentType.MOBILE_APP;
    }

    @Override
    public MobileAppData data() {
        return new MobileAppData(properties.getLink());
    }

}
