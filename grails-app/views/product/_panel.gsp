<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="${!params.tab || params.tab != 'filter' ? 'active' : ''}">
        <g:link action="${actionName}" params="[providerId: provider.id]">
            Crear
        </g:link>
    </li>
    <li role="presentation" class="${params.tab == 'filter' ? 'active' : ''}">
        <g:link action="${actionName}" params="[providerId: provider.id, tab: 'filter']">
            Filtrar
        </g:link>
    </li>
</ul>

<g:if test="${!params.tab || params.tab != 'filter'}">
    <g:form action="${actionName}" autocomplete="off">
        <g:hiddenField name="providerId" value="${params?.providerId}"/>
        <g:render template="form"/>

        <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
    </g:form>
</g:if>
<g:else>
    <g:link
        action="${actionName}"
        params="[providerId: params?.providerId, enabled: true, tab: 'filter']"
        class="btn btn-block btn-${params.boolean('enabled') || params.enabled == null ? 'primary' : 'default'}">
        Activos
    </g:link>

    <g:link
        action="${actionName}"
        params="[providerId: params?.providerId, enabled: false, tab: 'filter']"
        class="btn btn-block btn-${params.boolean('enabled') == false ? 'primary' : 'default'}">
        No activos
    </g:link>
</g:else>