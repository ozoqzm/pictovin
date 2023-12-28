<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>로그인</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/login.css' />" type="text/css">
<script>
function login() {
	if (form.userId.value == "") {
		alert("사용자 ID를 입력하십시오.");
		form.userId.focus();
		return false;
	} 
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.password.focus();
		return false;
	}		
	form.submit();
}

function userCreate(targetUri) {
	form.action = targetUri;
	form.method="GET";		// register form 요청
	form.submit();
}
</script>
</head>
<body>
<form name="form" method="POST" action="<c:url value='/user/login' />">
	<div class="title">로그인</div>
	<c:if test="${loginFailed}">
	  	  <br><font color="red"><c:out value="${exception.getMessage()}" /></font><br>
	</c:if>
	<div class="inputWrap">
		<input type="text" class="id" name="userId" placeholder="아이디">
	</div>
	<div class="inputWrap">
		<input type="password" class="pwd" name="password" placeholder="비밀번호">
	</div>
	<div class="option">
		아이디, 비밀번호 찾기 | <a href="<c:url value='/user/join/form'/>">회원가입</a>
	</div>
	<div class="btn">
		<input type="button" class="loginbtn" value="로그인" onClick="login()">
	</div>
	<div class="btn">
		<button class="googlebtn">
			<img src="<c:url value='/images/google.png' />" alt="Google 로그인"> Google 로그인
		</button>
	</div>
</form>
</body>
</html>