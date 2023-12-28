<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진관 정보 페이지</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/studio.css' />" type="text/css">
</head>
<body>
<div class="title">
	<img src="<c:url value='/images/megaphone.png' />" alt="icon">
	네컷 사진관 정보는 여기서 확인하세요!
</div>
<br>
<div id="box">
	<span id="studio1">인생네컷<br/>
		<img src="<c:url value='/images/studio/insaengLogo.png' />" alt="logo">
	</span>
	<span id="studio2">하루필름<br/>
		<img src="<c:url value='/images/studio/harufilmLogo.png' />" alt="logo">
	</span>
	<span id="studio3">포토이즘<br/>
		<img src="<c:url value='/images/studio/photoismLogo.png' />" alt="icon">
	</span>
</div>
</body>
</html>