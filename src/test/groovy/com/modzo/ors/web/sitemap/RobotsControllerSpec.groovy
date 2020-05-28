package com.modzo.ors.web.sitemap

import com.modzo.ors.web.IntegrationSpec
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class RobotsControllerSpec extends IntegrationSpec {

    void 'should retrieve robots txt'() {
        when:
            ResponseEntity<String> result = restTemplate.getForEntity('/robots.txt', String)
        then:
            result.statusCode == HttpStatus.OK
        and:
            result.body == getClass().getResource('/data/robots.txt').text
    }

}
