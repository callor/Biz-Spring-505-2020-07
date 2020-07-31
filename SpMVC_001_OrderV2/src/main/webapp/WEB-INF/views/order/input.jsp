<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %> 

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
	<style>
		section {
			margin: 10px;
		}
	</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-header.jspf" %>
	<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>
	
	<section>
	<h3>주문서입력화면</h3>
		<form method="POST">
	        <input name="o_num" placeholder="주문번호를 입력하세요"><br/>
	        <input name="o_date" placeholder="날짜를 입력하세요"><br/>
	        <input name="o_cnum" placeholder="고객번호를 입력하세요"><br/>
	        <input name="o_pcode" placeholder="상품번호를 입력하세요"><br/>
	        <input name="o_price" placeholder="가격을 입력하세요"><br/>
	        <input name="o_qty" placeholder="수량을 입력하세요"><br/>
	        <button>보내기</button>
	    </form>
	</section>
	
	<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>
</body>
</html>