<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>
<link rel=stylesheet href="<c:url value='/css/main.css' />"
	type="text/css">
<style>
#info {
	text-align: center;
	margin-bottom: 50px;
}

#info a {
	font-size: 18px;
	text-decoration: none;
	color: white;
}
body {
	background-color: #E68CBC;
	margin: 0;
	padding: 0;
}

nav {
	color: white;
	background-color: #94C45A;
	padding: 20px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

nav a {
	color: white;
	text-decoration: none;
	margin: 0 10px;
}

#logo {
	display: block;
	margin: 50px auto;
	width: 35%;
}

#logo-text {
	font-size: 20px;
	font-weight: bold;
	margin-right: auto;
}

#logo-container {
	display: flex;
	flex-direction: column;
	align-items: center;
}

#logo-text2 {
	font-size: 21px;
	color: #ffffff;
	padding-bottom: 18px;
	font-weight: light;
}

#box {
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: center;
}

#diary, #album, #concept {
	width: 270px;
	height: 270px;
	margin: 30px;
	font-size: 20px;
	text-align: center;
	background-color: #ffffff;
	border-radius: 10px;
	padding: 20px;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
	text-decoration: none;
	color: black;
}

.box-description {
	font-size: 15px;
	color: #555;
	margin-top: 5px;
	display: block;
	padding: 20px;
	line-height: 1.8;
}

</style>
</head>
<body>
	<nav>
		<span id="logo-text">PICTOVIN</span> <a
			href="<c:url value='/user/login/form'/>"><b>Login</b></a> <a
			href="<c:url value='/user/join/form'/>"><b>Sign up</b></b></a>
	</nav>
	<div id="logo-container">
		<img id="logo" src="<c:url value='/images/logo.png' />" alt="Logo">
		<span id="logo-text2">Pictovin, 이렇게 활용하세요!</span>
	</div>
	<div id="box">
		<a href="#" id="diary"> <strong>다이어리 작성🐰</strong> <span
			class="box-description"> <b>넘쳐나는 사진들, 어려운 관리!</b> <br> <br> 다이어리 작성 기능으로  <br>나만의 사진 일기를  <br> 더욱 특별하게 기록할
				수 있어요. <br>일상을 더 아름답게 남겨보세요! </span>
		</a>
		<!-- 로그인한 사람만 넘어가게 해야함 -->
		<a href="#" id="album"> <strong>공유 앨범🐰</strong> <span
			class="box-description"> <b>추억을 공유하자! </b><br> <br> 공유앨범 기능으로  <br> 사진과 이야기를 모아 공유하고, 함께한 순간을 더 특별한  <br>기억으로 남기며 소통하세요. </span>
		</a> <a href="#" id="concept"> <strong>사진 컨셉 추천🐰</strong> <span
			class="box-description"> <b>사진이 어려운 당신을 위해!</b> <br><br> 나에게 맞는 예시 사진들로 <br> 촬영을 더
				편하고 특색있게!<br> 추억을 더욱 특별하게 남겨보세요. </span>
		</a>
	</div>
	<div id="info">
		<a href="<c:url value='/recommend/concept'/>">⭐ 사진 컨셉 추천받으러 가기 ⭐</a>
	</div>
</body>
</html>