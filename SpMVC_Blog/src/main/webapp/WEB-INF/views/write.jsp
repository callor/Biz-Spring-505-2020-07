<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>얼렁뚱땅 블로그</title>

<link rel="stylesheet" type="text/css" href="static/css/main.css" />
<link rel="stylesheet" type="text/css" href="static/css/input.css" />

</head>
<body>
	<header>
		<h3>얼렁뚱땅 블로그 V1</h3>
		<p>나의 얼렁뚱땅 블로그에 방문해 주신것을 환영합니다!!</p>
	</header>
	<section id="main">
		<form action="writer" method="POST">
			<div>
				<input name="title" placeholder="제목을 입력하세요" />
			</div>
			<div>
				<input name="content" placeholder="내용을 입력하세요" />
			</div>
			<div>
				<input name="user" placeholder="사용자를 입력하세요" />
			</div>
			<div>
				<button type="button">처음으로</button>
				<button type="submit">저장</button>
			</div>
		</form>
	</section>
	<footer>
		<address>CopyRight &copy; callor@callor.com</address>
	</footer>
</body>
</html>