<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf"%>
<style>
 #main {
 	background-image: url("${rootPath}/static/images/road-4143370.jpg");
  }

</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-header.jspf"%>

	<section id="main">
		<article id="button">
			<button>
				<a href="${rootPath}/blog/input">블로그작성</a>
			</button>
		</article>
		<article id="blog_body">
			<c:forEach items="${BLOGS}" var="BLOG">
				<section class="blog_title">
					<h3>${BLOG.title} - <span>${BLOG.user}</span></h3>
				</section>
				<section class="blog_text">
					<h5>${BLOG.content}</h5>
				</section>
			</c:forEach>
		</article>
	</section>
	<%@ include file="/WEB-INF/views/include/include-footer.jspf"%>
</body>
</html>