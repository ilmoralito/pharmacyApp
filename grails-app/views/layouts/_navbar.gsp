<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-1">
                <span class="sr-only">Navegar</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <g:link class="navbar-brand" controller="${controllerName}">
                ${grailsApplication.config.ni.sb.companyName}
            </g:link>
        </div>
        <div id="navbar-collapse-1" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <g:render template="/layouts/notifications"/>
                <g:render template="/layouts/reports"/>
                <g:render template="/layouts/administration"/>
                <g:render template="/layouts/profile"/>
            </ul>
        </div>
    </div>
</nav>
