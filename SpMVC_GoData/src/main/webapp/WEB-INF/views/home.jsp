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
		<li class="get-station">노선정보가져오기</li>
		<li>
			<form>
				<input name="hosp" 
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
		
	</ul>
</nav>
<c:if test="${BODY == 'BIS'}">
	<%@ include file="/WEB-INF/views/station_view.jsp" %> 
</c:if>
<c:if test="${BODY == 'PET'}">
	<%@ include file="/WEB-INF/views/hosp_view.jsp" %> 
</c:if>



</body>
</html>