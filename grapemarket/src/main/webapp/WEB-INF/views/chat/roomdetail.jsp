<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!doctype html>
<html lang="en">
<head>
<title>Websocket ChatRoom</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>



<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" href="/webjars/bootstrap/4.3.1/dist/css/bootstrap.min.css"> -->
<style>
[v-cloak] {
	display: none;
}

div.container2 {
	overflow-x: auto;
	overflow-y: scroll;
	display: inline-block;
	border: solid 1px rgba(0, 0, 0, .125);
	height: 400px;
	width: 380px;
}

.list-group-item1 {
	padding: 0;
	margin: 0;
	border: 0;
	margin-left: 0.5rem;
	background-color: rgba(0, 0, 0, 0);
	padding: 0.7rem;
	text-align: right;
}

.list-group-item2 {
	padding: 0;
	margin: 0;
	border: 0;
	margin-left: 0.5rem;
	background-color: rgba(0, 0, 0, 0);
	padding: 0.7rem;
	text-align: left;
}

.test_item1 {
	background-color: RGB(229, 229, 234);
	/* RGB(16,136,254); */
	/* RGB(229,229,234) */
	color: black;
	font-weight: 600;
	padding: 0.5rem;
	padding-left: 1rem;
	padding-right: 1rem;
	border-radius: 10px;
	padding: 0.5rem;
}

.test_item2 {
	background-color: RGB(16, 136, 254);
	/* RGB(16,136,254); */
	/* RGB(229,229,234) */
	color: white;
	font-weight: 600;
	padding: 0.5rem;
	padding-left: 1rem;
	padding-right: 1rem;
	border-radius: 10px;
	padding: 0.5rem;
}
</style>
</head>
<body>
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="principal" />
	</sec:authorize>
	<input type="hidden" id="sendername" value="${principal.user.username}" />
	<input type="hidden" id="roomId" value="${roomId}" />
	<div class="container" id="app" v-cloak>


		<div class="container2" id="container2">

			<ul class="list-group">

				<c:forEach var="message" items="${messages}">

					<c:choose>
						<c:when test="${principal.user.username eq message.sender}">
							<li class="list-group-item1"><span class="test_item1"> ${message.message}</span></li>
						</c:when>
						<c:otherwise>
							<li class="list-group-item2"><span class="test_item2"> <a onclick="messageReportPopup(${message.id}); return false">
										${message.message} </a>
							</span></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<div v-for="message in messages">
					<li class="list-group-item1" v-if="message.propertyA"><span class="test_item1"> {{message.message}}</span></li>

					<li class="list-group-item2" v-else><span class="test_item2"> {{message.message}}</span></li>
				</div>



			</ul>
		</div>


		<div class="input-group">
			<div class="input-group-prepend">

				<label class="input-group-text">내용</label>
			</div>
			<input type="text" id="message" name="message" class="form-control" v-model="message" @keyup.enter="sendMessage">
			<div class="input-group-append">
				<button class="btn btn-primary" type="button" @click="sendMessage">보내기</button>
			</div>
		</div>
	</div>
	<script>
        // websocket & stomp initialize
        var sendername = document.getElementById('sendername').value;
        var roomId = document.getElementById('roomId').value;
        
        var sock = new SockJS("/ws-stomp");
        var ws = Stomp.over(sock);
        // vue.js
        var vm = new Vue({
            el: '#app',
            data: {
                roomId: '',
                room: {},
                sender: '',
                message: '',
                messages: [],
                property:''
            },
            created() {
                this.roomId = roomId;
                this.sender = sendername;
                this.message = '';
                this.findRoom();
                this.property = '';
            },
            methods: {
                findRoom: function() {
                    axios.get('/chat/room/'+this.roomId).then(response => { this.room = response.data; });
                },
                sendMessage: function() {
                    ws.send("/pub/chat/message", {}, JSON.stringify({temp:this.roomId, sender:this.sender, message:this.message}));
                    this.message = '';
                },
                recvMessage: function(recv) {
                	if(sendername===recv.sender){
                		this.messages.push({"type":recv.type,"sender":recv.sender,"message":recv.message,"propertyA":true})
                	}else{
                		this.messages.push({"type":recv.type,"sender":recv.sender,"message":recv.message,"propertyA":false})
                    	}
                    
                    $('#container2')
                    .stop()
                    .animate({ scrollTop: $('#container2')[0].scrollHeight }, 10);
                }
            }
        });
        // pub/sub event
        ws.connect({}, function(frame) {
            ws.subscribe("/sub/chat/room/"+vm.$data.roomId, function(message) {
                var recv = JSON.parse(message.body);
                vm.recvMessage(recv);
                
            });
        }, function(error) {
        });

        document.getElementById('container2').scrollTop = document.getElementById('container2').scrollHeight;
      
    var popupX = (window.screen.width / 2) - (500 / 2);
    var popupY = (window.screen.height / 2) - (600 / 2);
    	function messageReportPopup(id) {
            window.open("/report/boardReportForm?id=" + id + "&type=message", "a", "width=500, height=600, left="+popupX+", top="+popupY);
        }
    
    </script>



	<%@include file="../include/script.jsp"%>
</body>
</html>