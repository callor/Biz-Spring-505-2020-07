<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>나의 홈페이지</title>
<style>
	form {
		with:90%;
		margin:10px auto;
	}
	
	input {
		display: inline-block;
		width:80%;
	}
	
</style>
</head>
<body>
<h3>로그아웃</h3>
<%/*
 sprint security project에서 form method=POST로 설정할 경우
 반드시 
 ${_csrf.parameterName}을 name으로 하고
 ${_csrf.token}을 value로 하는 input box를 포함해야 만 한다.
 
 spring security 5.x 에서는 ${_csrf.parameterName}은 _csrf라는 문자열일
 뿐이지만 input name="_csrf" 라고 사용하지 않는다
 
 security version에 따라 name 변수명이 다르기 때문이다.
 
 spring security에서 logout
 spring security에서는 logout을 반드시 POST method로 요청을 해야 한다.
 
*/%>
<form method="POST" action="${rootPath}/logout">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<button>로그아웃</button>
</form>

</body>
</html>