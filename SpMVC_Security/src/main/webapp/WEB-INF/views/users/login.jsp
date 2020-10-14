<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<style>
#login_body {
	margin: 0;
	padding: 0;
}

#login_body form {
	width: 400px;
	padding: 40px;
	/* 
        body를 기준으로 box의 왼쪽 꼭지점 좌표를 설정
        위에서 50%위치, 왼쪽에서 50% 위치로 지정하고
        다시 현재 박스의 width의 50% 만큼을 왼쪽(-50%)으로 이동
        박스의 height의 50% 만큼을 위쪽(-50%)으로 이동
        form box가 화면 한가운데 위치하도록 만들기
        */
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	z-index: 100;
	background-color: #191919;
	text-align: center;
	border-radius: 20px;
	box-shadow: 12px 12px 2px 1px rgba(0, 0, 255, 0.2);
	animation-name: login_box_top_down;
	animation-duration: 0.8s;
	-webkit-animation-name: login_box_top_down;
	-webkit-animation-duration: 0.8s;
	-moz-animation-name: login_box_top_down;
	-moz-animation-duration: 0.8s;
}

#login_body form h2 {
	color: white;
	font-weight: 500;
}

#login_body form input, #login_body form button {
	display: block;
	margin: 20px auto;
	text-align: center;
	width: 200px;
	padding: 16px 10px;
	background: none;
	/*
        input box에 focus(클릭)가 위치할때
        실제 input box 바깥쪽에 임의 box가 발생하는데
        이 box를 보이지 않도록 하는 설정
        */
	outline: 0;
	border: 0;
	border-radius: 25px;
	transition: 0.5s;
}

#login_body form input {
	border: 2px solid #3498db;
	color: white;
}

#login_body form input:focus {
	width: 280px;
	border: 2px solid #2ecc71;
}

#login_body form button {
	border: 2px solid #2ecc71;
	color: white;
}

#login_body form button:hover {
	background-color: #2ecc71;
}

/* 
      @keyframes 
      CSS3 에니메이션을 JS 사용하지 않고 구현할수 있도록 만들어진 Query
      */
@keyframes login_box_top_down {from { top:-300px;
	opacity: 0;
}

to {
	top: 50%;
	opacity: 1;
}
}

h4#login-fail {
	margin:5px auto;
	background-color: red;
	color:yellow;
	border-radius: 15px;
	padding:8px;
}

</style>
<section id="login_body">
	<form method="POST" action="${rootPath}/login">
		<h2>로그인</h2>
		
		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
			<h4 id="login-fail">${SPRING_SECURITY_LAST_EXCEPTION.message}</h4>
			<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
		</c:if>
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input name="username" placeholder="사용자 ID" /> 
		<input name="password" type="password" placeholder="비밀번호" />
		<button>로그인</button>
		<button type="button">회원가입</button>
	</form>
</section>

</body>
</html>