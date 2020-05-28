package com.modzo.ors.web.sitemap.pages;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.modzo.ors.web.ApplicationProperties;
import com.modzo.ors.web.api.radio.stations.RadioStationResponse;
import com.modzo.ors.web.api.radio.stations.RadioStationsClient;
import com.modzo.ors.web.utils.SeoText;
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
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
class SitemapPagesService {

    private final Cache<Integer, String> cache;

    private final RadioStationsClient radioStationsClient;

    private final ApplicationProperties properties;

    SitemapPagesService(RadioStationsClient radioStationsClient, ApplicationProperties properties) {
        this.radioStationsClient = radioStationsClient;
        this.cache = Caffeine.newBuilder()
                .expireAfterWrite(properties.getSitemap().getCacheTime())
                .maximumSize(500)
                .build();
        this.properties = properties;
    }

    String sitemap(int page) {
        return ofNullable(cache.getIfPresent(page))
                .orElseGet(() -> buildAndCacheSitemap(page));
    }

    private String buildAndCacheSitemap(int page) {
        String sitemap = buildSitemap(page);
        cache.put(page, sitemap);
        return sitemap;
    }

    private String buildSitemap(int page) {
        int totalPages = totalPages();
        Preconditions.checkArgument(totalPages >= page, "Page does not exist");

        List<RadioStationResponse> radioStations = getRadioStations(page);

        String domain = properties.getSitemap().getDomain();

        WebSitemapGenerator wsg = Sneaky.sneak(() -> new WebSitemapGenerator(domain));
        radioStations.forEach(station -> addPage(domain, wsg, station));

        List<String> sitemaps = wsg.writeAsStrings();
        Assert.isTrue(sitemaps.size() == 1, "There should be only one sitemap");
        return sitemaps.get(0);
    }

    private void addPage(String domain, WebSitemapGenerator wsg, RadioStationResponse station) {
        String path = String.format("/radio-stations/%s/%s",
                SeoText.from(station.getTitle()),
                station.getId()
        );
        Options url = Sneaky.sneak(() -> new Options(domain + path));
        url.changeFreq(ChangeFreq.WEEKLY);
        url.lastMod(new Date());
        url.priority(0.5);
        wsg.addUrl(url.build());
    }

    private List<RadioStationResponse> getRadioStations(int page) {
        int itemsPerPage = properties.getSitemap().getItemsPerPage();
        var radioStations = radioStationsClient.getRadioStations(page - 1, itemsPerPage);
        return radioStations.getContent().stream()
                .map(EntityModel::getContent)
                .collect(Collectors.toList());
    }

    private int totalPages() {
        int radioStationsCount = (int) radioStationsCount();
        int itemsPerPage = properties.getSitemap().getItemsPerPage();
        return IntMath.divide(radioStationsCount, itemsPerPage, RoundingMode.CEILING);
    }

    private long radioStationsCount() {
        PagedModel<EntityModel<RadioStationResponse>> radioStations = radioStationsClient.getRadioStations(0, 1);
        return radioStations.getMetadata().getTotalElements();
    }

}
