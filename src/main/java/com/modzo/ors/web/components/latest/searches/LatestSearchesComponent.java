package com.modzo.ors.web.components.latest.searches;

import com.modzo.ors.web.api.latest.searches.LastSearchResponse;
import com.modzo.ors.web.api.latest.searches.LatestSearchesClient;
import com.modzo.ors.web.components.CommonComponent;
import com.modzo.ors.web.components.ComponentType;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class LatestSearchesComponent implements CommonComponent {

    private final LatestSearchesClient latestSearchesClient;

    LatestSearchesComponent(LatestSearchesClient latestSearchesClient) {
        this.latestSearchesClient = latestSearchesClient;
    }

    @Override
    public ComponentType type() {
        return ComponentType.LATEST_SEARCHES;
    }

    @Override
    public Object data() {
        PagedModel<EntityModel<LastSearchResponse>> latestSearches = latestSearchesClient.getLatestSearches();
        List<LatestSearchesData.Query> searches = latestSearches.getContent().stream()
                .map(EntityModel::getContent)
                .filter(Objects::nonNull)
                .map(search -> new LatestSearchesData.Query(search.getQuery(), search.getType()))
                .collect(Collectors.toList());
        return List.of(new LatestSearchesData(searches));
    }
}
