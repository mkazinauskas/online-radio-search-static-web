package com.modzo.ors.web.search.song

import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.modzo.ors.web.IntegrationSpec
import org.junit.Rule
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class SearchBySongControllerSpec extends IntegrationSpec {

    @Rule
    WireMockRule wireMockRule = wireMockRule('stubs/search/by-song')

    void 'should retrieve search by song page'() {
        when:
            ResponseEntity<String> result = restTemplate.getForEntity('/search/by-song/test', String)
        then:
            result.statusCode == HttpStatus.OK
        and:
            result.body.contains('src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>')
            result.body.contains('<span>Some google ads</span>')
        and:
            result.body.contains('<h1 class="title">Found songs by title "test"</h1>')
            result.body.contains('<a href="/radio-stations/by-played-song/test/6390">')
        and:
            result.body.contains('<h2>Latest searches</h2>')
            result.body.contains('<a href="/search/by-radio-station/hits"><button class="button is-small is-rounded ' +
                    'is-success is-light">hits genre</button></a>')
    }

}
