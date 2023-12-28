<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알람 페이지</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/alarm.css' />" type="text/css">
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
	<div id="maintext">알림 목록</div>
	<div id="box">
		<div class="alarm">
			<img src="<c:url value='/images/albumImage.png' />" width="20px">
			<span id="box2">
				<div id="content">앨범이름에 이용자가 참가하고 싶어합니다.<br/> 수락하시겠습니까?</div>
				<div id="acceptbox">
					<input type="button" class="loginbtn" value="거절" onClick="login()">
					<input type="button" class="loginbtn" value="수락" onClick="login()">
				</div>
			</span>
		</div>
		<div class="alarm">
			<img src="<c:url value='/images/albumImage.png' />" width="20px">
			<span id="box2">
				<div id="content">앨범이름에 참가되었습니다.<br/> 앨범이름의 모든 게시물을 구경해보세요.</div>
				<div id="browsebox">
					<input type="button" class="loginbtn" value="보러가기" onClick="login()">
				</div>
			</span>	
		</div>
		<div class="alarm">
			<img src="<c:url value='/images/albumImage.png' />" width="20px">
			<span id="box2">
				<div id="content">앨범이름 앨범의 "일기명"글에 사용자명이 댓글을 남겼습니다.</div>
			</span>
		</div>
		<div class="alarm">
			<img src="<c:url value='/images/albumImage.png' />" width="20px">
			<span id="box2">
				<div id="content">앨범이름 앨범의 "일기명"글을 사용자명이 좋아합니다.</div>
			</span>
		</div>
	</div>
</div>
</body>
</html>