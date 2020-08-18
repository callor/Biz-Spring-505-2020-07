<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<link rel="stylesheet" type="text/css" 
	href="${rootPath}/static/css/input.css?ver=2020-08-13-001" />
<script>
$(function(){
	
	/*
	저장버튼의 type="button"으로 설정하여 submit기능을 무력화 하고
	jq에서 버튼이 클릭되었을때 실행할 event 핸들러를 만들고
	input box에 값이 있는지 검사하여 없으면 alert을 보이고
	입력받도록 수행하기
	*/
	$("#save").click(function(){
		
		// input tag에 입력된 값을 추출하기
		var user = $("#bl_user").val()
		var title = $("#bl_title").val()
		var contents = $("#bl_contents").val()
		
		if(user == "") {
			alert("작성자 이름은 반드시 입력해야 합니다")
			$("#bl_user").focus()
			return false;
		}
		if(title == "") {
			alert("제목은 반드시 입력해야 합니다")
			$("#bl_title").focus()
			return false;
		}
		if(contents == "") {
			alert("내용을 입력해 주세요")
			$("#bl_contents").focus()
			return false
		}
		
		// form(input)에 입력된 데이터를 서버로 전송하라
		$("form").submit()
		
	})
	
	$("#goHome").click(function(){
		document.location.href = "${rootPath}/blog/list"
	})
})
</script>	
	
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-header.jspf" %>
	<section id="main">
		<form method="POST">
			<input name="bl_seq" value="${BLOG.bl_seq}" type="hidden"/>
			<input name="bl_date" value="${BLOG.bl_date}" type="hidden"/>
			<input name="bl_time" value="${BLOG.bl_time}" type="hidden"/>
			<div>
				<input name="bl_user" id="bl_user" value="${BLOG.bl_user}"  placeholder="사용자를 입력하세요" />
			</div>
			<div>
				<input name="bl_title" id="bl_title" value="${BLOG.bl_title}" placeholder="제목을 입력하세요" />
			</div>
			<div>
				<input name="bl_contents" id="bl_contents" value="${BLOG.bl_contents}" placeholder="내용을 입력하세요" />
			</div>
			<div>
				<button type="button" id="goHome">처음으로</button>
				<button type="button" id="save">저장</button>
			</div>
		</form>
	</section>
	<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>
	
</body>
</html>