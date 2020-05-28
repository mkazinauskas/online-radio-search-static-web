package com.modzo.ors.web.sitemap.root;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class SitemapRootController {

    private final SitemapRootService sitemapRootService;

    SitemapRootController(SitemapRootService sitemapRootService) {
        this.sitemapRootService = sitemapRootService;
    }

    @GetMapping(value = "/sitemaps/index.xml", produces = "application/xml;charset=utf-8")
    ResponseEntity<String> sitemapRoot() {
        String sitemap = sitemapRootService.rootSitemap();
        return ResponseEntity.ok().body(sitemap);
    }
}
