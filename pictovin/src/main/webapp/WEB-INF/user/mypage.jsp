<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/mypage.css' />" type="text/css">
<script>
function userUpdate() {
	form.submit();
}
</script>
</head>
<body>
<nav>
	<span id="title"> 
		<a href="<c:url value='/user/main'/>"> 
			<img src="<c:url value='/images/title.png'/>" width="130px">
		</a>
	</span>
	<span id="btnlist"> 
		<span>
			<a href="<c:url value='/album/search'/>">
				<img src="<c:url value='/images/search.png' />" width="20px">
			</a>
		</span> 
		<a href="<c:url value='/user/main'/>"><span>Logout</span></a> 
		<a href="<c:url value='#'/>"><span>Profile</span></a>
		<span><img src="<c:url value='/images/alarm.png' />"></span>
	</span>
</nav>
<hr>
<div id="whiteBox">
<form name="form" method="POST" action="<c:url value='/user/update' />">
	<div id="maintext">Profile</div>
	<div id="circle"><img src="<c:url value='/images/imgIcon.png'/>" width="70px"></div>
	<div class="box">
		<span class="box2">이름</span>
		<input type="text" class="name" name="name" placeholder="${user.name}">
	</div>
	<div class="box">
		<span class="box2">이메일</span>
		<input type="text" class="email" name="email" placeholder="${user.email}">
	</div>
	<div class="edit">
		<input type="button" class="registerbtn" value="수정하기" onClick="userUpdate()">
	</div>
</form>
</div>
</body>
</html>