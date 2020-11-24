<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>나의 홈페이지</title>
</head>
<body>
<h3>나의 홈페이지 방문을 환영합니다</h3>
<style>
	.valid-error {
		display: inline-block;
		margin-left: 10px;
		font-size: 12px;
		color:red;
	}
	
	form.main-form {
		width:80%;
		margin: 10px auto;
	}
	
	form.main-form input {
		width:80%;
		padding:8px;
		margin:4px;
	}
</style>
<form:form class="main-form" modelAttribute="userVO">
	<div>
		<form:input placeholder="이름" path="name"/>
		<form:errors path="name" class="valid-error"></form:errors>
	</div>
	<div>
		<form:input placeholder="이메일" path="email"/>
		<form:errors path="email" class="valid-error"></form:errors>
	</div>
	<div>
		<form:input placeholder="나이" path="age"/>
		<form:errors path="age" class="valid-error"></form:errors>
	</div>
	<div>
		<button>저장</button>
	</div>
</form:form>

</body>
</html>