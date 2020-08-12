<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<link rel="stylesheet" type="text/css" 
		href="${rootPath}/static/css/input.css" />
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-header.jspf" %>
	<section id="main">
		<form action="${rootPath}/blog/writer" method="POST">
			<div>
				<input name="title" placeholder="제목을 입력하세요" />
			</div>
			<div>
				<input name="content" placeholder="내용을 입력하세요" />
			</div>
			<div>
				<input name="user" placeholder="사용자를 입력하세요" />
			</div>
			<div>
				<button type="button">처음으로</button>
				<button type="submit">저장</button>
			</div>
		</form>
	</section>
	<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>
</body>
</html>