<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<link href="${rootPath}/static/css/book-write.css?ver=2020-09-24-007" 
				rel="stylesheet">  
<script src="${rootPath}/static/js/book-write.js?ver=2020-09-24-001"></script>
</head>

<form:form id="books" modelAttribute="bookVO">
	<fieldset>
	<legend>도서정보 입력</legend>
	
	<div><label for="title">도서명</label>
		<form:input path="title" placeholder="도서명"/>
		<button id="naver-search" type="button">네이버검색</button>
	</div>
	
	<div><label for="link">상세링크</label>
	<form:input path="link" placeholder="상세링크"/>
	</div>
	
	<div><label for="image">이미지</label>
	<form:input path="image" placeholder="이미지"/>
	</div>
	
	<div><label for="author">저자</label>
	<form:input path="author" placeholder="저자"/>
	</div>
	
	<div><label for="price">도서정가</label>
	<form:input path="price" placeholder="가격"/>
	</div>
	
	<div><label for="discount">할인가격</label>
	<form:input path="discount" placeholder="할인"/>
	</div>
	
	<div><label for="publisher">출판사</label>
	<form:input path="publisher" placeholder="출판사"/>
	</div>
	
	<div><label for="isbn">ISBN</label>
	<form:input path="isbn" placeholder="ISBN"/>
	</div>
	
	<div><label for="description">세부설명</label>
	<form:input path="description" placeholder="세부설명"/>
	</div>
	
	<div><label for="pubdate">출간일자</label>
	<form:input path="pubdate" placeholder="출간일자"/>
	</div>
	
	<div><label for="buydate">구입일자</label>
	<form:input path="buydate" placeholder="구입일자"/>
	</div>
	
	<div><label for="buyprice">구입금액</label>
	<form:input path="buyprice" placeholder="구입금액"/>
	</div>
	
	<div><label for="buystore">구입처</label>
	<form:input path="buystore" placeholder="구입처"/>
	</div>
	
	<div class="button-box">
		<button id="btn-save" type="button">저장</button>
	</div>
	</fieldset>
</form:form>

<section id="book-modal">
	<article id="modal-body">
		<div id="modal-header">
			<span>&times;</span>
		</div>
		<div id="search-result"></div>
	</article>
</section>





