<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />        
<style>
	section#main-body {
		width: 100%;
		display: flex;
	}
	section#main-body article {
		flex:1;
		margin:10px;
		border:1px solid blue;
		
	}
	
</style>
<section id="main-body">
	<article>
		<p>도서목록</p>
	</article>
	<article>
		<p>독서록</p>
	</article>
	<article>
		<p>공지사항</p>
	</article>	
</section>