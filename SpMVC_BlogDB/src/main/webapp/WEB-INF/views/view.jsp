<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-header.jspf" %>
	<section id="main">
		<article id="button">
			<button><a href="input">블로그작성</a></button>
		</article>
		<article id="blog_body">
			<section class="blog_title">
				<h4>${TITLE}</h4>
			</section>
			<section class="blog_text">
				<p>${CONTENT}</p>
			</section>
		</article>
	</section>
	<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>
</body>
</html>