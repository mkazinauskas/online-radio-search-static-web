package com.modzo.ors.web.sitemap.root

import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.modzo.ors.web.IntegrationSpec
import org.junit.Rule
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class SitemapRootControllerSpec extends IntegrationSpec {

    @Rule
    WireMockRule wireMockRule = wireMockRule('stubs')

    void 'should retrieve root sitemap'() {
        when:
            ResponseEntity<String> result = restTemplate.getForEntity('/sitemaps/index.xml', String)
        then:
            result.statusCode == HttpStatus.OK
        and:
            String xml = result.body
            Node node = new XmlParser().parseText(xml)
            with(node.url[0]) {
                loc.text() == 'https://web.onlineradiosearch.com/sitemaps/sitemap-1.xml'
                changefreq.text() == 'weekly'
                priority.text() == '1.0'
                lastmod.text() != null
            }
            with(node.url[1]) {
                loc.text() == 'https://web.onlineradiosearch.com/sitemaps/sitemap-2.xml'
                changefreq.text() == 'weekly'
                priority.text() == '1.0'
                lastmod.text() != null
            }
            with(node.url[2]) {
                loc.text() == 'https://web.onlineradiosearch.com/sitemaps/sitemap-3.xml'
                changefreq.text() == 'weekly'
                priority.text() == '1.0'
                lastmod.text() != null
            }
    }

    void 'should cache sitemap'() {
        when:
            String firstResult = restTemplate.getForEntity('/sitemaps/index.xml', String).body
        and:
            String secondResult = restTemplate.getForEntity('/sitemaps/index.xml', String).body
        then:
            firstResult == secondResult
    }

}
