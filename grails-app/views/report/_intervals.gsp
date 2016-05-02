<div class="btn-group btn-group-justified" role="group" aria-label="nami">
    <div class="btn-group" role="group">
        <g:link
            action="${target}"
            params="[field: 'month']"
            class="btn btn-default btn-block ${params?.field == 'month' ? 'active' : ''}">
            <small>Mes</small>
        </g:link>
    </div>
    <div class="btn-group" role="group">
        <g:link
            action="${target}"
            params="[field: 'week']"
            class="btn btn-default btn-block ${params?.field == 'week' || !params.field ? 'active' : ''}">
            <small>Semana</small>
        </g:link>
    </div>
    <div class="btn-group" role="group">
        <g:link
            action="${target}"
            params="[field: 'year']"
            class="btn btn-default btn-block ${params?.field == 'year' ? 'active' : ''}">
            <small>Año</small>
        </g:link>
    </div>
</div>