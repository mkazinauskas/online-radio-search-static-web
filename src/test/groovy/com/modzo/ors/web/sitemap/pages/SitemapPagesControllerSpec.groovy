package com.modzo.ors.web.sitemap.pages

import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.modzo.ors.web.IntegrationSpec
import org.junit.Rule
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class SitemapPagesControllerSpec extends IntegrationSpec {

    @Rule
    WireMockRule wireMockRule = wireMockRule('stubs/sitemap/pages')

    void 'should retrieve root sitemap'() {
        when:
            ResponseEntity<String> result = restTemplate.getForEntity('/sitemaps/sitemap-1.xml', String)
        then:
            result.statusCode == HttpStatus.OK
        and:
            String xml = result.body
            Node node = new XmlParser().parseText(xml)
            with(node.url[0]) {
                loc.text() == 'https://web.onlineradiosearch.com/radio-stations/the-hits-radio/5'
                changefreq.text() == 'weekly'
                priority.text() == '0.5'
                lastmod.text() != null
            }
            with(node.url[1]) {
                loc.text() == 'https://web.onlineradiosearch.com/radio-stations/frisky-chill-feelin-frisky/6'
                changefreq.text() == 'weekly'
                priority.text() == '0.5'
                lastmod.text() != null
            }
            with(node.url[2]) {
                loc.text() == 'https://web.onlineradiosearch.com/radio-stations/radio-roermondialive/65'
                changefreq.text() == 'weekly'
                priority.text() == '0.5'
                lastmod.text() != null
            }
            with(node.url[3]) {
                loc.text() == 'https://web.onlineradiosearch.com/radio-stations/web-radio-dnor/7'
                changefreq.text() == 'weekly'
                priority.text() == '0.5'
                lastmod.text() != null
            }
    }

    void 'should cache sitemap'() {
        when:
            String firstResult = restTemplate.getForEntity('/sitemaps/sitemap-1.xml', String).body
        and:
            String secondResult = restTemplate.getForEntity('/sitemaps/sitemap-1.xml', String).body
        then:
            firstResult == secondResult
    }

}
