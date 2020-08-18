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
				<section class="blog_title" onclick="goView(${BLOG.bl_seq})">
					<h3>${BLOG.bl_title} - <span>${BLOG.bl_user}</span></h3>
				</section>
				<section class="blog_text">
					<h5>${BLOG.bl_contents}</h5>
				</section>
			</c:forEach>
		</article>
	</section>
	<%@ include file="/WEB-INF/views/include/include-footer.jspf"%>
	<script>
		function goView(seq) {
			// 현재 보고있는 화면에서 href="주소"로 화면을 전환하라
			document.location.href
				="${rootPath}/blog/view?seq=" + seq
		}
	
	</script>
</body>
</html>