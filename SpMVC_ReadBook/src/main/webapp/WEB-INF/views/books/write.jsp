<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>나의 홈페이지</title>
</head>
<body>
<style>
	form#books {
		width:60%;
		margin:10px auto;
	}
	form#books input {
		display: block;
		width:90%;
		border:1px solid #ddd;
		margin:5px auto;
		padding:8px;
	}
	form#books fieldset {
		border:1px solid rgb(0,100,200);
		border-radius: 10px;
	}
	
</style>

<h3>도서정보 등록</h3>
<form method="POST" id="books">
	<fieldset>
	<legend>도서정보 입력</legend>
	<input name="seq" placeholder="일련번호"/>
	<input name="title" placeholder="도서명"/>
	<input name="link" placeholder="상세링크"/>
	<input name="image" placeholder="이미지"/>
	<input name="author" placeholder="저자"/>
	<input name="price" placeholder="가격"/>
	<input name="discount" placeholder="할인"/>
	<input name="publisher" placeholder="출판사"/>
	<input name="isbn" placeholder="ISBN"/>
	<input name="description" placeholder="세부설명"/>
	<input name="pubdate" placeholder="출간일자"/>
	<input name="buydate" placeholder="구입일자"/>
	<input name="buyprice" placeholder="구입금액"/>
	<input name="buystore" placeholder="구입처"/>
	<button>저장</button>
	</fieldset>
</form>
</body>
</html>





