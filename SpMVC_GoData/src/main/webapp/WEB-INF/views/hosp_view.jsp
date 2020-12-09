<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>    
<section>
	<table class="hosp-list">
		<tr>
			<th>병원이름</th>
			<th>도로명주소</th>
			<th>지번주소</th>
			<th>전화번호</th>
			<th>위도</th>
			<th>경도</th>
			<th>데이터기준일</th>
		</tr>
		<c:choose>
			<c:when test="${empty H_LIST}">
				<tr><td colspan="7">데이터가 없음</td></tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${H_LIST}" var="hs">
				<tr>
					<td>${hs.apiDongName}</td>
					<td>${hs.apiNewAddress}</td>
					<td>${hs.apiOldAddress}</td>
					<td>${hs.apiTel}</td>
					<td>${hs.apiLat}</td>
					<td>${hs.apiLng}</td>
					<td>${hs.apiRegDate}</td>
				</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</section>