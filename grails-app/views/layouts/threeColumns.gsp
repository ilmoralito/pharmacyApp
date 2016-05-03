<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="layout" content="no-main"/>
        <title><g:layoutTitle/></title>
        <g:layoutHead/>
    </head>
    <body>
        <div class="col-md-2">
            <g:render template="/layouts/sidebar"/>
        </div>
        <div class="col-md-8">
            <g:pageProperty name="page.main"/>
        </div>
        <div class="col-md-2">
            <g:pageProperty name="page.col1"/>

            <g:set var="message" value="${message ?: flash.message}"/>
            <g:if test="${message}">
                <p><br>${message}</p>
            </g:if>
        </div>
    </body>
</html>