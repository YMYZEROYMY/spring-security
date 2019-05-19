
$(document).ready(function () {
    getHotMovie();
    whoim();
});

function getHotMovie() {
    $.ajax({
        url: "/movie/getHotMovie",
        dataType: "json",
        success: function (data) {
            changeToMovie(data,$("#hotMovies"));
        }
    })
}
