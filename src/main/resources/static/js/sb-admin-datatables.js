$(document).ready(function() {
    $('table td.details_button').on('click',function (e) {
        var button = $(this).find('.fa');
        if(button.hasClass('fa-minus')){
            button.toggleClass('fa-plus').toggleClass('fa-minus');
        } else {
            $('table td.details_button .fa').removeClass('fa-minus').addClass('fa-plus');
            button.toggleClass('fa-plus').toggleClass('fa-minus');
        }

    });

    $('#dataTable').DataTable();

});


