function createChat() {
    var form = new FormData(document.getElementById('createChat'));
    let url = "/chat/chat";

    fetch(url, {
        method: "POST",
        body: form
    }).then(function (res) {
        return res.text();
    }).then(function (res) {
        if (res === "ok") {
            //화면에 적용
            location.href = "http://localhost:8080/chat/";
        }
    });


}


function sendMessage2() {
    var form = new FormData(document.getElementById('messagebox'));
    let url = "/pub/chat/message";

    fetch(url, {
        method: "POST",
        body: form
    }).then(function (res) {
        return res.text();
    }).then(function (res) {
        if (res === "ok") {
            //화면에 적용
        }
    });
}


function outChat(id, info) {
    var url = 'http://localhost:8080/chat/outChat/' + id + '/' + info;
    fetch(url)
        .then(function (response) {
            return response.json();
        })
        .then(function (myJson) {
        });
}


function enterRoom(roomId) {
    localStorage.setItem('wschat.roomId', roomId);

    var ifrm = document.getElementById("chatframe");
    ifrm.src = "http://localhost:8080/chat/room/enter/" + roomId;

    var url = 'http://localhost:8080/chat/product/' + roomId;
    fetch(url)
        .then(function (response) {
            return response.json();
        })
        .then(function (myJson) {
            var username = myJson.sellerId.username;
            var title = myJson.board.title;
            var price = myJson.board.price;
            var boardId = myJson.board.id;

            var info = product_info(username, title, price, boardId)

            $("#product_info").empty();
            $("#product_info").append(info);
        });
}

function product_info(username, title, price, boardId) {

    var info = `<h4><div class="col-sm-12 mb-sm-40 font-alt"><a href="/board/detail/${boardId}">게시글로 이동</a></div></h4>`;
    info += `<h5><div class="col-sm-12 mb-sm-40 font-alt">판매자</div></h5>`;
    info += `<div id="product_seller" class="col-sm-12 mb-sm-40 font-alt">&nbsp` + username;
    info += `</div><h5><div class="col-sm-12 mb-sm-40 font-alt">상품명</div></h5>`;
    info += `<div id="product_title" class="col-sm-12 mb-sm-40 font-alt">&nbsp` + title;
    info += `</div><h5><div class="col-sm-12 mb-sm-40 font-alt">가격</div></h5>`;
    info += `<div id="product_price" class="col-sm-12 mb-sm-40 font-alt">&nbsp` + price;
    info += `</div>`
    return info;
}

