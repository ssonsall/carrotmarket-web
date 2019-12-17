/**
 * 
 */
// <!-- 주소 스크립트 시작 -->
function goPopup() {
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제
	// 주소검색URL(http://www.juso.go.kr/addrlink/addrCoordUrl.do)를 호출하게 됩니다.
	var pop = window.open("/map/popup", "pop",
			"width=570,height=420, scrollbars=yes, resizable=yes");
}

function jusoCallBack(roadFullAddr, entX, entY) {
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	document.form.roadFullAddr.value = roadFullAddr;

	/* 주소변환 시작 */

	Proj4js.reportError = function(msg) {
		alert(msg);
	}
	Proj4js.defs['GRS80'] = '+proj=tmerc +lat_0=38 +lon_0=127.5 +k=0.9996 +x_0=1000000 +y_0=2000000 +ellps=GRS80 +units=m +no_defs';
	Proj4js.defs['WGS84'] = '+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs';
	var GRS80 = new Proj4js.Proj('GRS80');
	var WGS84 = new Proj4js.Proj('WGS84');
	var pre_x = entX;
	var pre_y = entY;
	var p = new Proj4js.Point(pre_x, pre_y);
	Proj4js.transform(GRS80, WGS84, p);
	/* 주소변환 끝 */

	document.form.entX.value = p.y;
	document.form.entY.value = p.x;
}
// <!-- 주소 스크립트 끝 -->
// <!-- 주소 인증 시작 -->
var x = document.getElementById("demo");
var y = document.getElementById("check");

function getLocation() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPosition, showError);
	} else {
		x.innerHTML = "Geolocation is not supported by this browser.";
	}
}

function showPosition(position) {
	userLatitude = document.getElementById("addressX").value;
	userLongitude = document.getElementById("addressY").value;
	getLatitude = position.coords.latitude;
	getLongitude = position.coords.longitude;

	console.log("유저주소X" + userLatitude);
	console.log("받은 주소 X" + getLatitude);
	console.log("유저주소Y" + userLongitude);
	console.log("받은 주소 Y" + getLongitude);

	calX = getLatitude - userLatitude;
	calY = getLongitude - userLongitude;

	if (userLatitude > getLatitude) {
		calX = userLatitude - getLatitude;
	}
	if (userLongitude > getLongitude) {
		calY = userLongitude - getLongitude;
	}

	if (0.0004 >= (calX * calX + calY * calY)) {
		y.innerHTML = "인증이 성공하였습니다.";
		document.getElementById('townAuthForm').submit();
	} else {
		y.innerHTML = "인증에 실패하였습니다.";
	}
	console.log(".X계산값" + calX);
	console.log(".Y계산값" + calY);
	console.log(calX * calX + calY * calY)
	x.innerHTML = "Latitude: " + position.coords.latitude + "<br>Longitude: "
			+ position.coords.longitude;
}

function showError(error) {
	switch (error.code) {
	case error.PERMISSION_DENIED:
		x.innerHTML = "User denied the request for Geolocation."
		break;
	case error.POSITION_UNAVAILABLE:
		x.innerHTML = "Location information is unavailable."
		break;
	case error.TIMEOUT:
		x.innerHTML = "The request to get user location timed out."
		break;
	case error.UNKNOWN_ERROR:
		x.innerHTML = "An unknown error occurred."
		break;
	}
}
// <!-- 주소 인증 끝 -->
