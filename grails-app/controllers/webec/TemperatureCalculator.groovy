package webec

import grails.validation.Validateable

class TemperatureCalculatorController {

    def tempCalc(TemperatureCalculatorModel calcModel) {
        calcModel.fahrenheit = Math.round(calcModel.fahrenheit   * 10) / 10
        calcModel.celsiusResult = Math.round((calcModel.fahrenheit - 32)/1.8 * 100) / 100
        if (FieldUtil.hasError(calcModel, "fahrenheit")) {
            calcModel.fahrenheit_error = "error"
            calcModel.fahrenheit_error_message = "value '$calcModel.fahrenheit' is not valid, must be greater than -459.67."
        }
        if (calcModel.hasErrors()) {
            calcModel.celsiusResult = "Cannot calculate. Input data was invalid."
        }
        render view: 'tempCalc', model: [calculatorInstance: calcModel]
    }

}

class TemperatureCalculatorModel implements Validateable {

    double fahrenheit = 3.0

    String fahrenheit_error           = ""
    String fahrenheit_error_message   = ""

    String celsiusResult = ""

    static constraints = {
        fahrenheit min: -459.67d, scale: 2
        celsiusResult nullable: true
    }
}
