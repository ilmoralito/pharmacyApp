$(function() {
    $('.remove').on('click', function(e) {
        var selecction = confirm("Si aceptas no podras deshacer los cambios. Deseas continuar?");

        if (selecction) {
            return true;
        } else {
            e.preventDefault();
        }
    });
});
