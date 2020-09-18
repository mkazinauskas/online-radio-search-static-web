package com.modzo.ors.web.search.radio.station

import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.modzo.ors.web.IntegrationSpec
import org.junit.Rule
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class SearchByRadioStationControllerSpec extends IntegrationSpec {

    @Rule
    WireMockRule wireMockRule = wireMockRule('stubs/search/by-radio-station')

    void 'should retrieve search by radio station page'() {
        when:
            ResponseEntity<String> result = restTemplate.getForEntity('/search/by-radio-station/test', String)
        then:
            result.statusCode == HttpStatus.OK
        and:
            result.body.contains('src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>')
            result.body.contains('<span>Some google ads</span>')
        and:
            result.body.contains('<h1 class="title">Radio stations by title "test"</h1>')
            result.body.contains('<a href="/radio-stations/test-casthostnet/33186">')
        and:
            result.body.contains('<h2>Latest searches</h2>')
            result.body.contains('<a href="/search/by-radio-station/hits"><button ' +
                    'class="button is-small is-rounded is-info is-light">hits station</button></a>')
    }

}
