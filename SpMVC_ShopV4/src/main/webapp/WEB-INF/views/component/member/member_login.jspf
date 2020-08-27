<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${rootPath}/static/css/login.css?ver=2020-08-26"/>
<style>
	#login_body h4 {
		width:90%;
		margin:10px auto;
		background-color: red;
		color:yellow;
		padding:8px 20px;
		display: none;
		border-radius: 25px;
	}
</style>
<script>
$(function(){
	
	if("${MSG}" != "" ) {
		$("#msg").css("display","block")
	}
	
})
</script>

<body id="login_body">
    <form:form modelAttribute="LOGIN_VO">
      <h2>로그인</h2>
      <h4 id="msg">${MSG}</h4>
      <form:input path="m_userid" placeholder="사용자 ID" />
      <form:input path="m_password" type="password" placeholder="비밀번호" />
      <button>로그인</button>
      <button type="button">회원가입</button>
    </form:form>
 </body>