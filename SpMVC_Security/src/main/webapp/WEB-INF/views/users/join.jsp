<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />        
<form>
	<fieldset>
		<legend>회원가입</legend>
		<input placeholder="USER ID">
		<input placeholder="PASSWORD">
		<button>가입신청</button>
	</fieldset>
</form>