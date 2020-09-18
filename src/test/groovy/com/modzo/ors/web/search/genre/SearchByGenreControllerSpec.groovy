package com.modzo.ors.web.search.genre

import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.modzo.ors.web.IntegrationSpec
import org.junit.Rule
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class SearchByGenreControllerSpec extends IntegrationSpec {

    @Rule
    WireMockRule wireMockRule = wireMockRule('stubs/search/by-genre')

    void 'should retrieve search by genre page'() {
        when:
            ResponseEntity<String> result = restTemplate.getForEntity('/search/by-genre/rock', String)
        then:
            result.statusCode == HttpStatus.OK
        and:
            result.body.contains('src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>')
            result.body.contains('<span>Some google ads</span>')
        and:
            result.body.contains('<h1 class="title">Found genres by title "rock"</h1>')
            result.body.contains('<a href="/radio-stations/by-genre/prog-rock/10">')
        and:
            result.body.contains('<h2>Latest searches</h2>')
            result.body.contains('<a href="/search/by-radio-station/hits"><button ' +
                    'class="button is-small is-rounded is-info is-light">hits station</button></a>')
    }

}
