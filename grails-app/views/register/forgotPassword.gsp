<html>
<head>
<title><g:message code='spring.security.ui.forgotPassword.title'/></title>
<meta name='layout' content='register'/>
</head>
<body>
	<g:form action='forgotPassword' name="forgotPasswordForm" autocomplete='off'>
		<g:if test='${emailSent}'>
			<div>
				<h2>Importante:</h2>
				<p>Se a enviado un correo electrónico para cambiar su contraseña, puede revisar su cuenta de correo 
				para continuar con el proceso de recuperacción de su clave de acceso al sistema. GRACIAS!!</p>
				<br>
				<g:link controller="login" action="index" class="btn btn-info">
					<span class="glyphicon glyphicon-chevron-left"></span>
					Regresar
				</g:link>	
			</div>
		</g:if>
		<g:else>
			<h3 align="center">Recuparación de Contraseña</h3>
			<br/>
			<p align="justify">Debe ingresar su <strong>correo electronico</strong> correctamente para poder, iniciar el proceso de restauración de su contraseña Olvidada.</p>
			<div class="form-group">
				<g:textField name="username" size="35" class='form-control' placeholder="Correo Electrónico" autofocus="true"/>
			</div>
			<g:link controller="login" action="index" class="btn btn-default">
				<span class="glyphicon glyphicon-chevron-left"></span>
				Regresar
			</g:link>
			<input type='submit' id="submit" class="btn btn-primary" value='Restaurar Contraseña'/>
		</g:else>
	</g:form>
</body>
</html>
