package com.modzo.ors.web.components.ads.propeller;

public class PropellerAdsData {

    private final boolean enabled;

    private final String domainVerificationCode;

    public PropellerAdsData(boolean enabled, String domainVerificationCode) {
        this.enabled = enabled;
        this.domainVerificationCode = domainVerificationCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getDomainVerificationCode() {
        return domainVerificationCode;
    }
}
