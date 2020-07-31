<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>나의 홈페이지</title>
</head>
<body>
	<h3>주문내역 상세보기</h3>
	<p>SEQ : ${ORDER.o_seq}</p>
	<p>주문번호 : ${ORDER.o_num}</p>
	<p>고객번호 : ${ORDER.o_cnum}</p>
	<p>주문일자 : ${ORDER.o_date}</p>
	<p>상품코드 : ${ORDER.o_pcode}</p>
	<p>가격 : ${ORDER.o_price}</p>
	<p>수량 : ${ORDER.o_qty}</p>
</body>
</html>

