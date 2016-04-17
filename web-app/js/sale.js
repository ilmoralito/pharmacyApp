$(function() {
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
});
