<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />        
<style>
	table#book-detail {
		width:60%;
		margin:20px auto;
	}
	
	table#book-detail .title td {
		padding:0.5rem 1rem;
		background-color: #ddd;
		border-bottom: 1px solid gray;
	}
	
	table#book-detail .info td:first-child {
		display: flex;
		justify-content: center;
		align-items: center;
		padding:10px
	}
	
	table#book-detail table th {
		color:blue;
		padding:8px 12px;
		text-align: right;
		border-bottom: 1px solid gray;
	}
	table#book-detail table td {
		border-bottom: 1px solid gray;
	} 
	
	table#book-detail .dummy {
		padding: 30px;
	}
	
	table#book-detail .desc-title th {
		text-align: left;
		padding: 10px 30px;
		color:whilte;
		background-color: green; 
	}
	
	table#book-detail .desc td {
		padding:15px;
	} 
	
	table#book-detail .link th,
	table#book-detail .link td {
		background-color: #ccc;
		padding:10px;
	}
	
	section#read-book-body {
		width:60%;
		border:1px solid green;
		margin:5px auto;
	}
	
</style>

<table id="book-detail">
	<tr class="title"><td colspan="2"><h3>${BOOKVO.title}</h3></td></tr>
	<tr class="info">
		<td><img src="${BOOKVO.image}"></td>
		<td>
			<table>
				<tr class="author"><th>저자</th><td>${BOOKVO.author}</td>
						<td>${BOOKVO.pubdate}</td></tr>
				<tr class="price"><th>가격</th><td>${BOOKVO.price}</td>
						<td>${BOOKVO.discount}</td></tr>
				<tr class="pub"><th>출판사</th><td>${BOOKVO.publisher}</td>
							<td>${BOOKVO.isbn}</td></tr>
			</table>
		</td>
	</tr>
	
	<tr><td class="dummy" colspan="2"></td></tr>
	
	<tr class="desc-title"><th colspan="2">책소개</th></tr>
	<tr class="desc"><td colspan="2">${BOOKVO.description}</td></tr>
	
	<tr class="link">
		<th>자세히보기</th>
		<td><a href="${BOOKVO.link}" target=_new>바로가기</a></td>
	</tr>
		
	<tr class="buy">
		<th colspan="2">
			<p>구입일 : ${BOOKVO.buydate}
			| 구입가격 : ${BOOKVO.buyprice }
			| 구입처 : ${BOOKVO.buystore}
		</th>
	</tr>
</table>
<section id="read-book-body">
	<%@ include file="/WEB-INF/views/read-book/read-book-write.jsp" %>
</section>



