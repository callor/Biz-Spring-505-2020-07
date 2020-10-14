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
<title>나의 홈페이지</title>
<style>
	
	* {
		box-sizing: border-box;
		margin:0;
		padding:0;
	}
	
	html,body {
		width:100%;
		height: 100%;
	}
	
	body {
		display: flex;
		flex-direction: column;
	}
	
	header {
		padding:2rem;
		background-color: green;
		color:white;
		text-align: center;
	}
	
	section#main-content {
		/*
		html, body 의 height : 100%로 설정하고,
		body를 flex로 설정,
		flex-direction : column 으로 설정
		header, nav, content, footer 를 각각 배열하고
		
		content가 있는 box에만 flex:1 로 설정하면
		세로방향 전체 가득찬 layout이 만들어진다.
		
		*/
		flex:1;
		
		/* content box에 포함되는 내용이 넘치면 자동으로 scroll bar를 형성 */
		overflow: auto;
	}
	
	nav#main-nav ul {
		list-style: none;
		display: flex;
		background-color: blue;
	}
	nav#main-nav li {
		padding:8px 16px;
		margin:0px 5px;
		color:white;
		border-bottom: 3px solid transparent;
		cursor: pointer;
		transition: border-color 0.3s linear;
	}
	
	nav#main-nav li:hover {
		border-bottom: 3px solid yellow; 
	}
	
	nav#main-nav li:nth-child(3) {
		margin-left: auto;
	}
	
	footer {
		background-color: black;
		color:white;
		text-align: center;
		padding:0.7rem;
	}
</style> 

</head>
<body>
	<tiles:insertAttribute name="header"/>
	<tiles:insertAttribute name="menu" />
	<section id="main-content">
		<tiles:insertAttribute name="content" />
	</section>
	<tiles:insertAttribute name="footer" />
</body>
</html>





