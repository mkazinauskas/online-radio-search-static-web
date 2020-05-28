package com.modzo.ors.web;

import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    @NotBlank
    private String apiUrl;

    @NotNull
    private Sitemap sitemap;

    public static class Sitemap {

        @NotNull
        private Integer itemsPerPage;

        @NotNull
        private Duration cacheTime;

        @URL
        @NotNull
        private String domain;

        public Integer getItemsPerPage() {
            return itemsPerPage;
        }

        public void setItemsPerPage(Integer itemsPerPage) {
            this.itemsPerPage = itemsPerPage;
        }

        public Duration getCacheTime() {
            return cacheTime;
        }

        public void setCacheTime(Duration cacheTime) {
            this.cacheTime = cacheTime;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Sitemap getSitemap() {
        return sitemap;
    }

    public void setSitemap(Sitemap sitemap) {
        this.sitemap = sitemap;
    }
}
