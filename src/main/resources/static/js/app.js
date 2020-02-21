$(document).ready(function () {

    $('.input-group.date').datepicker({
        autoclose: true,
        todayHighlight: true
    });


    var filters = $(".filters");
    console.log("filters", filters);

    var filterForms = filters.children("div");
    console.log("filterForms", filterForms);

    filterForms.hide();

    var filterBtns = $(".filter-button");
    console.log("filterBtns", filterBtns);

    filterBtns.on('click', function (e) {
        $(this).next().toggle();
    });


});