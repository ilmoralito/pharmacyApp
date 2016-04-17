<table class="table table-hover">
    <colgroup>
        <col span="1" style="width: 14.83%;">
        <col span="1" style="width: 14.83%;">
        <col span="1" style="width: 14.83%;">
        <col span="1" style="width: 14.83%;">
        <col span="1" style="width: 14.83%;">
        <col span="1" style="width: 14.83%;">
        <col span="1" style="width: 5%;">
        <col span="1" style="width: 5%;">
        <col span="1" style="width: 1%;">
    </colgroup>
    <thead>
        <th>Nombre</th>
        <th>Laboratorio</th>
        <th>Presentacion</th>
        <th>Medida</th>
        <th>Marca</th>
        <th>Detalle</th>
        <th>Cantidad</th>
        <th>Total</th>
        <th></th>
    </thead>
    <tbody>
        <g:each in="${saleDetails}" var="saleDetail" status="idx">
            <tr>
                <td>${saleDetail.item.product}</td>
                <td>${saleDetail.item.product.provider}</td>
                <td>
                    <g:if test="${saleDetail.item instanceof ni.sb.MedicineOrder}">
                        ${saleDetail.item.presentation}
                    </g:if>
                </td>
                <td>
                    <g:if test="${saleDetail.item instanceof ni.sb.MedicineOrder}">
                        ${saleDetail.item.measure}
                    </g:if>
                </td>
                <td>
                    <g:if test="${saleDetail.item instanceof ni.sb.BrandProductOrder}">
                        ${saleDetail.item.brand}
                    </g:if>
                </td>
                <td>
                    <g:if test="${saleDetail.item instanceof ni.sb.BrandProductOrder}">
                        ${saleDetail.item.detail}
                    </g:if>
                </td>
                <td>${saleDetail.quantity}</td>
                <td>${saleDetail.total}</td>
                <td>
                    <g:link event="removeItem" params="[id: idx]" class="btn btn-default btn-xs">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </g:link>
                </td>
            </tr>
        </g:each>
        <tr>
            <td colspan="7"></td>
            <td colspan="2"><strong>${sale.saleDetails.total.sum()}</strong></td>
        </tr>
    </tbody>
</table>