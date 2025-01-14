<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src='/map/lib/proj4js-combined.js'></script>
<%
	//request.setCharacterEncoding("UTF-8"); //한글깨지면 주석제거
	//request.setCharacterEncoding("EUC-KR");  //해당시스템의 인코딩타입이 EUC-KR일경우에
	String inputYn = request.getParameter("inputYn");
	String roadFullAddr = request.getParameter("roadFullAddr");
	String roadAddrPart1 = request.getParameter("roadAddrPart1");
	String roadAddrPart2 = request.getParameter("roadAddrPart2");
	String engAddr = request.getParameter("engAddr");
	String jibunAddr = request.getParameter("jibunAddr");
	String zipNo = request.getParameter("zipNo");
	String addrDetail = request.getParameter("addrDetail");
	String admCd = request.getParameter("admCd");
	String rnMgtSn = request.getParameter("rnMgtSn");
	String bdMgtSn = request.getParameter("bdMgtSn");
	String detBdNmList = request.getParameter("detBdNmList");
	String bdNm = request.getParameter("bdNm");
	String bdKdcd = request.getParameter("bdKdcd");
	String siNm = request.getParameter("siNm");
	String sggNm = request.getParameter("sggNm");
	String emdNm = request.getParameter("emdNm");
	String liNm = request.getParameter("liNm");
	String rn = request.getParameter("rn");
	String udrtYn = request.getParameter("udrtYn");
	String buldMnnm = request.getParameter("buldMnnm");
	String buldSlno = request.getParameter("buldSlno");
	String mtYn = request.getParameter("mtYn");
	String lnbrMnnm = request.getParameter("lnbrMnnm");
	String lnbrSlno = request.getParameter("lnbrSlno");
	String emdNo = request.getParameter("emdNo");
	String entX = request.getParameter("entX");
	String entY = request.getParameter("entY");
%>
</head>
<script language="javascript">
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("주소입력화면 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";

/*
			모의 해킹 테스트 시 팝업API를 호출하시면 IP가 차단 될 수 있습니다. 
			주소팝업API를 제외하시고 테스트 하시기 바랍니다.
*/

//devU01TX0FVVEgyMDE5MTIxMzIzMjI0NjEwOTMxMzE=
function init(){
	var url = location.href;
	var confmKey = "devU01TX0FVVEgyMDIwMDExMzE1MTMwNDEwOTM4MzI=";
	var resultType = "4"; // 도로명주소 검색결과 화면 출력내용, 1 : 도로명, 2 : 도로명+지번, 3 : 도로명+상세건물명, 4 : 도로명+지번+상세건물명
	var inputYn= "<%=inputYn%>";
	if(inputYn != "Y"){
		document.form.confmKey.value = confmKey;
		document.form.returnUrl.value = url;
		document.form.resultType.value = resultType;
		document.form.action="http://www.juso.go.kr/addrlink/addrCoordUrl.do"; //인터넷망
		document.form.submit();
	}else{
		<%-- // opener.jusoCallBack("<%=emdNm%>","<%=entX%>", "<%=entY%>"); --%>		
		
	Proj4js.defs['GRS80'] = '+proj=tmerc +lat_0=38 +lon_0=127.5 +k=0.9996 +x_0=1000000 +y_0=2000000 +ellps=GRS80 +units=m +no_defs';
	Proj4js.defs['WGS84'] = '+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs';
	var GRS80 = new Proj4js.Proj('GRS80');
	var WGS84 = new Proj4js.Proj('WGS84');
	var emdNm = '<%=emdNm%>';
	var pre_x = <%=entX%>;
	var pre_y = <%=entY%>;
	var p = new Proj4js.Point(pre_x, pre_y);
	Proj4js.transform(GRS80, WGS84, p);
	var tran_x = p.y;
	var tran_y = p.x;
	
	location.href="https://ec2-15-164-214-186.ap-northeast-2.compute.amazonaws.com:8443/android/juso?address="+emdNm+"&addressX="+tran_x+"&addressY="+tran_y+"";
	
	}
}
</script>
<body onload="init();">
	<form id="form" name="form" method="post">
		<input type="hidden" id="confmKey" name="confmKey" value="" /> <input
			type="hidden" id="returnUrl" name="returnUrl" value="" /> <input
			type="hidden" id="resultType" name="resultType" value="" />
		<!-- 해당시스템의 인코딩타입이 EUC-KR일경우에만 추가 START-->
		<!-- 
		<input type="hidden" id="encodingType" name="encodingType" value="EUC-KR"/>
		 -->
		<!-- 해당시스템의 인코딩타입이 EUC-KR일경우에만 추가 END-->
	</form>
</body>
</html>