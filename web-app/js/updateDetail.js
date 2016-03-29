$('.details').on("dblclick", function(event) {
    var _this = $(this);
    var dataset = _this.data();
    var id = dataset.id;
    var detail = dataset.detail;

    // CREATE STRUCTURE
    var div = $('<div>', { class: 'form-group' });
    var input = $('<input>', {
        class: 'form-control',
        id: 'name',
        value: detail,
        'data-detail': detail,
        'data-id': id
    });
    var buttonConfirm = $('<button>', { class: 'btn btn-primary', id: 'confirmButton', text: 'Confirmar' });
    var buttonCancel = $('<button>', { class: 'btn btn-default pull-right', id: 'cancelButton', text: 'Cancelar' });

    div.append(input);
    _this.html(div);
    _this.append(buttonConfirm);
    _this.append(buttonCancel);
});

$(document).on('click', '#cancelButton', function() {
    var _this = $(this);
    var td = _this.parent();
    var detailName = td.find('input').data().detail;

    td.html(detailName);
});

$(document).on('click', '#confirmButton', function() {
    var _this = $(this);
    var td = _this.parent();
    var input = td.find('input');
    var dataset = input.data()
    var id = dataset.id;
    var currentDetailName = dataset.detail;
    var name = input.val();
    var url = window.ajaxPATH;

    if (currentDetailName !== name) {
        $.ajax({
            url : url,
            method: "POST",
            data: { id: id, name: name },
            success: function(data) {
                if (data.error) {
                    alert("A ocurrido un error. El nombre es requerido y no debe estar repetido");
                }

                if (data.name) {
                    // update td
                    td.html(data.name);

                    // update 
                    $('span#' + id).html(data.name);
                }
            },
            error: function(xhr, ajaxOptions, error) {
                console.log(xhr, ajaxOptions, error);
            }
        })
    } else {
        td.html(currentDetailName);
    }
})
