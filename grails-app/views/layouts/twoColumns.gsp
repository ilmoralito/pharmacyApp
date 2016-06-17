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
    <div class="col-md-10">
        <g:pageProperty name="page.main"/>

        <g:set var="message" value="${message ?: flash.message}"/>
        <g:if test="${message}">
            <div class="message"><br>${message}</div>
        </g:if>
    </div>
</body>
</html>