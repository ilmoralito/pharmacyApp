<li class="dropdown ${controllerName == 'notifications' ? 'active' : ''}">
    <a
        href="#"
        class="dropdown-toggle"
        data-toggle="dropdown">
        <span
            class="glyphicon glyphicon-bell ${grailsApplication.config.ni.sb.lowStocks || grailsApplication.config.ni.sb.aboutToExpire || grailsApplication.config.ni.sb.paymentDateClose ? 'notify' : ''}"
            aria-hidden="true">
        </span>
        <span class="caret"></span>
    </a>
    <ul class="dropdown-menu">
        <li role="presentation" class="dropdown-header">Articulos</li>
        <li class="${actionName == 'lowStocks' ? 'active' : ''}">
            <g:link controller="notifications" action="lowStocks">
                Productos con bajas existencias
                <g:if test="${grailsApplication.config.ni.sb.lowStocks}">
                    <span class="badge">${grailsApplication.config.ni.sb.lowStocks}</span>
                </g:if>
            </g:link>
        </li>
        <li class="${actionName == 'aboutToExpire' ? 'active' : ''}">
            <g:link controller="notifications" action="aboutToExpire">
                Medicinas proximas a vencerse
                <g:if test="${grailsApplication.config.ni.sb.aboutToExpire}">
                    <span class="badge">${grailsApplication.config.ni.sb.aboutToExpire}</span>
                </g:if>
            </g:link>
        </li>
        <li role="presentation" class="dropdown-header">Pedidos</li>
        <li class="${actionName == 'paymentDateClose' ? 'active' : ''}">
            <g:link controller="notifications" action="paymentDateClose">
                Pedidos con fecha de pago proximo
                <g:if test="${grailsApplication.config.ni.sb.paymentDateClose}">
                    <span class="badge">${grailsApplication.config.ni.sb.paymentDateClose}</span>
                </g:if>
            </g:link>
        </li>
        <li class="divider"></li>
        <li>
            <g:link controller="configuration">Configurar</g:link>
        </li>
    </ul>
</li>

