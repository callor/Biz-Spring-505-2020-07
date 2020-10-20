<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<style>
section#bbs-detail-header {
	width:50%;
	border: 1px solid blue;
	margin: 20px auto;
	display: flex;
	box-shadow: 2px 2px 2px rgba(0,0,0,0.5)
}

section#bbs-detail-header article:first-child {
	flex:1;	
}

section#bbs-detail-header article:last-child {
	flex:2;
}

section#bbs-detail-header div {
	margin:5px;
	padding:10px;
	border-bottom: 1px solid #ddd
}

section#bbs-detail-header .title{
	display: inline-block;
	width:25%;
	background-color: #ddd;
	font-weight: bold;
	text-align: right;
}
section#bbs-detail-header .content{
	display: inline-block;
	width:60%;
}

section#bbs-detail-header img {
	margin:5px;
	border-radius: 5px;
	box-shadow: 2px 2px 2px rgba(0,0,0,0.5)
}

section#bbs-detail-body {
	width:50%;
	margin:10px auto;
	box-shadow: 2px 2px 2px rgba(0,0,0,0.5)
}

</style>
<section id="bbs-detail-header">
	<article><img src="${rootPath}/upload/${BBSVO.b_file}" width="200px"></article>
	<article>
		<div class="title">제목</div>
		<div class="content">${BBSVO.b_subject}</div>
		<div class="title">작성일시</div>
		<div class="content">${BBSVO.b_date}, ${BBSVO.b_time}</div>
		<div class="title">작성자</div>
		<div class="content">${BBSVO.b_writer}</div>
	</article>
</section>
<section id="bbs-detail-body">
${BBSVO.b_content}
</section>
