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
	margin:0 auto;
	box-shadow: 2px 2px 2px rgba(0,0,0,0.5);
	background-color: #ccc;
	padding:20px;
}

section#bbs-button-box {
	width:50%;
	margin:10px auto;
	text-align: right;
}

section#bbs-button-box button {
	margin:10px;
	padding: 10px 16px;
	border:0;
	outline: 0;
	border-radius: 5px;
	font-weight: bold;
}

section#bbs-button-box button:hover {
	box-shadow: 2px 2px 2px rgba(0,0,0,0.6);
}

/* button.bbs-list */
section#bbs-button-box button:nth-child(1) {
	background-color: green;
}
section#bbs-button-box button:nth-child(2) {
	background-color: blue;
	color:white;
}
section#bbs-button-box button:nth-child(3) {
	background-color: orange;
}
</style>
<script>
document.addEventListener("DOMContentLoaded", function() {
	document.querySelector("section#bbs-button-box")
				.addEventListener("click",function(e){
					
		let url = "${rootPath}/bbs/${BBSVO.b_seq}/"
		if(e.target.tagName === ("BUTTON")) {
			
			/*
			게시글 삭제를 요청하면(삭제버튼 클릭)
			ajax 사용하여 서버에 DELETE method type으로 삭제를 요청하자
			*/
			if(e.target.className == "delete") {
				if(confirm("정말 삭제할까요?")) {
					// JSON 객체데이터를 문자열화 하여 HTTP Body 담기
					let data = { 
							seq : "${BBSVO.b_seq}",
							subject : "${BBSVO.b_subject}"
							
					}
					fetch( "${rootPath}/api/bbs",
							{  
								method : "PUT",
								headers : {
									"Content-Type" : "application/json"
								},
								body: JSON.stringify(data)
							}
					)
					.then(function(result){
						alert(result)
					})
					.catch(function(error){
						alert("실패")
					})
					return false;
				}
			}
			document.location.href= url + e.target.className
		}		
	})
})

</script>
<section id="bbs-detail-header">
	<article>
	
	<a href="${rootPath}/upload/${BBSVO.b_file}" target=_new>
		<c:if test="${empty BBSVO.b_file }">
			<img src="${rootPath}/static/files/noImage.png" width="200px">
		</c:if>
		<c:if test="${not empty BBSVO.b_file }">
			<img src="${rootPath}/upload/${BBSVO.b_file}" width="200px">
		</c:if>
	</a>
	
	</article>
	<article>
		<div class="title">제목</div><div class="content">${BBSVO.b_subject}</div>
		<div class="title">작성일시</div><div class="content">${BBSVO.b_date}, ${BBSVO.b_time}</div>
		<div class="title">작성자</div><div class="content">${BBSVO.b_writer}</div>
	</article>
</section>
<section id="bbs-detail-body">
${BBSVO.b_content}
</section>
<section id="bbs-button-box">
	<button class="list">리스트</button>
	<button class="update">수정</button>
	<button class="delete">삭제</button>
</section>
<style>
section#images-box {
	width:50%;
	margin:10px auto;
	padding:5px;
}
section#images-box img{
	margin:3px;
	border-radius: 20px;
}

</style>
<section id="images-box">
	<c:if test="${not empty BBSVO.images}">
		<c:forEach items="${BBSVO.images}" var="image"> 
			<img src="${rootPath}/upload/${image.i_file_name}" height="100px">
		</c:forEach>
	</c:if>
</section>











