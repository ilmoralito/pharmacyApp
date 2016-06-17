<table class="table table-hover">
    <colgroup>
        <col span="1" style="width: 20%;">
        <col span="1" style="width: 60%;">
        <col span="1" style="width: 9%;">
        <col span="1" style="width: 9%;">
        <col span="1" style="width: 2%;">
    </colgroup>
    <thead>
        <th>Laboratorio</th>
        <th>Articulo</th>
        <th>Cantidad</th>
        <th>Total</th>
        <th></th>
    </thead>
    <tbody>
        <g:each in="${saleDetails}" var="saleDetail" status="idx">
            <tr>
                <td>${saleDetail.item.product.provider}</td>
                <td>
                    <g:if test="${saleDetail.item instanceof ni.sb.MedicineOrder || saleDetail.item instanceof ni.sb.BrandProductOrder}">
                        ${saleDetail.item.getFullName()}
                    </g:if>
                    <g:else>
                        ${saleDetail.item.product.name}
                    </g:else>
                </td>
                <td>${saleDetail.quantity}</td>
                <td>${saleDetail.total}</td>
                <td>
                    <g:if test="${actionName != 'detail'}">
                        <g:link event="removeItem" params="[id: idx]" class="btn btn-default btn-xs">
                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                        </g:link>
                    </g:if>
                </td>
            </tr>
        </g:each>
        <tr>
            <td colspan="3"></td>
            <td colspan="2"><strong>${saleDetails.total.sum()}</strong></td>
        </tr>
    </tbody>
</table>
