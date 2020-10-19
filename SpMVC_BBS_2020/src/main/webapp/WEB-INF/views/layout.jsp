<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>BBS 2020</title>
</head>
<body>
	<%/*
	tiles:insertAttribute 
	다른 jsp 파일을 부착하는 용도의 설정값
	여기에 name으로 설정된 부분에 tile.xml 파일에서 지정한 jsp파일이 부착된다
	*/%>
	<tiles:insertAttribute name="header"/>
	<tiles:insertAttribute name="nav"/>
	<tiles:insertAttribute name="content"/>
	<tiles:insertAttribute name="footer"/>
</body>
</html>



