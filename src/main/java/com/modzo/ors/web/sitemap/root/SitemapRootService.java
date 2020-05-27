package com.modzo.ors.web.sitemap.root;

import com.modzo.ors.web.api.radio.stations.RadioStationResponse;
import com.modzo.ors.web.api.radio.stations.RadioStationsClient;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

@Component
class SitemapRootService {

    private final RadioStationsClient radioStationsClient;

    SitemapRootService(RadioStationsClient radioStationsClient) {
        this.radioStationsClient = radioStationsClient;
    }

    String rootSitemap(){
        PagedModel<EntityModel<RadioStationResponse>> radioStations = radioStationsClient.getRadioStations(0);
        return String.valueOf(radioStations.getMetadata().getTotalElements());
    }

}
