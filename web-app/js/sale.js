$(function() {
    $('#query').on('keyup', function() {
        var $this = $(this);
        var url = window.queryPATH;
        var val = $this.val();
        var itemsList = $('#items');

        $.ajax({
            url: url,
            method: "POST",
            data: { query: val },
            success: function(items) {
                var data = [];

                if (items) {
                    for (var i = items.length - 1; i >= 0; i--) {
                        data.push(items[i])

                        var tr = $('<tr>'),
                            anchor = $('<a>', {
                                'data-id': items[i].id,
                                'data-name': items[i].name,
                                'data-quantity': items[i].quantity,
                                'data-purchasePrice': items[i].purchasePrice,
                                'data-sellingPrice': items[i].sellingPrice,
                                'data-provider': items[i].provider,
                                'data-presentation': items[i].presentation,
                                'data-measure': items[i].measure,
                                'data-brand': items[i].brand,
                                'data-presentation': items[i].presentation,
                                text: items[i].name,
                                href: '#',
                                class: 'items'
                            }),
                            tdName = $('<td>'),
                            tdLaboratory = $('<td>', { text: items[i].provider }),
                            tdSellingPrice = $('<td>', { text: items[i].sellingPrice });

                        tdName.append(anchor);
                        tr.append(tdName);
                        tr.append(tdLaboratory);
                        tr.append(tdSellingPrice);

                        itemsList.append(tr)
                    }
                }

                localStorage.items = data;
            },
            error(xhr, ajaxOptions, error) {
                console.log(xhr, ajaxOptions, error);
            }
        })
    });

    $(document).on('click', '.items', function(event) {
        event.preventDefault();

        var $this = $(this),
            data = $this.data(),
            itemsToSale = $('#itemsToSale');

            console.log(data);

        var tr = $('<tr>'),
            tdName = $('<td>', { text: data.name }),
            tdLaboratory = $('<td>', { text: data.provider }),
            tdPresentation = $('<td>', { text: data.presentation }),
            tdMeasure = $('<td>', { text: data.measure }),
            tdBrand = $('<td>', { text: data.brand }),
            tdDetail = $('<td>', { text: data.detail }),
            tdQuantity = $('<td>'),
            tdSellingPrice = $('<td>', { text: data.sellingprice });
            tdTotal = $('<td>', { class: 'total' }),
            inputQuantity = $('<input>', {
                id: 'quantity',
                type: 'number',
                class: 'form-control input-sm',
                min: 1,
                max: data.quantity,
                placeholder: data.quantity
            }),
            tdDelete = $('<td>'),
            anchor = $('<a>', { id: 'delete', href: '#', text: 'Eliminar' });

        tdQuantity.append(inputQuantity);
        tdDelete.append(anchor);

        tr.append(tdName);
        tr.append(tdLaboratory);
        tr.append(tdPresentation);
        tr.append(tdMeasure);
        tr.append(tdBrand);
        tr.append(tdDetail);
        tr.append(tdQuantity);
        tr.append(tdSellingPrice);
        tr.append(tdTotal);
        tr.append(tdDelete);

        itemsToSale.prepend(tr);
    });

    $(document).on('change', '#quantity', function() {
        var $this = $(this),
            summary = $('#summary'),
            tdTotal = $this.parent().next().next(),
            quantity = parseInt($(this).val()),
            sellingPrice = parseFloat($this.parent().next().text());

            tdTotal.text(calculateTotal(quantity, sellingPrice));
            $('#summary').text(calculateBalance());
            $('#balance').text(calculateBalance());

    });

    $(document).on('click', '#delete', function(event) {
        $(event.target).parent().parent().remove();

        $('#summary').text(calculateBalance());
        $('#balance').val(calculateBalance());
    });

    var calculateTotal = function(quantity, sellingPrice) {
        var result = quantity * sellingPrice;

        return result;
    }

    var calculateBalance = function() {
        var summary = 0;

        $('.total').each (function() {
            summary += parseFloat($(this).text());
        })

        return summary;
    }
});
