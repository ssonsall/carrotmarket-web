function getState() {

	var url = "/tradeState/tradeList";
	$("#stateList").empty();
	fetch(url).then(function (res) {
		return res.json();
	}).then(function (res) {

		for (var value of res) {
			var title = value.board.title;
			var price = value.board.price;
			var state = value.state;

			console.log(title);
			console.log(price);
			console.log(state);

			var menuItem = getMenuItem(title, price, state);

			$("#stateList").append(menuItem);
		}
	});
}

function getMenuItem(title, price, state) {

	var item = `<div class="menu" ><div class="row">`;
	item += `<div class="col-sm-6"><h4 class="menu-title font-alt">${title}</h4></div>`;
	item += `<div class="col-sm-2"><h4 class="menu-title font-alt">${state}</h4></div>`;
	item += `<div class="col-sm-4 menu-price-detail"><h4 class="menu-price font-alt">${price} WON</h4></div></div></div>`;

	return item;

}