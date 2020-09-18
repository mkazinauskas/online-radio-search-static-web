package com.modzo.ors.web.search.genre;

import com.modzo.ors.web.components.CommonComponents;
import com.modzo.ors.web.components.ComponentType;
import com.modzo.ors.web.utils.SeoText;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SearchByGenreController {

    private final CommonComponents commonComponents;

    private final SearchByGenreService searchByGenreService;

    public SearchByGenreController(CommonComponents commonComponents, SearchByGenreService searchByGenreService) {
        this.commonComponents = commonComponents;
        this.searchByGenreService = searchByGenreService;
    }

    @GetMapping("/search/by-genre/{query}")
    public ModelAndView searchByGenre(@PathVariable("query") String query,
                                     Pageable pageable) {
        String queryText = SeoText.revert(query);

        Map<String, Object> items = new HashMap<>(commonComponents.load());
        items.put(ComponentType.PAGE_TITLE.getName(), queryText + " results of popular genres"
                + " at OnlineRadioSearch.com. Page " + (pageable.getPageNumber() + 1));

        items.put(ComponentType.DESCRIPTION.getName(), queryText + " genre search results. Browse "
                + queryText + " genre search results. Page " + (pageable.getPageNumber() + 1)
        );
        items.put(ComponentType.KEYWORDS.getName(),
                query.replaceAll("-", ", ")
                        + ", search, genre, mp3, aac, wmv, streaming, dnas, music, "
                        + "m3u, pls"
        );
        items.put("seoQuery", SeoText.from(query));
        items.put("query", queryText);
        items.put("foundGenres", searchByGenreService.retrieve(queryText, pageable));
        items.put("submenu-search-by-genre", true);
        return new ModelAndView("search/by-genre/index", items);
    }
}
