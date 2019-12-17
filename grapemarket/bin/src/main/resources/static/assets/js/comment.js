function commentWrite() {
    var form = new FormData(document.getElementById('comment-submit'));
    let url = "/comment/write";
    
    fetch(url, {
        method: "POST",
        body: form
    }).then(function (res) {
        return res.text();
    }).then(function (res) {
        if (res === "ok") {
            //화면에 적용
            console.log(res);
            var username = document.getElementById("username").value;
            var content = document.getElementById("comment_area").value;
            console.log(username);
            console.log(content);
            var comment_et = commentItemForm(username,content);
            
            $("#comments_reviews").append(comment_et);
            //입력폼 초기화하기
            
            $("#comment_area").val("");
        }
    });

   
}

function commentItemForm(username, content) {
    var commentItem = `<div class="comment clearfix">
										<div class="comment-avatar">
											<img src="" alt="avatar" />
										</div>
										<div class="comment-content clearfix">
											<div class="comment-author font-alt">
												<a href="#">${username}</a>
											</div>
											<div class="comment-body">
												<p>${content}</p>
											</div>
											<div class="comment-meta font-alt">
												Today, 14:55 -<span><i class="fa fa-star star"></i></span><span><i
													class="fa fa-star star"></i></span><span><i
													class="fa fa-star star"></i></span><span><i
													class="fa fa-star star"></i></span><span><i
													class="fa fa-star star-off"></i></span>
											</div>
										</div>
									</div>`;
    return commentItem;
}

