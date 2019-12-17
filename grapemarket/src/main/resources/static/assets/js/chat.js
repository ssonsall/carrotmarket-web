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

function enterRoom(roomId) {
    
    localStorage.setItem('wschat.roomId', roomId);
    location.href = "/chat/room/enter/" + roomId;
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