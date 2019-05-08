
$(document).ready(function () {
    getHotMovie();
});

function login() {
    $("#myModal").modal('hide');
    $.ajax({
        url:"/login",
        type:'post',
        data:$("#loginForm").serialize(),
        dataType:"json",
        success:function(data){
            solveLoginSuccess(data)
        },
        error:function () {
            solveLoginFalse();
        }
    });
    return false;
}

function solveLogout() {
    $.ajax({
        url:'/logout',
        type:'post',
        dataType:'json',
        success:function (data) {
            switchLog();
        },
        error:function () {
            switchLog();
        }
    });
}

//设置延迟alert是为了防止model收回不及时
function solveLoginFalse() {
    setTimeout(function (args) {
        alert("登录失败");
        $("#myModal").modal('show');
    },500);
}

function solveLoginSuccess(data) {
    var result=eval(data);
    if(result.flag==="true"){
        switchLog();
    }
    setTimeout(function (args) {
        alert(result.msg);
    },500);
}

function switchLog() {
    singleToggle($("#showLoginUl"));
    singleToggle($("#showLogoutUl"));
}

function singleToggle(node) {
    node.slideToggle(500);
}

function getHotMovie() {
    $.ajax({
        url:"/movie/getHotMovie",
        dataType:"json",
        success:function(data){
            changeToMovie(data);
        }
    })
}

function changeToMovie(data) {
    var node=$("#hotMovies");
    var movies=eval(data);
    var length=movies.length;
    for(var i=0;i<length;i++){
        var movie=movies[i];
        var caption=$("<div class='caption'></div>");
        caption.append("<h3 class='captionParent captionHead'>"+movie.name+"</h3>");
        caption.append("<p class='captionParent captionBody'>"+movie.info+"</p>");
        var photo=$("<div class='moviePhoto'></div>");
        photo.append("<img width='100%' src='"+movie.path+"'>");
        var thumbnail=$("<div class='thumbnail'></div>");
        thumbnail.append(photo);
        thumbnail.append(caption);
        var top=$("<div class='col-lg-2 col-md-3 col-sm-4 col-xs-6'></div>");
        top.append(thumbnail);
        node.append(top);
    }
}

// <div class="col-md-3 col-sm-6">
//     <div class="thumbnail">
//     <img src="photo/01.jpg">
//     <div class="caption">
//     <h3>01</h3>
//     <p>最爱的图片</p>
//     </div>
//     </div>
//     </div>