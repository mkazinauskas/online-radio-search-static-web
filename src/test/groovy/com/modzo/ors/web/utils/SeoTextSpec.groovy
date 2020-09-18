package com.modzo.ors.web.utils

import spock.lang.Specification

class SeoTextSpec extends Specification {

    void 'should strip dashes'() {
        when:
            String result = SeoText.from("Ludwig van Beethoven (1770-1827) - 'Sonatine' per")
        then:
            result == "ludwig-van-beethoven-1770-1827-sonatine-per"
    }

    void 'should Trim White Space'() {
        when:
            String result = SeoText.from("Test   ")
        then:
            result == 'test'
    }

    void 'should Leave Arabic Symbols'() {
        when:
            String result = SeoText.from("إذاعة &rlm;الق")
        then:
            result == 'إذاعة-rlmالق'
    }

    void 'should Lowercase Letters'() {
        when:
            String result = SeoText.from('Test')
        then:
            result == 'test'
    }

    void 'should Change Spaces To Dashes'() {
        when:
            String result = SeoText.from('Test go')
        then:
            result == 'test-go'
    }

    void 'should Strip Letters And Numbers To Dashes'() {
        when:
            String result = SeoText.from('L$%e#t$¥s@go')
        then:
            result == 'letsgo'
    }
}
