<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>얼렁뚱땅 블로그</title>
<link rel="stylesheet" type="text/css" href="static/css/main.css"/>
</head>
<body>
	<header>
		<h3>얼렁뚱땅 블로그 V1</h3>
		<p>나의 얼렁뚱땅 블로그에 방문해 주신것을 환영합니다!!</p>
	</header>
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
	<footer>
		<address>CopyRight &copy; callor@callor.com</address>
	</footer>
</body>
</html>