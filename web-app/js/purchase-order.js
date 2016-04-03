$('input:radio[name=paymentType]').on('change', function() {
    var $this = $(this);
    var $target = $('#target');

    console.log($this.val())
    if ($this.val() == 'credit') {
        $target.removeClass('hide');
        $target.addClass('show');
        $('#deadline').focus();
    } else {
        $target.removeClass('show');
        $target.addClass('hide');
    }
})