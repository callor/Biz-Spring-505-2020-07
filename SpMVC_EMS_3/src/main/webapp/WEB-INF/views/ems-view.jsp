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
<body>
<h3>나의 홈페이지 방문을 환영합니다</h3>
<p>보내는 Email : ${from_email}</p>
<p>받는 Email : ${to_email}</p>
<p>보내는 날짜 : ${EMS.s_date}</p>
<p>보내는 시각  : ${EMS.s_time}</p>
<p>제목 : ${s_subject}</p>
<p>내용  : ${EMS.s_content}</p>
</body>
<a href="${rootPath}/">처음으로</a>
</html>