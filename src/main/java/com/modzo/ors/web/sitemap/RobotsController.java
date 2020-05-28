package com.modzo.ors.web.sitemap;

import com.modzo.ors.web.ApplicationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class RobotsController {
    private final ApplicationProperties properties;

    public RobotsController(ApplicationProperties properties) {
        this.properties = properties;
    }

    @GetMapping(value = "/robots.txt")
    ResponseEntity<String> sitemapRoot() {
        StringBuilder builder = new StringBuilder();
        builder.append("User-agent: *\n");
        builder.append("Allow: /\n");
        builder.append("\n");
        builder.append("Sitemap: ");
        builder.append(properties.getSitemap().getDomain());
        builder.append("/sitemaps/index.xml");
        return ResponseEntity.ok().body(builder.toString());
    }
}
