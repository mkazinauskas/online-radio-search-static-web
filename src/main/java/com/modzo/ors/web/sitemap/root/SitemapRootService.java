package com.modzo.ors.web.sitemap.root;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.modzo.ors.web.ApplicationProperties;
import com.modzo.ors.web.api.radio.stations.RadioStationResponse;
import com.modzo.ors.web.api.radio.stations.RadioStationsClient;
import com.rainerhahnekamp.sneakythrow.Sneaky;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class SitemapRootService {

    private final static String ROOT_SITEMAP_KEY = "ROOT";

    private final Cache<String, String> cache;

    private final RadioStationsClient radioStationsClient;

    private final String domain;

    SitemapRootService(RadioStationsClient radioStationsClient, ApplicationProperties properties) {
        this.radioStationsClient = radioStationsClient;
        this.cache = Caffeine.newBuilder()
                .expireAfterWrite(properties.getSitemap().getCacheTime())
                .maximumSize(1)
                .build();
        this.domain = properties.getSitemap().getDomain();
    }

    String rootSitemap() {
        return Optional.ofNullable(cache.getIfPresent(ROOT_SITEMAP_KEY))
                .orElseGet(this::buildAndCacheSitemap);
    }

    private String buildAndCacheSitemap() {
        PagedModel<EntityModel<RadioStationResponse>> radioStations = radioStationsClient.getRadioStations(0);

        String sitemap = buildSitemap(); //String.valueOf(radioStations.getMetadata().getTotalElements());
        cache.put(ROOT_SITEMAP_KEY, sitemap);
        return sitemap;
    }

    private String buildSitemap() {
        WebSitemapGenerator wsg = Sneaky.sneak(() -> new WebSitemapGenerator(domain));
        wsg.addUrl(new WebSitemapUrl(Sneaky.sneak(() -> new WebSitemapUrl.Options(domain + "/radio-stations/musicarama/43158"))));
        List<String> sitemaps = wsg.writeAsStrings();
        return wsg.writeSitemapsWithIndexAsString();
    }

}
