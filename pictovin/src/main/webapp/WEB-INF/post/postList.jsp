<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="<c:url value='/css/postList.css'/>"
	type="text/css">
<title>일기 목록 조회</title>
<style>
#spanBox {
	display: inline-block;
}
#imageBox img {
    width: 100%;
    height: 100%;
    object-fit: cover; /* 이미지가 박스에 꽉 차게 조정 */
}
</style>
</head>
<body>
	<div id="side">
		<div id="title">
			<a href="<c:url value='/user/main'/>"> <img
				src="<c:url value='/images/title.png'/>" width="130px">
			</a>
		</div>

	</div>
	<hr id="line">
	<div id="box1">
		<a href="<c:url value='/album/list'/>">
			<div id="albumname">< 앨범 목록으로 이동</div>
		</a>
	</div>
	<div id="whiteBox">
		<c:forEach var="photo" items="${photoList}">
			<div id="spanBox">
				<div id="imageBox">
					<!-- postId 전달 -->
					<a
						href="<c:url value='/post/view'>
						<c:param name='postId' value='${photo.postId}'/>
						</c:url>">
						<img src="<c:url value='/upload/${photo.imagePath}'/>" width="200">
					</a>
				</div>
				<div>${photo.postDate}</div>
			</div>
		</c:forEach>
	</div>
	<div id="box">
		<div id="plusbtn">
			<a href="<c:url value='/post/createForm'> 
			<c:param name='albumId' value='${param.albumId}'/>
			</c:url>">
			 <img
				src="<c:url value='/images/plus.png'/>" width="70px;">
			</a>
		</div>
	</div>

	<!-- comment 리스트 반복문 추가
		<c:forEach var="comment" items="${comments}">
			<div id="comment">
				<img id="profile" src="<c:url value='/images/profile.png'/>"
					width="40px" height="40px">
				<div>
					<div id="username">${comment.username}</div>
					<div id="commcontent">${comment.content}</div>
				</div>
			</div>
		</c:forEach>
		 -->
</body>
</html>