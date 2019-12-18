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

// function enterRoom(roomId) {
//     localStorage.setItem('wschat.roomId', roomId);

//     var ifrm = document.getElementById("chatframe");
//     ifrm.src = "http://localhost:8080/chat/room/enter/" + roomId;

//     var url = 'http://localhost:8080/chat/test/' + roomId;
//     console.log(url);
//     console.log('test tt');
//     fetch(url)
//         .then(function (response) {
//             return response.json();
//         })
//         .then(function (myJson) {
//             console.log(myJson);
//             console.log('test');
//             console.log(myJson.board.title);
//             console.log(myJson.board.price);
//             console.log(myJson.sellerId.username);
//             var username = myJson.sellerId.username;
//             var title = myJson.board.title;
//             var price = myJson.board.price;

//             var info = product_info(username, title, price)

//             document.getElementById("product_info").innerHtml(info);

//         });
// }

// function product_info(username, title, price) {

//     var info = `<h5><div class="col-sm-12 mb-sm-40 font-alt">판매자</div></h5>`;
//     info += `<div id="product_seller" class="col-sm-12 mb-sm-40 font-alt">&nbsp ${username}</div>`;
//     info += `<h5><div class="col-sm-12 mb-sm-40 font-alt">상품명</div></h5>`;
//     info += `<div id="product_title" class="col-sm-12 mb-sm-40 font-alt">&nbsp ${title}</div>`;
//     info += `<h5><div class="col-sm-12 mb-sm-40 font-alt">가격</div></h5>`;
//     info += `<div id="product_price" class="col-sm-12 mb-sm-40 font-alt">&nbsp ${price}</div>`;

//     return info;
// }


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