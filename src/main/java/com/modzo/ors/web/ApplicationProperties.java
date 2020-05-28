package com.modzo.ors.web;

import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@ConfigurationProperties(prefix = "application")
@Validated
@ConstructorBinding
public class ApplicationProperties {

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

    public ApplicationProperties(String apiUrl, Sitemap sitemap) {
        this.apiUrl = apiUrl;
        this.sitemap = sitemap;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public Sitemap getSitemap() {
        return sitemap;
    }
}
