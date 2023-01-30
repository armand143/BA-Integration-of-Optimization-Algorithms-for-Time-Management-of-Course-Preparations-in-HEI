$(document).ready(function(){
    $("#form_id").submit(function(event){
        event.prevenDefault();
    });

    $.ajax({
        type: "POST",
        url: "ajax/get_results",
        success: function(response){

        }
    });

});


$.ajaxSetup({
    beforeSend: function(xhr, settings) {
        if (!csrfSafeMethod(settings.type) && !this.crossDomain) {
            xhr.setRequestHeader("X-CSRFToken", csrf_token);
        }
    }
});

// function addCourseForm(){
//     $.ajax({
//         url: 'ajax/',
//         type: 'POST',
//         data: {
//             'current_form_id': currentFormId
//         },
//         success: function(response){
//             //Append the new form to the existing forms
//             $('#forms-container').append(response);
//             // Update the currentFormId variable to the new form's id
//             currentFormId = $('forms-contianer > form:last-child').attr('id');
//         }
//     });

// }

function addCourseForm() {
    var currentForm = $('#current_form').val();
    $.ajax({
        url: '/form/next/',
        data: { form_number: currentForm },
        success: function(data) {
            $('#form_container').html(data);
            $('#current_form').val(currentForm + 1);
        }
    });
}