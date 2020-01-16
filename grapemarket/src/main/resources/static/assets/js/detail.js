function boardComplete(boardId) {
	var url = "/board/complete"
	var data = new FormData(document.getElementById('selectForm'));
	console.log(data);
	fetch(url, {
		method: "post",
		body: data,
		contentType: "application/json; charset=utf-8"
	}).then().then()

	localStorage.setItem('storageTest','abc');
	location.href="/user/userProfile";
}
