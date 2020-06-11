package com.modzo.ors.web.error

import com.modzo.ors.web.IntegrationSpec
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

class ErrorControllerSpec extends IntegrationSpec {

    void 'should retrieve main page'() {
        given:
            HttpHeaders headers = new HttpHeaders()
            headers.add(HttpHeaders.ACCEPT, MediaType.TEXT_HTML_VALUE)
        when:
            ResponseEntity<String> result = restTemplate.exchange(
                    '/not-existing-page',
                    HttpMethod.GET,
                    new HttpEntity(headers),
                    String
            )
        then:
            result.statusCode == HttpStatus.NOT_FOUND
        and:
            result.body.contains('This happens sometimes, and only admin knows why :)')
    }

}
