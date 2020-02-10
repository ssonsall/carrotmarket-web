function boardComplete(boardId) {

	var url = "/board/complete"

	var data = new FormData(document.getElementById('selectForm'));
	var selectBox_Buyer = document.getElementById('selectBox_Buyer').value;
	console.log(document.getElementById('selectBox_Buyer').value);
	console.log(typeof (document.getElementById('selectBox_Buyer').value))

	fetch(url, {
		method : "post",
		body : data,
		contentType : "application/json; charset=utf-8"
	}).then(function(res) {
		return res.text();
	}).then(function(res) {
		localStorage.setItem('from', 'detail');
		localStorage.setItem('state', 'complete');
		location.href = "/user/userProfile";
	});

}
