package com.modzo.ors.web;

import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@ConfigurationProperties(prefix = "application")
@Validated
@ConstructorBinding
public class ApplicationProperties {

    @ConstructorBinding
    public static class GoogleAds {

        private final boolean enabled;

        private final String clientId;

        private final boolean mainPageEnabled;

        private final boolean radioStationTopEnabled;

        private final boolean radioStationDownEnabled;

        private final boolean searchPageTopEnabled;

        public GoogleAds(boolean enabled,
                         String clientId,
                         boolean mainPageEnabled,
                         boolean radioStationTopEnabled,
                         boolean radioStationDownEnabled,
                         boolean searchPageTopEnabled) {
            this.enabled = enabled;
            this.clientId = clientId;
            this.mainPageEnabled = mainPageEnabled;
            this.radioStationTopEnabled = radioStationTopEnabled;
            this.radioStationDownEnabled = radioStationDownEnabled;
            this.searchPageTopEnabled = searchPageTopEnabled;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public String getClientId() {
            return clientId;
        }

        public boolean isMainPageEnabled() {
            return mainPageEnabled;
        }

        public boolean isRadioStationTopEnabled() {
            return radioStationTopEnabled;
        }

        public boolean isRadioStationDownEnabled() {
            return radioStationDownEnabled;
        }

        public boolean isSearchPageTopEnabled() {
            return searchPageTopEnabled;
        }
    }

    @ConstructorBinding
    public static class PropellerAds {

        private final boolean enabled;

        private final String domainVerificationCode;

        public PropellerAds(boolean enabled, String domainVerificationCode) {
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

    @ConstructorBinding
    public static class Sitemap {

        @NotNull
        private final Integer itemsPerPage;

        @NotNull
        private final Duration cacheTime;

        @URL
        @NotNull
        private final String domain;

        public Sitemap(Integer itemsPerPage,
                       Duration cacheTime,
                       String domain) {
            this.itemsPerPage = itemsPerPage;
            this.cacheTime = cacheTime;
            this.domain = domain;
        }

        public Integer getItemsPerPage() {
            return itemsPerPage;
        }

        public Duration getCacheTime() {
            return cacheTime;
        }

        public String getDomain() {
            return domain;
        }
    }

    @ConstructorBinding
    public static class MobileApp {

        @NotNull
        @URL
        private final String link;

        public MobileApp(String link) {
            this.link = link;
        }

        public String getLink() {
            return link;
        }
    }

    @ConstructorBinding
    public static class AddThis {

        private final boolean enabled;

        private final String scriptLocation;

        public AddThis(boolean enabled, String scriptLocation) {
            this.enabled = enabled;
            this.scriptLocation = scriptLocation;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public String getScriptLocation() {
            return scriptLocation;
        }
    }

    @ConstructorBinding
    public static class Disqus {

        private final boolean enabled;

        private final String urlPrefix;

        private final String accountName;

        public Disqus(boolean enabled,
                      String urlPrefix,
                      String accountName) {
            this.enabled = enabled;
            this.urlPrefix = urlPrefix;
            this.accountName = accountName;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public String getUrlPrefix() {
            return urlPrefix;
        }

        public String getAccountName() {
            return accountName;
        }
    }

    @ConstructorBinding
    public static class GoogleAnalytics {

        private final boolean enabled;

        private final String userId;

        public GoogleAnalytics(boolean enabled, String userId) {
            this.enabled = enabled;
            this.userId = userId;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public String getUserId() {
            return userId;
        }
    }

    @NotBlank
    private final String apiUrl;

    @NotNull
    private final Sitemap sitemap;

    @NotNull
    private final GoogleAds googleAds;

    @NotNull
    private final PropellerAds propellerAds;

    @NotNull
    private final MobileApp mobileApp;

    @NotNull
    private final AddThis addThis;

    @NotNull
    private final Disqus disqus;

    @NotNull
    private final GoogleAnalytics googleAnalytics;

    public ApplicationProperties(String apiUrl,
                                 Sitemap sitemap,
                                 GoogleAds googleAds,
                                 PropellerAds propellerAds,
                                 MobileApp mobileApp,
                                 AddThis addThis,
                                 Disqus disqus,
                                 GoogleAnalytics googleAnalytics) {
        this.apiUrl = apiUrl;
        this.sitemap = sitemap;
        this.googleAds = googleAds;
        this.propellerAds = propellerAds;
        this.mobileApp = mobileApp;
        this.addThis = addThis;
        this.disqus = disqus;
        this.googleAnalytics = googleAnalytics;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public Sitemap getSitemap() {
        return sitemap;
    }

    public GoogleAds getGoogleAds() {
        return googleAds;
    }

    public PropellerAds getPropellerAds() {
        return propellerAds;
    }

    public MobileApp getMobileApp() {
        return mobileApp;
    }

    public AddThis getAddThis() {
        return addThis;
    }

    public Disqus getDisqus() {
        return disqus;
    }

    public GoogleAnalytics getGoogleAnalytics() {
        return googleAnalytics;
    }
}
