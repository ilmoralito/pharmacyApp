$('.trigger').on("dblclick", function(event) {
    var _this = $(this),
        id = _this.data('id'),
        name = _this.data('name');

    var div = $('<div>', { class: 'form-group' }),
        input = $('<input>', { class: 'form-control', id: 'name', value: name, 'data-name': name, 'data-id': id }),
        buttonConfirm = $('<button>', { class: 'btn btn-primary', id: 'confirmButton', text: 'Confirmar' }),
        buttonCancel = $('<button>', { class: 'btn btn-default pull-right', id: 'cancelButton', text: 'Cancelar' });

    div.append(input);
    _this.html(div);
    _this.append(buttonConfirm);
    _this.append(buttonCancel);
});

$(document).on('click', '#cancelButton', function() {
    var _this = $(this),
        td = _this.parent(),
        name = td.find('input').data('name');

    td.html(name);
});

$(document).on('click', '#confirmButton', function() {
    var _this = $(this),
        td = _this.parent(),
        input = td.find('input'),
        id = input.data('id'),
        currentName = input.data('name'),
        name = input.val(),
        url = window.ajaxPATH;

    if (currentName !== name) {
        $.ajax({
            url : url,
            method: "POST",
            data: { id: id, name: name },
            success: function(data) {
                if (data.error) {
                    alert("A ocurrido un error. El nombre es requerido y no debe estar repetido");
                }

                if (data.name) {
                    td.attr('data-name', data.name);

                    td.html(data.name);
                    $('span#' + id).html(data.name);
                }
            },
            error: function(xhr, ajaxOptions, error) {
                console.log(xhr, ajaxOptions, error);
            }
        })
    } else {
        td.html(currentName);
    }
})
