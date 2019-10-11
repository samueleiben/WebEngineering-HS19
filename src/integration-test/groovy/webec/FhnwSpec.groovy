package webec


import geb.spock.GebSpec
import grails.testing.mixin.integration.Integration

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
class FhnwSpec extends GebSpec {

    void "test fhnw click link locations"() {
        when:"The fhnw website is visited"
            go 'http://www.fhnw.ch/de'
        then:"The title is Fachhochschule Nordwestschweiz | FHNW"
        	title == "Fachhochschule Nordwestschweiz | FHNW"

        when: "click on link to all events"
            $("span", text: "Standorte und Kontakt").click()
        then: "locations page is displayed"
            title == "Standorte und Kontakt | FHNW"
    }
}
