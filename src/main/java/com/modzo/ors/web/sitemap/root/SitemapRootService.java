package com.modzo.ors.web.sitemap.root;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.math.IntMath;
import com.modzo.ors.web.ApplicationProperties;
import com.modzo.ors.web.api.radio.stations.RadioStationResponse;
import com.modzo.ors.web.api.radio.stations.RadioStationsClient;
import com.rainerhahnekamp.sneakythrow.Sneaky;
import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl.Options;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Optional.ofNullable;

@Component
class SitemapRootService {

    private static final String ROOT_SITEMAP_KEY = "ROOT";

    private final Cache<String, String> cache;

    private final RadioStationsClient radioStationsClient;

    private final ApplicationProperties properties;

    SitemapRootService(RadioStationsClient radioStationsClient, ApplicationProperties properties) {
        this.radioStationsClient = radioStationsClient;
        this.cache = Caffeine.newBuilder()
                .expireAfterWrite(properties.getSitemap().getCacheTime())
                .maximumSize(1)
                .build();
        this.properties = properties;
    }

    String rootSitemap() {
        return ofNullable(cache.getIfPresent(ROOT_SITEMAP_KEY))
                .orElseGet(this::buildAndCacheSitemap);
    }

    private String buildAndCacheSitemap() {
        String sitemap = buildSitemap();
        cache.put(ROOT_SITEMAP_KEY, sitemap);
        return sitemap;
    }

    private String buildSitemap() {
        int sitemapCount = sitemapCount();
        String domain = properties.getSitemap().getDomain();

        WebSitemapGenerator wsg = Sneaky.sneak(() -> new WebSitemapGenerator(domain));
        IntStream.rangeClosed(1, sitemapCount)
                .forEach(number -> addSitemaps(domain, wsg, number));

        List<String> sitemaps = wsg.writeAsStrings();
        Assert.isTrue(sitemaps.size() == 1, "There should be only one sitemap");
        return sitemaps.get(0);
    }

    private void addSitemaps(String domain, WebSitemapGenerator wsg, int number) {
        Options url = Sneaky.sneak(() -> new Options(domain + "/sitemaps/sitemap-" + number + ".xml"));
        url.changeFreq(ChangeFreq.WEEKLY);
        url.lastMod(new Date());
        url.priority(1.0);
        wsg.addUrl(url.build());
    }

    private int sitemapCount() {
        int radioStationsCount = (int) radioStationsCount();
        int itemsPerPage = properties.getSitemap().getItemsPerPage();
        return IntMath.divide(radioStationsCount, itemsPerPage, RoundingMode.CEILING);
    }

    private long radioStationsCount() {
        PagedModel<EntityModel<RadioStationResponse>> radioStations = radioStationsClient.getRadioStations(0, 1);
        return radioStations.getMetadata().getTotalElements();
    }

}
