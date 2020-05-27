package com.modzo.ors.web

import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Rule
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

    @Rule
    WireMockRule wireMockRule = new WireMockRule(
            options().port(8081).usingFilesUnderClasspath('stubs')
    )

    @Autowired
    TestRestTemplate restTemplate

}