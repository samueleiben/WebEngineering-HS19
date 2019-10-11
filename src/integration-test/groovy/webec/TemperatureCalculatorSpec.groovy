package webec

import geb.spock.GebSpec
import grails.testing.mixin.integration.Integration

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
class TemperatureCalculatorSpec extends GebSpec {

    void "Calculate in place with a self-refreshing view"() {
        when: "Go to start GSP page by calling it disguised as HTML"
            go '/TemperatureCalculator.html?lang=en'
        then:
        	title == "Temperature Calculator"

        when: "set valid input"
            $("form").fahrenheit   = '32'
            $("input", type: "submit").click()

        then: "Result is displayed"
            $("output").text() == "0"
    }

    void "Invalid input shows error message and sets error class"() {
        when: "Go to start GSP page by calling it disguised as HTML"
            go '/TemperatureCalculator.html?lang=en'
        then:
        	title == "Temperature Calculator"

        when: "set invalid input"
            $("form").fahrenheit = '-1000'
            $("input", type: "submit").click()

        then: "Result contains error message"
            $("output").text() == "Cannot calculate. Input data was invalid."
        then: "invalid en field has error class while valid exam input has no class"
            $("#fahrenheit",   class:'error')
    }

    void "Invalid input is handled in-place by JS without submission"() {
        given: "a valid state"
            try { browser.driver.javascriptEnabled = true } catch(onlyForHtmlUnit) {}
            go '/TemperatureCalculator.html?lang=en'
            $("form").fahrenheit = '3.0'
        when:
            $("input", type: "submit").click()
        then: "we should have a clean, valid state to start from"
            $("#fahrenheit").attr('class') == ""
        when: "we enter some invalid value _without_ submitting"
            $("form").fahrenheit = '-1000'   // note: no click here!
        then: "the in-place JS logic should kick in"
            $("#fahrenheit").attr('class') == "error"
    }


}
