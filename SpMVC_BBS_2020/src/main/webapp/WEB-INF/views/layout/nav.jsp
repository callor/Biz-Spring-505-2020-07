<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />      
<script>
	// 화면에 Content가 모두 loading 되면
	document.addEventListener("DOMContentLoaded",function(){
		// id가 nav-bbs인 tag에 click이벤트 설정
		document.querySelector("#nav-bbs").addEventListener("click",function(){
			document.location.href="${rootPath}/bbs/list"
		})
	})
	
</script>  
<nav>
	<ul>
		<li>Home</li>
		<li id="nav-bbs">자유게시판</li>
	</ul>
</nav>