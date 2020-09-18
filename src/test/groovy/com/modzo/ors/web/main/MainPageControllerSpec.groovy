package com.modzo.ors.web.main

import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.modzo.ors.web.IntegrationSpec
import org.junit.Rule
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class MainPageControllerSpec extends IntegrationSpec {

    @Rule
    WireMockRule wireMockRule = wireMockRule('stubs/main')

    void 'should retrieve main page'() {
        when:
            ResponseEntity<String> result = restTemplate.getForEntity('/', String)
        then:
            result.statusCode == HttpStatus.OK
        and:
            result.body.contains('src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>')
            result.body.contains('<span>Some google ads</span>')
        and:
            result.body.contains('<h1 class="title">Latest Radio Stations</h1>')
            result.body.contains('<strong>&quot;The Hits&quot; Radio</strong>')
            result.body.contains('<strong>FRISKY CHILL | feelin&#39; frisky?</strong>')
            result.body.contains('<strong>radio-roermondialive</strong>')
            result.body.contains('<strong>Web Radio DNOR</strong> - webradiodnor.myl2mr.com')
        and:
            result.body.contains('<h2>Latest searches</h2>')
            result.body.contains('<a href="/search/by-song/ambient"><button ' +
                    'class="button is-small is-rounded">ambient</button></a>')
            result.body.contains('<a href="/search/by-song/hits"><button ' +
                    'class="button is-small is-rounded">hits</button></a>')
    }

}
