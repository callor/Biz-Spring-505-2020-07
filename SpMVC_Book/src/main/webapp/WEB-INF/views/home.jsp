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
</head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.0/axios.min.js"></script>
<script>
	document.addEventListener("DOMContentLoaded",function(){
	
		axios.post("${rootPath}/api/insert",
				{
					title : "자바야놀자",
					author : "홍길동",
					comp : "우리출판사",
					price : 15000
		
				})
		.then(
			document.location.href = "${rootPath}/api/list"		
		)
		
	})
	
</script>
<body>
<h3>나의 홈페이지 방문을 환영합니다</h3>



</body>
</html>