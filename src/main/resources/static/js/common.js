$(document).ready(function () {
    $('.navbar-burger').click(function (event) {
        event.stopPropagation();
        $('.navbar-burger').toggleClass('is-active');
        $('.navbar-menu').toggleClass('is-active');
    });

    $('.dropdown').click(function (event) {
        event.stopPropagation();
        $(event.currentTarget).toggleClass('is-active');
    });

    $('[modal-target]').click(function (event) {
        event.stopPropagation();
        const target = $(event.target).attr('modal-target');
        $(target).toggleClass('is-active');
        $(target + ' .delete').click(function (event) {
            $(target).removeClass('is-active');
        });
    });

});