$(function() {
    $('#client\\.id').select2({
        theme: "bootstrap"
    });

    $('#id').select2({
        theme: "bootstrap"
    });

    $('#company').select2({
        theme: "bootstrap"
    });

    $('#employee').select2({
        theme: "bootstrap"
    });

    autosize($('#annotation'));

    $('#moneyReceived').on('blur', function() {
        var $this = $(this);
        var moneyReceived = $this.val();
        var balance = parseFloat($('#balance').val());
        var errorMessage = $('<small>', { text: 'Dato no valido' });

        if ($this.next() != undefined) {
            $this.next().remove()
        }

        if (isNaN(moneyReceived) || moneyReceived.trim() === "" || parseFloat(moneyReceived) < balance) {
            $this.after(errorMessage);

            return false
        }

        var change = parseFloat(moneyReceived) - balance;

        $('#change').html(change);
    });

    $('#addClient').on('click', function() {
        $(this).next().toggleClass('show hide');
    })

    // Employee
    $('#company').on('change', function() {
        var employee = $('#employee');
        var optionSelected = $('option:selected', this);
        var employees = optionSelected.data('employees');

        employee.find('option').remove();

        for (var i = employees.length - 1; i >= 0; i--) {
            var opt = $('<option>', {
                value: employees[i].id,
                text: employees[i].fullName,
                'data-identificationCard': employees[i].identificationCard,
                'data-inss': employees[i].inss,
                'data-companyCreditLimit': employees[i].companyCreditLimit
            });

            employee.append(opt);
        }
    });

    var defaultEmployees = $('#company').find('option:first-child').data('employees');
    var employee = $('#employee');

    for (var i = defaultEmployees.length - 1; i >= 0; i--) {
        var opt = $('<option>', {
            value: defaultEmployees[i].id,
            text: defaultEmployees[i].fullName,
            'data-identificationCard': defaultEmployees[i].identificationCard,
            'data-inss': defaultEmployees[i].inss,
            'data-companyCreditLimit': defaultEmployees[i].companyCreditLimit
        });

        employee.append(opt);
    }

    $('#employee').on('change', function() {
        var optionSelected = $('option:selected', this);
            data = optionSelected.data();
            template = $('#template').html();
            rendered = Mustache.render(template, data);

        Mustache.parse(template);

        $('#employeeInformation').html(rendered);
    });
});
