<g:form>
	<g:if test="${items.any {it instanceof ni.sb.Medicine}}">
		<div class="form-group">
			<g:select name="genericName" from="${genericNames}" value="${genericName}" noSelection="['':'Nombres genericos']" class="form-control"/>
		</div>
	</g:if>

	<div class="form-group">
		<g:select name="product" from="${items}" optionKey="name" value="${product}" class="form-control"/>
	</div>

	<g:submitButton name="filter" value="Filtrar" class="btn btn-primary btn-block"/>
</g:form>