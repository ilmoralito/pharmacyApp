<g:if test="${collection}">
    <table class="table table-hover">
        <thead>
            <th>${label}</th>
        </thead>
        <tbody>
            <g:each in="${collection}" var="c">
                <tr id="${c.id}">
                    <td>
                        <g:link
                            action="show"
                            params="[id: c.id, providerId: provider.id]">
                            ${c.name}
                        </g:link>
                    </td>
                </tr>
            </g:each>
        </tbody>
    </table>
</g:if>
<g:else>
    <p><br>Nada que mostrar</p>
</g:else>
