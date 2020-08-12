<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf"%>
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
			<section class="blog_title">
				<h3>윤동주 - 서시(序詩)</h3>
			</section>
			<section class="blog_text">
				<h5>죽는날까지 하늘을 우러러</h5>
				<h5>한 점 부끄럼이 없기를</h5>
				<h5>잎새에 이는 바람에도</h5>
				<h5>나는 괴로워 했다</h5>
				<h5>별을 노래하는 마음으로</h5>
				<h5>모든 죽어가는 것을 사랑해야지</h5>
				<h5>그리고 나에게 주어진 길을</h5>
				<h5>걸어야 겠다</h5>
				<h5>오늘 밤에도 별이 바람에 스치운다.</h5>

			</section>
		</article>
	</section>
	<%@ include file="/WEB-INF/views/include/include-footer.jspf"%>

</body>
</html>