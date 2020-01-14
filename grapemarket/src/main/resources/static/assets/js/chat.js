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
            location.href = "/chat/";
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
    var url = '/chat/outChat/' + id + '/' + info;
    fetch(url)
        .then(function (response) {
            return response.json();
        })
        .then(function (myJson) {

         });
}


function enterRoom(roomId, userId) {
    localStorage.setItem('wschat.roomId', roomId);


    var ifrm = document.getElementById("chatframe");
    console.log('ifrm ' + ifrm);
    ifrm.src = "/chat/room/enter/" + roomId;
    console.log('ifrm.src ' + ifrm.src);
    var url = '/chat/product/' + roomId;
    fetch(url)
        .then(function (response) {
            return response.json();
        })
        .then(function (myJson) {
            var username = myJson.sellerId.username;
            var sellerId = myJson.sellerId.id;
            var title = myJson.board.title;
            var price = myJson.board.price;
            var boardId = myJson.board.id;

            var info = product_info(username, title, price, boardId)

            $("#product_info").empty();
            $("#product_info").append(info);

            var tradeState;

            if (sellerId === userId) {
                tradeState = "sell";
            } else {
                tradeState = "buy";
            }


            var form = trade_state(tradeState, userId, boardId, roomId);

            $("#tradeState").empty();
            $("#tradeState").append(form);

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

function trade_state(tradeState, userId, boardId, roomId) {

    if (tradeState === 'sell') {
        var form = `<div id="completeBtn"><button onclick="setTradeStateComplete(1,${userId},${boardId},${roomId})">판매 완료</button></div>`;
    } else if (tradeState === 'buy') {
        var form = `<div id="completeBtn"><button onclick="setTradeStateComplete(2,${userId},${boardId},${roomId})">구매 완료</button></div>`;
    }
    return form;
}


function setTradeStateComplete(stateNum, userId, boardId, roomId) {

    if (stateNum === 1) {
        alert("판매 완료 처리되었습니다.");
        var form = `<button onclick="outChat(${roomId}, 'buyer')">채팅방 나가기</button></div>`;
    } else {
        alert("구매 완료 처리되었습니다.");
        var form = `<button onclick="outChat(${roomId}, 'seller')">채팅방 나가기</button></div>`;
    }

    let url = "/chat/setStateComplete";

    var data = { "userId": userId, "boardId": boardId };

    fetch(url, {
        method: "POST",
        body: JSON.stringify(data),
        contentType: "application/json; charset=utf-8"
    }).then(function (res) {
        return res.text();
    }).then(function (res) {
        if (res === "ok") {



            $("#completeBtn").empty();
            $("#completeBtn").append(form);
        }

    });
};


