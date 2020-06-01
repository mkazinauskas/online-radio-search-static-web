package com.modzo.ors.web.radio.stations

import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.modzo.ors.web.IntegrationSpec
import org.junit.Rule
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class RadioStationDownloadControllerSpec extends IntegrationSpec {

    @Rule
    WireMockRule wireMockRule = wireMockRule('stubs/radio-stations/download')

    void 'should download radio station playlist'() {
        when:
            ResponseEntity<String> result = restTemplate.getForEntity(
                    '/radio-stations/the-hits-radio/5/streams/1/pls',
                    String
            )
        then:
            result.statusCode == HttpStatus.OK
            result.headers.get('Content-Type').first().contains('audio/x-scpls')
        and:
            result.body.contains('"The Hits" Radio')
            result.body.contains('http://listen.radionomy.com:80/-TheHits-Radio')
    }
}
