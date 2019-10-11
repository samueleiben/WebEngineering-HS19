package webec

import geb.spock.GebSpec
import grails.testing.mixin.integration.Integration

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
class CalculatorSpec extends GebSpec {


    void "Basic calculation"() {
        when:
        go '/static/GradeCalculator.html'
        then:
        title == "Grade Calculator"

        when: "set valid input"
        $("form").en = 5
        $("form").exam = 6
        $("input", type: "submit").click()

        then: "Result Page is displayed"
        title == "Average"
        $("output").text() == "5.5"

        when: "click on back link"
        $("a", text: "calculator").click()
        then:
        title == "Grade Calculator"

        when: "set invalid input"
        $("form").en = "e"
        $("form").exam = "e"
        $("input", type: "submit").click()

        then: "Page shows error message"
        title == "Grade Calculator"
        $("#invalid-en-message").css("display") == "block"
    }
}
