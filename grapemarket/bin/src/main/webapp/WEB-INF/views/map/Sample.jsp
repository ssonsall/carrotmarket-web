<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript">

function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrCoordUrl.do)를 호출하게 됩니다.
	var pop = window.open("/map/popup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadFullAddr,emdNm,entX,entY){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.form.roadFullAddr.value = roadFullAddr;
		document.form.emdNm.value = emdNm;
		document.form.entX.value = entX;
		document.form.entY.value = entY;
		console.log(emdNm);
		console.log(entX);
		console.log(entY);
}

</script>
<title>주소 입력 샘플</title>
</head>
<body>
<form name="form" id="form" method="post">

	<input type="button" onClick="goPopup();" value="팝업_domainChk"/>
	<div id="list"></div>
	<div id="callBackDiv">
		<table>
			<tr><td>도로명주소           </td><td><input type="text"  style="width:500px;" id="roadFullAddr"  name="roadFullAddr" /></td></tr>
			<tr><td>읍면동명        	</td><td><input type="text"  style="width:500px;" id="emdNm"  name="emdNm" /></td></tr>
			<tr><td>X 좌표       		</td><td><input type="text"  style="width:500px;" id="entX"  name="entX" /></td></tr>
			<tr><td>Y 좌표       		</td><td><input type="text"  style="width:500px;" id="entY"  name="entY" /></td></tr>
		</table>
	</div>

</form>
</body>
</html>