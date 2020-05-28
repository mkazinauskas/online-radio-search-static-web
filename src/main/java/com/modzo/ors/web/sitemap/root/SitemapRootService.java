package com.modzo.ors.web.sitemap.root;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.modzo.ors.web.ApplicationProperties;
import com.modzo.ors.web.api.radio.stations.RadioStationResponse;
import com.modzo.ors.web.api.radio.stations.RadioStationsClient;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class SitemapRootService {

    private final static String ROOT_SITEMAP_KEY = "ROOT";

    private final Cache<String, String> cache;

    private final RadioStationsClient radioStationsClient;

    SitemapRootService(RadioStationsClient radioStationsClient, ApplicationProperties properties) {
        this.radioStationsClient = radioStationsClient;
        this.cache = Caffeine.newBuilder()
                .expireAfterWrite(properties.getSitemap().getCacheTime())
                .maximumSize(1)
                .build();
    }

    String rootSitemap() {
        return Optional.ofNullable(cache.getIfPresent(ROOT_SITEMAP_KEY))
                .orElseGet(this::buildAndCacheSitemap);
    }

    private String buildAndCacheSitemap() {
        PagedModel<EntityModel<RadioStationResponse>> radioStations = radioStationsClient.getRadioStations(0);

        String sitemap = String.valueOf(radioStations.getMetadata().getTotalElements());
        cache.put(ROOT_SITEMAP_KEY, sitemap);
        return sitemap;
    }

}
