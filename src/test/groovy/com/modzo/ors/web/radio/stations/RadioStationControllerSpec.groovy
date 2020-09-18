package com.modzo.ors.web.radio.stations

import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.modzo.ors.web.IntegrationSpec
import org.junit.Rule
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class RadioStationControllerSpec extends IntegrationSpec {

    @Rule
    WireMockRule wireMockRule = wireMockRule('stubs/radio-stations/main')

    void 'should retrieve radio stations page'() {
        when:
            ResponseEntity<String> result = restTemplate.getForEntity('/radio-stations/the-hits-radio/5', String)
        then:
            result.statusCode == HttpStatus.OK
        and:
            result.body.contains('src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>')
            result.body.contains('<span>Some google ads</span>')
        and:
            result.body.contains('<h1 class="title">&quot;The Hits&quot; Radio')
            result.body.contains('addThisScriptLocationFromProperties')
            result.body.contains('discusAccountName')
            result.body.contains('<p class="modal-card-title">Information</p>')
            result.body.contains('href="/radio-stations/the-hits-radio/5/streams/1/pls">Download')
            result.body.contains('<source src="https://listen.radionomy.com:80/-secure" type="audio/ogg">')
            result.body.contains('testGoogleAnalyticsUserId')
            result.body.contains('<meta name="propeller" content="testPropellerAdsDomainVerificationCode"/>')
        and:
            result.body.contains('<h2>Latest searches</h2>')
            result.body.contains('<a href="/search/by-radio-station/hits"><button ' +
                    'class="button is-small is-rounded is-info is-light">hits station</button></a>')
    }
}
