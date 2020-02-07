function like() {
    var form = new FormData(document.getElementById('likeForm'));
    let url = "/like/like";
    let status = "";

    fetch(url, {
        method: "POST",
        body: form
    }).then(function (res) {
        return res.text();
    }).then(function (res) {

        res = JSON.parse(res);
        console.log(res);
        console.log(res.status);

        if (res.status === "delete") {
            let likeDiv = likeView(res.status);

            $("#liked").empty();
            $("#liked").append(likeDiv);

            $("#likeCount").empty();
            $("#likeCount").append(likeCount(res.likeCount));
            //var username = myJson.sellerId.username;
        } else if (res.status === "save") {
            let likeDiv = likeView(res.status);

            $("#liked").empty();
            $("#liked").append(likeDiv);

            $("#likeCount").empty();
            $("#likeCount").append(likeCount(res.likeCount));
        } else {

        }
    });
}

function likeView(status) {

    if (status == "delete") {
        var likeView = `<span onclick="like()" style="font-size: 30px;">🤍</span>`;
    } else {
        var likeView = `<span onclick="like()" style="font-size: 30px;">🧡</span>`;
    }


    return likeView;
}

function likeCount(count) {

    if (count != 0) {
        var likeCount = `<p>${count} 명의 회원이 이 상품을 좋아합니다.</p>`;
    } else {
        var likeCount = `<p> </p>`;
    }


    return likeCount;
}