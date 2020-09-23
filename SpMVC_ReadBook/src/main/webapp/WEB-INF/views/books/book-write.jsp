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
	* {
		box-sizing: border-box;
		padding:0;
		margin: 0;
	}
	
	html, body {
		width:100%;
		height: 100%;
	}
	
	body {
		overflow: auto;
	}
	
	form#books {
		width:60%;
		margin:10px auto;
	}
	form#books input {
		/*
		만약 input box 와 button 등 다른 tag를 한줄에 나란히 놓으면서
		input box의 width를 임의로 설정하고 싶으면
		display를 inline-block으로 설정하자
		
		block으로 설정하면 width는 임의로 설정할 수 있지만
		다른 tag를 오른쪽에 위치하게 할수 없다
		기본값인 inline 이면 width을 설정할수 없다.
		*/
		display: inline-block;
		width:90%;
		border:1px solid #ddd;
		margin:5px auto;
		padding:8px;
	}
	form#books fieldset {
		border:1px solid rgb(0,100,200);
		border-radius: 10px;
	}
	
	form#books #title {
		width: 70%;
	}
	
	form#books div.button-box {
		width:93%;
		text-align:right;
	}
	
	form#books button {
		border:none;
		outline: 0;
		
		padding:0.5rem 12px;
		border-radius: 5px;
	}
	form#books button#naver-search {
		background-color: green;
		color:white;
	}
	form#books button#btn-save {
		background-color: blue;
		color:white;
	}
	
	form#books button:hover {
		box-shadow: 5px 5px 5px rgba(0,0,0,0.3);
	}
	
	/* Modal 설정
	-----------------------------*/
	section#book-modal {
		position: fixed;
		top:0;
		left:0;
		width:100%;
		height:100%;
		background-color: rgba(0,0,0,0.4);
	}
	article#modal-body {
		position:absolute;
		top:50%;
		left:70%;
		width:70%;
		height:50%;
		transform: translate(-50%, -50%);
		display:flex;
		flex-flow:column nowrap;
	}
	div#modal-header {
		flex:1;		
		width:60%;
		text-align: right;
		background-color: rgba(100,100,100,1);
	}
	
	div#modal-header span {
		font-size: 30px;
		font-weight: 500;
		color:white;
		cursor: pointer;
		margin:15px;
	}
	div#modal-header span:hover {
		color:red;
	}

	div#search-result {
		flex:7;
		width:60%;
		padding:30px;
		overflow:auto;
		
		background-color: rgba(255,255,255,1);
		border:1px solid rgba(0,0,255,1);
		
		box-shadow: 10px 10px 10px rgba(0,0,0,0.5);
		border-bottom-left-radius: 15px; 
		border-bottom-right-radius: 15px;
	}
	
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$("#naver-search").click(function(){
			let title = $("#title").val()
			if(title === "") {
				alert("도서명을 입력한 후 검색을 하세요")
				$("#title").focus()
				return false
			}
			// ajax를 사용하여 서버에 네이버 검색 요청
			$.ajax({
				// ajax로 서버의 /naver/search URL에 POST로 요청을 하면서
				// search_text 변수에 title 변수에 담긴 값을 담아서 전달하고
				url : "${rootPath}/naver/search",
				method:"POST",
				data:{"search_text" : title},
				// 서버가 데이터 조회를 수행한 후 view(HTML코드)코드를 
				// return 하면 그 결과를
				// #search-result div box에 채워서 보여달라
				success : function(result) {
					// $("#search-result").html(result)
				},
				error : function(error) {
					alert("서버 통신 오류!!")
				}
			})
			$("#book-modal").css("display","flex")
		})
		
		// x 표시를 클릭했을때 modal 창 닫기
		$("#book-modal div.header span").click(function(){
			$("#book-modal").css("display","none")
		})
		/*
		동적으로 구현된 HTML에 event 핸들링 설정하기
		현재 document(HTML 문서)가 생성되는 동안이 없던 tag를
		JS(JQ)코드에서 동적으로 생성했을 경우 화면에 그려지는 것은
		아무런 문제가 없으나
		
		JS에서 event핸들러를 설정할때 아직 화면에 없는 tag에 연결을 하면
		무시해 버리고 없던일로 만들어 버린다.
		
		사후에(HTML 문서가 완성된 후) JS 코드로 생성할 tag(id, class)에
		event를 설정하려면 자체에 설정하지 않고
		가장 상위 객체인 document에 on 함수를 사용하여 event를 설정한다.
		$(document).on("event","대상",function(){ } )
		
		주의사항
		
		
		*/
		
		$(document).on("click","div.book-select",function(){
			let isbn = $(this).data("isbn")
			alert(isbn)
		})
	
	})
	
</script>
</head>

<h3>도서정보 등록</h3>
<form method="POST" id="books">
	<fieldset>
	<legend>도서정보 입력</legend>
	<input name="seq" id="seq" placeholder="일련번호"/>
	<input name="title" id="title" placeholder="도서명"/>
	<button id="naver-search" type="button">네이버검색</button>
	<input name="link" id="link" placeholder="상세링크"/>
	<input name="image" id="image" placeholder="이미지"/>
	<input name="author" id="author" placeholder="저자"/>
	<input name="price" id="price" placeholder="가격"/>
	<input name="discount" id="discount" placeholder="할인"/>
	<input name="publisher" id="publisher" placeholder="출판사"/>
	<input name="isbn" id="isbn" placeholder="ISBN"/>
	<input name="description" id="description" placeholder="세부설명"/>
	<input name="pubdate" id="pubdate" placeholder="출간일자"/>
	<input name="buydate" id="buydate" placeholder="구입일자"/>
	<input name="buyprice" id="buyprice" placeholder="구입금액"/>
	<input name="buystore" id="buystore" placeholder="구입처"/>
	<div class="button-box">
		<button id="btn-save" type="button">저장</button>
	</div>
	</fieldset>
</form>

<section id="book-modal">
	<article id="modal-body">
		<div id="modal-header">
			<span>&times;</span>
		</div>
		<div id="search-result"></div>
	</article>
</section>

</body>
</html>





