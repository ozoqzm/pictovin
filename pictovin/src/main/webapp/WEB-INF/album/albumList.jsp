<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/albumList.css">
<title>앨범 목록 페이지</title>
<style>
nav a {
	text-decoration: none;
	color: #423151;
}

body {
	position: relative;
	padding-top: 2%;
	background-color: #BBD8F8;
	overflow-y: auto; /* 수직 스크롤바 */
}

#side {
	position: relative;
	margin: auto;
	margin-bottom: 30px;
	display: flex;
	justify-content: space-between;
	width: 730px;
}

#title {
	position: relative;
	left: 0px;
	margin-right: 880px;
}

#box1 {
	position: relative;
	margin: auto;
	width: 800px;
	height: 30px;
	margin-top: 30px;
}

#whiteBox {
	padding: 30px;
	position: relative;
	margin: auto;
	width: 800px;
	height: 570px;
	background: white;
	overflow-y: auto;
	position: relative;
}

#box {
	position: relative;
	margin: auto;
	width: 800px;
	height: 50px;
}

#plusbtn {
	position: absolute;
	right: -20px;
	bottom: 30px;
	z-index: 3;
}

#line {
	width: 100%;
	height: 4px;
	background: #FFF;
	border: none;
}

#imageBox {
	position: relative;
	margin: auto;
	margin-top: 40px;
	margin-left: 30px;
	width: 175px;
	height: 500px;
	border: 1px solid #423151;
	display: inline-block;
	overflow: hidden;
}

#search {
	width: 20px;
	height: 20px;
}

#albumImage {
	display: inline-block;
	position: relative;
	margin-left: 20px;
	margin-bottom: 10px;
	position: relative;
}

#albumName {
	margin: auto;
}

span {
	margin-left: 20px;
}

.album-container {
	margin-bottom: 20px;
	display: inline-block;
}

#btnlist {
	display: inline-block;
}

nav {
	color: #423151;
	display: flex;
	justify-content: space-between;
	align-items: center;
}
</style>
</head>
<body>
	<nav>
		<span id="title"> <a href="<c:url value='/user/main'/>"> <img
				src="<c:url value='/images/title.png'/>" width="130px">
		</a>
		</span> <span id="btnlist"> <span> <a
				href="<c:url value='/album/search'/>"> <img
					src="<c:url value='/images/search.png' />" width="20px">
			</a>
		</span> <a href="<c:url value='/user/main'/>"><span>Logout</span></a> <a
			href="<c:url value='/user/mypage'/>"><span>Profile</span></a> <span><a
				href="<c:url value='/user/alarm'/>"><img
					src="<c:url value='/images/alarm.png' />"></a></span>
		</span>
	</nav>
	<hr id="line">
	<!-- 일기 리스트 나중에 반복문으로 수정 -->
	<div id="box1">
		<div id="albumname">
			<!-- Sort -->
			<div id="sort">
				<div class="create-order" id="create-order">생성순</div>
			</div>
		</div>
	</div>
	<!-- 바탕 -->
	<div id="whiteBox">
		<div id="albumImage">

			<c:forEach var="album" items="${albumList}">
				<span class="album-container">
					<div class="pageImg">
						<a
							href="<c:url value="/post/list">
    <c:param name="albumId" value="${album.albumId}" /></c:url>">
							<img src="<c:url value='/images/albumImage.png' />" width="150px">
						</a>
					</div>
					<div>${album.albumName}</div>
				</span>
			</c:forEach>
		</div>
	</div>
	<div id="box">
		<div id="plusbtn">
			<a href="<c:url value='/album/create'/>"> <img
				src="<c:url value='/images/plus.png'/>" width="70px;">
			</a>
		</div>
	</div>

</body>
</html>