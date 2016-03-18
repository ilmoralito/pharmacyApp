<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><g:layoutTitle default="Hello"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <r:layoutResources/>
</head>
<body id="background">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <h4>App name</h4>
                <g:layoutBody/>

                <g:if test="${flash.message}">
                    <br>
                    <p>${flash.message}</p>
                </g:if>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
    <r:layoutResources/>
</body>
</html>