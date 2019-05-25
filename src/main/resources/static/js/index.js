
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
function getByType(type) {
    var temp={
        target:type
    };
    $.ajax({
        url: "/movie/getByType",
        type:'post',
        data:temp,
        dataType: "json",
        success: function (data) {
            solveSearchResult(data);
        }
    })
}