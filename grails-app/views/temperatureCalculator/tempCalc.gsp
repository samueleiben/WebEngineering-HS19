<html>
<head>
    <meta name="layout" content="form"/>
    <title>
        Temperature Calculator
    </title>
    <script>
        function validate(field) {
            if( Number(field.value) < 1.0) {
                field.classList.add("error");
                field.focus();
            } else {
                field.classList.remove("error");
            }
        }
    </script>
</head>

<body>

<form action="/temperatureCalculator/tempCalc" method="get">
    <fieldset class="form padded">

        %{-- <g:render template="form_row" contextPath="" name="fahrenheit" label="Fahrenheit" model="${calculatorInstance}" /> --}%
        <tmpl:form_row name="fahrenheit" label="Fahrenheit" model="${calculatorInstance}" />

        <div>
            <label>&nbsp;</label>
            <input type="submit" value="Calculate"/>
        </div>
    </fieldset>
</form>


<div class="padded">
    <label>Result</label>
    <mvc:decorate grade="${calculatorInstance}">
        <output>${calculatorInstance.celsiusResult}</output>
    </mvc:decorate>
</div>


</body>
</html>

