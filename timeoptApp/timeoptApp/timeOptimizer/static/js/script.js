$(document).ready(function(){
    $('.course-item').click(function(){
        retrieveCourseData($(this).data('key'));
    });
});

function retrieveCourseData(key){
    $.ajax({
        url: 'retrieveCourseData/',
        type: 'GET',
        data: {
            'key': key
        },
        success: function(response){

        }
    });
}


