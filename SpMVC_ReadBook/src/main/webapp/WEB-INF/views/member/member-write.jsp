<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" 
			href="${rootPath}/static/css/member-write.css?ver=2020-09-28" />
			
<form:form modelAttribute="memberVO" id="member-write">
	<fieldset>
		<legend>회원가입</legend>
		<div>
			<label>회원 ID</label> 
			<form:input path="username" class="username"/>
			<button type="button" id="m_code_gen" class="m_code_gen">ID 중복검사</button>
		</div>
		<div>
			<label>비밀번호</label> 
			<form:input path="password" type="password"/>
		</div>
		<div>
			<label>비밀번호 확인</label> 
			<input name="re_password" id="re_password" type="password"/>
		</div>
		<div>
			<label>회원이름</label> 
			<form:input path="m_name" />
		</div>
		<div>
			<label>이메일</label> 
			<form:input path="m_email" />
		</div>
		<div>
			<label>전화번호</label> 
			<form:input path="m_tel" />
		</div>
		<div>
			<label>주소</label> 
			<form:input path="m_address" />
		</div>
		<div id="btn_box">
			<button type="button" id="btn_home">홈으로</button>
			<button type="submit" id="btn_save">가입신청</button>
		</div>
	</fieldset>
</form:form>



