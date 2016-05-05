<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><g:layoutTitle default="Botica 0.1"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <r:layoutResources/>
</head>
<body>
    <div class="container">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <g:layoutBody/>

            <g:if test="${flash.message}">
                <p><br>${flash.message}</p>
            </g:if>
        </div>
        <div class="col-md-3"></div>
    </div>
    <r:layoutResources/>
</body>
</html>