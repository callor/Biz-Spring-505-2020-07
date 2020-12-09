<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="styleSheet" href="${rootPath}/static/css/main.css?ver=2020-12-08"/>
<script>
	document.addEventListener("DOMContentLoaded",function(){
		const menu_station = document.querySelector("nav.main-nav .get-station")
		menu_station.onclick = function(){
			document.location.href="${rootPath}/bis/station"
		}
		
		const select_cat = document.querySelector("select[name='cat']")
		
		// select input box의 change 이벤트를 설정하기
		select_cat.onchange = function(e) {
			// change 이벤트가 발생하면 value값을 추출하고
			const value = e.target.value
			// value값이 hosp이면 
			if(value === 'hosp') {
				// input box의 placeholder를 변경
				document.querySelector("input[name='search']")
						.placeholder = '병원명 입력후 enter...'
			} else {
				document.querySelector("input[name='search']")
						.placeholder = '주소 입력후 enter...'
			}
		}
	})
</script>

</head>
<body>
<header>
	<h2>공공DB활용</h2>
</header>
<nav class="main-nav">
	<ul>
		<li>Home</li>
		
		<li>
			<form>
				<select name="cat">
					<option value="hosp">병원명</option>
					<option value="addr">주소</option>
				</select>
				<input name="search" 
					type="search"
					placeholder="동물병원 이름을 입력한 후 Enter">
			</form>
		</li>
		<li>
			<form action="${rootPath}/bis/station">
				<input name="station" 
					type="search"
					placeholder="버스 정류소 이름을 입력한 후 Enter">
			</form>
		</li>
		<li class="get-station">노선정보가져오기</li>
		
	</ul>
</nav>
<c:if test="${BODY == 'STATION'}">
	<%@ include file="/WEB-INF/views/station_view.jsp" %> 
</c:if>
<c:if test="${BODY == 'PET'}">
	<%@ include file="/WEB-INF/views/hosp_view.jsp" %> 
</c:if>
</body>
</html>