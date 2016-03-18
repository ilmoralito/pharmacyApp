<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><g:layoutTitle default="Botica 0.1"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <r:layoutResources/>
</head>
<body>
    <!--NAVBAR-->
    <g:render template="/layouts/navbar"/>
    <div class="container-fluid">
        <div class="col-md-2">
            <g:render template="/layouts/sidebar"/>
        </div>
        <div class="col-md-10">
            <g:layoutBody/>
            <!--
                The next code check is neccesary in order to display message 
                from commons controlers and from webflow
            -->
            <g:set var="message" value="${message ?: flash.message}"/>
            <g:if test="${message}">
                <div class="message">${message}</div>
            </g:if>
        </div>
    </div>
    <br>
    <r:layoutResources/>
</body>
</html>
