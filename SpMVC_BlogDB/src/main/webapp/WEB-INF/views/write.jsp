<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<link rel="stylesheet" type="text/css" 
	href="${rootPath}/static/css/input.css?ver=2020-08-13-001" />
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-header.jspf" %>
	<section id="main">
		<form method="POST">
			<input name="bl_seq" value="${BLOG.bl_seq}" type="hidden"/>
			<input name="bl_date" value="${BLOG.bl_date}" type="hidden"/>
			<input name="bl_time" value="${BLOG.bl_time}" type="hidden"/>
			<div>
				<input name="bl_user" value="${BLOG.bl_user}"  placeholder="사용자를 입력하세요" />
			</div>
			<div>
				<input name="bl_title" value="${BLOG.bl_title}" placeholder="제목을 입력하세요" />
			</div>
			<div>
				<input name="bl_contents" value="${BLOG.bl_contents}" placeholder="내용을 입력하세요" />
			</div>
			<div>
				<button type="button" onclick="goHome()">처음으로</button>
				<button type="submit">저장</button>
			</div>
		</form>
	</section>
	<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>
	<script>
		function goHome() {
			document.location.href ="${rootPath}/"
		}
	
	</script>
	
</body>
</html>