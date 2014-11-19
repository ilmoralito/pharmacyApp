<html>

<head>
<title><g:message code='spring.security.ui.resetPassword.title'/></title>
<meta name='layout' content='register'/>
</head>

<body>
<g:form action='resetPassword' name='resetPasswordForm' autocomplete='off'>
	<g:hiddenField name='t' value='${token}'/>
	<h3>Escriba su nueva contraseña</h3>
	<p>La contraseña debe tener 8 caracteres de tamaño, al menos una letra, un número y un carácter especiales:! @ # $% ^ &</p>

	<div class="form-group">
        <g:passwordField class="form-control" name="password" placeholder="Nueva contraseña" value="${command?.password}" bean="${command}" autofocus="true"/>
    </div>

	<div class="form-group">
        <g:passwordField class="form-control" name="password2" placeholder="Repita su contraseña" value="${command?.password2}" bean="${command}" labelCodeDefault='Password'/>
    </div>

	<g:link controller="login" action="index" class="btn btn-default">
		<span class="glyphicon glyphicon-chevron-left"></span>
		Regresar
	</g:link>	
	<input type='submit' id="submit" class="btn btn-primary" value='Actualizar Contraseña'/>
</g:form>
</body>
</html>