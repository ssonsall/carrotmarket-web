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
            // 화면에 적용
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
            // 화면에 적용
        }
    });
}


function outChat(id, info) {
    var url = '/chat/outChat/' + id + '/' + info;
    fetch(url).then().then();
    $("#chatId_" + id).empty();
    $("#product_image").empty();
    $("#product_info").empty();
    $("#tradeState").empty();
    var ifrm = document.getElementById("chatframe");
    ifrm.src = "";
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
            /* 채팅방 클릭시 상품 정보를 출력하기 위한 필요 정보 */
            var username = myJson.sellerId.username;
            var title = myJson.board.title;
            var price = myJson.board.price;
            var boardId = myJson.board.id;
            var image1 = myJson.board.image1;



            /* 상품 이미지 입력 */
            var addImage = add_image(image1)
            $("#product_image").empty();
            $("#product_image").append(addImage);
            /* 상품 정보 입력 */
            var info = product_info(username, title, price, boardId)
            $("#product_info").empty();
            $("#product_info").append(info);


            /* 채팅방 클릭시 거래 상태를 표시하기 위한 정보 */
            url = "/tradeState/checkState";
            var check = { "userId": userId, "boardId": boardId };
            fetch(url, {
                method: "POST",
                body: JSON.stringify(check),
                contentType: "application/json; charset=utf-8"
            }).then(function (res) {
                return res.text();
            }).then(function (res) {
                var form = trade_state(res, userId, boardId, roomId);

                $("#tradeState").empty();
                $("#tradeState").append(form);


            });


        });
}
function add_image(image1) {
    var add_image = `<div class="col-sm-12 mb-sm-40">
									<img src="/upload/${image1}"
                                        alt="Single Product Image" style="max-height: 300px; min-width: 232px;" />	</div>`;
    return add_image;
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

    if (tradeState === '판매중') {
        var form = `<div id="completeBtn"><button onclick="location.href='/board/detail/${boardId}'">게시물로 이동</button></div><button onclick="outChat(${roomId},'seller')">채팅방 나가기</button>`;
    } else if (tradeState === '구매중') {
        var form = `<div id="completeBtn"><button onclick="setTradeStateComplete(2,${userId},${boardId},${roomId})">구매 완료</button></div><button onclick="outChat(${roomId},'buyer')">채팅방 나가기</button>`;
    } else if (tradeState === '판매완료') {
        var form = `<button onclick="outChat(${roomId},'seller')">채팅방 나가기</button>`;
    } else if (tradeState === '구매완료') {
        var form = `<button onclick="outChat(${roomId},'buyer')">채팅방 나가기</button>`;
    }
    return form;
}


function setTradeStateComplete(stateNum, userId, boardId, roomId) {

    if (!stateNum === 1) {
    	alert("구매 완료 처리되었습니다.");
        var form = `<button onclick="outChat(${roomId}, 'seller')">채팅방 나가기</button></div>`;
    } 

    let url = "/tradeState/setStateComplete";

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


