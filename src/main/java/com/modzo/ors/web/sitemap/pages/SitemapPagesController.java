package com.modzo.ors.web.sitemap.pages;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.Min;

@Controller
class SitemapPagesController {

    private final SitemapPagesService sitemapPagesService;

    SitemapPagesController(SitemapPagesService sitemapPagesService) {
        this.sitemapPagesService = sitemapPagesService;
    }

    @GetMapping(value = "/sitemaps/sitemap-{page}.xml")
    ResponseEntity<String> sitemapRoot(@PathVariable("page") @Min(1L) int page) {
        String sitemap = sitemapPagesService.sitemap(page);
        return ResponseEntity.ok().body(sitemap);
    }
}
