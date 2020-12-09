<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" 
		href="${rootPath}/static/css/station.css?ver=2020-12-09"/>
		
<article class="station">
	<table class="station-list">
		<tr>
			<th>정류소ID</th>
			<th>정류소명</th>
			<th>다음정류소</th>
		</tr>	
		<c:forEach items="${ST_LIST}" var="st">
			<tr>
				<td>${st.STATION_NUM}</td>
				<td>${st.BUSSTOP_NAME}</td>
				<td>${st.NEXT_BUSSTOP}</td>
			</tr>
		</c:forEach>
	</table>
</article>
