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

        public GoogleAds(boolean enabled,
                         @NotNull String clientId) {
            this.enabled = enabled;
            this.clientId = clientId;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public String getClientId() {
            return clientId;
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

    @NotBlank
    private final String apiUrl;

    @NotNull
    private final Sitemap sitemap;

    @NotNull
    private final GoogleAds googleAds;

    public ApplicationProperties(String apiUrl,
                                 Sitemap sitemap,
                                 GoogleAds googleAds) {
        this.apiUrl = apiUrl;
        this.sitemap = sitemap;
        this.googleAds = googleAds;
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
}
