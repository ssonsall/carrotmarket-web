function boardComplete(boardId) {

	var url = "/board/complete"

	var data = new FormData(document.getElementById('selectForm'));
	var selectBox_Buyer = document.getElementById('selectBox_Buyer').value;
	console.log(document.getElementById('selectBox_Buyer').value);
	console.log(typeof(document.getElementById('selectBox_Buyer').value))
	if (selectBox_Buyer !== 'cancel') {
		fetch(url, {
			method : "post",
			body : data,
			contentType : "application/json; charset=utf-8"
		}).then().then()

		localStorage.setItem('from', 'detail');
		localStorage.setItem('state', 'complete');
		location.href = "/user/userProfile";
	} else {
		alert('구매자 없이 구매 완료처리를 하시겠습니까?!!!!!!!!!!!!!!?????????????????!')
	}

}
