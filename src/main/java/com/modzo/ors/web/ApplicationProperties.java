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

        @NotNull
        private final String clientId;

        private final boolean mainPageEnabled;

        private final boolean radioStationTopEnabled;

        private final boolean radioStationDownEnabled;

        private final boolean searchPageTopEnabled;

        public GoogleAds(boolean enabled,
                         @NotNull String clientId,
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

    @NotBlank
    private final String apiUrl;

    @NotNull
    private final Sitemap sitemap;

    @NotNull
    private final GoogleAds googleAds;

    @NotNull
    private final MobileApp mobileApp;

    @NotNull
    private final AddThis addThis;

    public ApplicationProperties(String apiUrl,
                                 Sitemap sitemap,
                                 GoogleAds googleAds,
                                 MobileApp mobileApp,
                                 AddThis addThis) {
        this.apiUrl = apiUrl;
        this.sitemap = sitemap;
        this.googleAds = googleAds;
        this.mobileApp = mobileApp;
        this.addThis = addThis;
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

    public MobileApp getMobileApp() {
        return mobileApp;
    }

    public AddThis getAddThis() {
        return addThis;
    }
}
