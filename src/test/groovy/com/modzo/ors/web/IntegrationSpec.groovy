package com.modzo.ors.web

import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles('test')
class IntegrationSpec extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    static WireMockRule wireMockRule(String path) {
        new WireMockRule(
                options().port(7777).usingFilesUnderClasspath(path)
        )
    }

}
