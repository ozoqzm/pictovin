<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>사용자 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/login.css' />" type="text/css">
<script>
function userCreate() {
	if (form.name.value == "") {
		alert("이름을 입력하십시오.");
		form.name.focus();
		return false;
	}
	if (form.userId.value == "") {
		alert("사용자 ID를 입력하십시오.");
		form.userId.focus();
		return false;
	}
	var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(emailExp.test(form.email.value)==false) {
		alert("이메일 형식이 올바르지 않습니다.");
		form.email.focus();
		return false;
	}
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.password.focus();
		return false;
	}
	if (form.password.value != form.password2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		form.name.focus();
		return false;
	}
	
	form.submit();
}

function userList(targetUri) {
	form.action = targetUri;
	form.submit();
}

</script>
</head>
<body>
<form name="form" method="POST" action="<c:url value='/user/register' />">
	<div class="title">회원가입</div>
	<c:if test="${registerFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	</c:if>
	<div class="inputWrap">
		<input type="text" class="name" name="name" placeholder="이름">
		<div>
			<input type="button" class="chk_btn" value="중복 확인">
		</div>
	</div>
	<div class="inputWrap">
		<input type="text" class="id" name="userId" placeholder="아이디">
	</div>
	<div class="inputWrap">
		<input type="text" class="email" name="email" placeholder="이메일 주소"
			<c:if test="${registerFailed}">value="${user.email}"</c:if>>
	</div>
	<div class="inputWrap">
		<input type="password" class="pwd" name="password" placeholder="비밀번호">
	</div>
	<div class="inputWrap">
		<input type="password" class="pwd_chk" name="password2" placeholder="비밀번호 확인">
	</div>
	<div class="btn">
		<input type="button" class="registerbtn" value="회원가입" onClick="userCreate()">
	</div>
</form>
</body>
</html>