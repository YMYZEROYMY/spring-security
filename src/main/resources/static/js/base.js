$(document).ready(function () {
    whoim();
    getInvoice();
    var loginName = document.getElementById("loginUsername");
    var registerName = document.getElementById("registerUsername");
    var loginPassword = document.getElementById("loginPassword");
    var registerPassword = document.getElementById("registerPassword");
    var rePassword = document.getElementById("rePassword");
    var loginSpan = $("#loginSpan");
    var registerSpan = $("#registerSpan");
    loginName.addEventListener("input", function (ev) {
        checkUsername($("#loginUsername"), loginSpan)
    });
    registerName.addEventListener("input", function (ev) {
        checkUsername($("#registerUsername"), registerSpan)
    });
    loginPassword.addEventListener("input", function (ev) {
        checkPassword($("#loginPassword"), loginSpan)
    });
    registerPassword.addEventListener("input", function (ev) {
        checkPassword($("#registerPassword"), registerSpan)
    });
    rePassword.addEventListener("input", checkRePassword, false);
});

var ifLogin = false;

function checkUsername(temp, span) {
    var username = temp.val().trim();
    var phoneReg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
    if (!phoneReg.test(username)) {
        showSpan(span, "请输入有效的手机号");
        return false;
    } else {
        hideSpan(span);
        return true;
    }
}

function checkPassword(temp, span) {
    var password = temp.val().trim();
    var passwordReg = /^[a-zA-Z0-9_]+$/;
    if (password.length < 8 || password.length > 20) {
        showSpan(span, "密码长度为8-20");
        return false;
    } else if (!passwordReg.test(password)) {
        showSpan(span, "密码为大小写字母或数字");
        return false;
    } else {
        hideSpan(span);
        return true;
    }
}

function checkRePassword() {
    if ($("#registerPassword").val() !== $("#rePassword").val()) {
        showSpan($("#registerSpan"), "两次密码不同");
        return false;
    } else {
        hideSpan($("#registerSpan"));
        return true;
    }
}

function hideSpan(span) {
    span.text(" ");
    span.fadeOut();
}

function showSpan(span, msg) {
    span.text(msg);
    span.fadeIn();
}

function getInvoice() {
    $.ajax({
        url: "/user/getInvoice",
        type: 'post',
        dataType: "json",
        success: function (data) {
            solveGetInvoiceSuccess(data);
        }
    });
}

function solveGetInvoiceSuccess(data) {
    var result = eval(data);
    var length = result.length;
    var table = $("#invoiceTable");
    $("#invoiceFirstTr").nextAll().remove();//添加前删除所有原有项
    for (var i = 0; i < length; i++) {
        var invoice = result[i];
        var tr = $("<tr></tr>");
        var td = $("<td></td>");
        var path = "/movie/detail/" + invoice.movieId;
        td.append("<a href='" + path + "'>" + invoice.movieName + "</a>");
        tr.append(td);
        tr.append("<td>" + invoice.date + "</td>");
        tr.append("<td>" + invoice.number + "</td>");
        tr.append("<td>" + invoice.sum + "</td>");
        table.append(tr);
    }
}

function whoim() {
    $.ajax({
        url: "/user/whoim",
        type: 'post',
        dataType: "json",
        success: function (data) {
            solveGetNameSuccess(data)
        }
    });
}

function searchMovie() {
    $.ajax({
        url: "/movie/searchMovie",
        type: 'post',
        data: $("#searchForm").serialize(),
        dataType: "json",
        success: function (data) {
            solveSearchResult(data);
        }
    });
    return false;
}

function solveSearchResult(data) {
    $("#hotMovies").text(" ");
    changeToMovie(data, $("#hotMovies"));
}

function login() {
    if (!checkUsername($("#loginUsername"), $("#loginSpan")) || !checkPassword($("#loginPassword"), $("#loginSpan"))) {
        alert("请检查输入");
        return false;
    }
    $("#myModal").modal('hide');
    var loginData = {
        username: $("#loginUsername").val(),
        password: hex_md5($("#loginPassword").val())
    };
    $.ajax({
        url: "/login",
        type: 'post',
        data: loginData,
        dataType: "json",
        success: function (data) {
            solveLoginSuccess(data)
        },
        error: function () {
            solveLoginFalse();
        }
    });
    return false;
}

function register() {
    if (!checkUsername($("#registerUsername"), $("#registerSpan")) || !checkPassword($("#registerPassword"), $("#registerSpan")) || !checkRePassword()) {
        alert("请检查输入");
        return false;
    }
    $("#myModal").modal('hide');
    var registerData = {
        username: $("#registerUsername").val(),
        password: hex_md5($("#registerPassword").val())
    };
    $.ajax({
        url: "/register",
        type: 'post',
        data: registerData,
        dataType: "json",
        success: function (data) {
            solveRegisterSuccess(data)
        },
        error: function () {
            solveRegisterFalse();
        }
    });
    return false;
}

function solveLogout() {
    ifLogin = false;
    $.ajax({
        url: '/logout',
        type: 'post',
        dataType: 'json',
        success: function (data) {
            addUsername(" ");
            showLoginUI();
        },
        error: function () {
            addUsername(" ");
            showLoginUI();
        }
    });
}

function solveGetNameSuccess(data) {
    if (data == null) {
        return;
    }
    var result = eval(data);
    var name = result.name;
    if (name == null) {
        return;
    }
    console.log("name:" + name);
    addUsername(name);
    ifLogin = true;
}

function addUsername(name) {
    $("#usernameDiv").text(name);
    showLogoutUI();
}

function showLoginUI() {
    $("#showLogoutUl").attr("style", "display:none");
    $("#showLoginUl").show();
}

function showLogoutUI() {
    $("#showLoginUl").attr("style", "display:none");
    $("#showLogoutUl").show();
}

//设置延迟alert是为了防止model收回不及时
function solveLoginFalse() {
    setTimeout(function (args) {
        alert("登录失败");
        $("#myModal").modal('show');
    }, 500);
}

function solveLoginSuccess(data) {
    var result = eval(data);
    if (result.flag === "true") {
        addUsername(result.name);
    }
    setTimeout(function (args) {
        alert(result.msg);
    }, 500);
    ifLogin = true;
}

function solveRegisterFalse(data) {
    var result = eval(data);
    setTimeout(function (args) {
        showSpan($("#registerSpan"), "注册失败\n提示：" + result.msg);
        $("#myModal").modal('show');
    }, 500);
}

function solveRegisterSuccess(data) {
    var result = eval(data);
    if (result.flag === "true") {
        setTimeout(function (args) {
            alert("注册成功");
        }, 500);
    } else {
        solveRegisterFalse(data);
    }
}

function switchLog() {
    singleToggle($("#showLoginUl"));
    singleToggle($("#showLogoutUl"));
}

function singleToggle(node) {
    node.slideToggle(500);
}


function changeToMovie(data, node) {
    var movies = eval(data);
    var length = movies.length;
    for (var i = 0; i < length; i++) {
        var movie = movies[i];
        var caption = $("<div class='caption'></div>");
        caption.append("<h3 class='captionParent captionHead'>" + movie.name + "</h3>");
        caption.append("<p class='captionParent captionBody'>" + "导演:" + movie.directorName + "</p>");
        caption.append("<p class='captionParent captionBody'>" + "售价:" + movie.price + "  剩余:" + movie.ticket + "</p>");
        caption.append("<p class='captionParent captionBody popularity'>" + "热度:" + movie.popularity + "</p>");
        var photo = $("<div class='moviePhoto'></div>");
        photo.append("<img width='100%' src='" + movie.path + "'>");
        var thumbnail = $("<div class='thumbnail'></div>");
        thumbnail.append(photo);
        thumbnail.append(caption);
        var path = "/movie/detail/" + movie.id;
        var jump = $("<a href='" + path + "'></a>");
        jump.append(thumbnail);
        var top = $("<div class='col-lg-2 col-md-3 col-sm-4 col-xs-6'></div>");
        top.append(jump);
        node.append(top);
    }
}

// <div class="col-lg-2 col-md-3 col-sm-4 col-xs-6">
//     <a href="movie/detail/i">
//     <div class="thumbnail">
//     <div class="moviePhoto">
//     <img width="100%" src=" ">
//     </div>
//     <div class="caption">
//     <h3 class="captionParent captionHead">复仇者联盟3</h3>
//     <p class="captionParent captionBody">director</p>
//     <p class="captionParent captionBody">number</p>
//     <p class="captionParent captionBody">popularity</p>
//     </div>
//     </div>
//     </a>
//     </div>
