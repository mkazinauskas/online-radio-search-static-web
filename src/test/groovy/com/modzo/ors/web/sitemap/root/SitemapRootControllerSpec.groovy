package com.modzo.ors.web.sitemap.root

import com.modzo.ors.web.IntegrationSpec
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class SitemapRootControllerSpec extends IntegrationSpec {

    void 'should retrieve root sitemap'() {
        when:
            ResponseEntity<String> result = restTemplate.getForEntity('/sitemap.xml', String)
        then:
            result.statusCode == HttpStatus.OK
        and:
            result.body == '16454'
    }

}
