$('form').find("select:first").on('change', function() {
    var $this = $(this);
    var data = $this.find(':selected').data('nami');
    var target = $this.parent().parent().next().find('select');

    target.find('option').remove()

    console.log(data);
});
