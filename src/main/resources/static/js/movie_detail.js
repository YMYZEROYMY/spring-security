$(document).ready(function () {
    whoim();
    getFullMovie();
    getSameTypeMovie();
});

function buy() {
    if (!ifLogin) {
        alert("请先登录");
        return false;
    }
    checkNumber();
    var buyNumber = $("#number").val();
    if (buyNumber <= 0) {
        alert("请输入购买数量");
        return false;
    }
    if (!confirm("确定购买吗？\n数量:" + buyNumber)) {
        return false;
    }
    var invoiceInfo = {
        id: $("#movieId").text(),
        number: buyNumber
    };

    $.ajax({
        url: "/user/bug",
        dataType: "json",
        data: invoiceInfo,
        success: function (data) {
            solveBuyResult(eval(data));
        },
        error: function () {
            alert("请检查连接");
        }
    });
    return false;
}

function solveBuyResult(data) {
    if (data.flag === "true") {
        alert("购买成功");
    } else {
        alert("购买失败\n提示：" + data.msg);
    }
    getFullMovie();
}

function getSameTypeMovie() {
    var dto = {
        id: $("#movieId").text()
    };
    $.ajax({
        url: "/movie/getSameTypeMovie",
        dataType: "json",
        data: dto,
        success: function (data) {
            changeToMovie(data,$("#sameType"));
        }
    })
}


function getFullMovie() {
    var dto = {
        id: $("#movieId").text()
    };
    $.ajax({
        url: "/movie/getFullMovie",
        dataType: "json",
        data: dto,
        success: function (data) {
            addMovieDetail(eval(data));
        },
        error: function () {
            alert("请检查连接");
        }
    })
}

function addMovieDetail(data) {
    var actorsName = "";
    var typesName = "";
    var i = 0;

    for (i = 0; i < data.actorsName.length; i++) {
        var actorName = data.actorsName[i];
        actorsName += actorName + "  ";
    }
    for (i = 0; i < data.typesName.length; i++) {
        var typeName = data.typesName[i];
        typesName += typeName + "  ";
    }

    $("#imgContainer").attr("src", data.path);
    $("#name").text(data.name);
    $("#director").text(data.directorName);
    $("#actors").text(actorsName);
    $("#types").text(typesName);
    $("#date").text(data.date);
    $("#ticket").text(data.ticket);
    $("#price").text(data.price);

    $("#number").attr("max", data.ticket);

}

function checkNumber() {
    var reg = /^[1-9]+[0-9]*]*$/;
    var number = $("#number");
    var num = number.val();
    var remainNumber = parseInt(number.attr("max"));
    if (!reg.test(num)) {
        number.val(1);
        return;
    }
    if (num <= 0) {
        number.val(1);
    }
    if (num >= remainNumber) {
        number.val(remainNumber);
    }
}

function addNumber() {
    var number = $("#number");
    var num = parseInt(number.val());
    if (num <= 0 || num >= parseInt(number.attr("max"))) {
        number.val(num);
        return;
    }
    number.val(num + 1);
}

function delNumber() {
    var number = $("#number");
    var num = parseInt(number.val());
    if (num <= 1 || num > parseInt(number.attr("max"))) {
        number.val(num);
        return;
    }
    number.val(num - 1);
}
