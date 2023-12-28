<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="<c:url value='/css/postView.css' />"
	type="text/css">
<title>일기 상세 조회</title>
<style>
#deletebtn {
position: relative;
text-align: center;
border: 1px;
border-radius: 5px;
background: white;
width: 70px;
left: 400px;
top: 20px;
}
a {
    text-decoration: none;
}

</style>
</head>
<body>
	<script>
		function postRemove() {
			return confirm("정말 삭제하시겠습니까?");
		}
	</script>
	<div id="backbtn">
    <c:url value='/post/list' var="postListUrl">
        <c:param name='albumId' value='${post.albumId}'/>
    </c:url>
    <a href="${postListUrl}">
        <img src="<c:url value='/images/backbtn.png'/>" width="30px;">
    </a>
</div>

	<div id="dateBar">${post.postDate}</div>
	<div id="side">
		<div id="imageZone">
			<img src="<c:url value='/upload/${photo.imagePath}'/>" />
		</div>
		<div id="Box1">
			<div id="whiteBox">
				<p>${post.content}</p>
			</div>
			<div id="tagside">
				<div id="tag">#${tag.studio}</div>
				<div id="tag">#${tag.concept}</div>
				<div id="tag">#${tag.people}</div>
				<div id="tag">#${tag.ageGroup}</div>
			</div>
			<a
				href="<c:url value='/post/delete'>
				   <c:param name='postId' value='${post.postId}'/>
				   <c:param name='albumId' value='${post.albumId}'/>
			 	 </c:url>"
				onclick="return postRemove();"><div id="deletebtn">글 삭제</div></a>
			<c:if test="${deleteFailed}">
				<font color="red"><c:out value="${exception.getMessage()}" /></font>
			</c:if>
		</div>
	</div>
	<hr id="line">
	<div id="commentList">
		<div id="count">댓글 (${replyListSize})</div>
		<div id="comment">
			<form name="form" method="post"
				action="<c:url value='/reply/create'/>">
				<input type="hidden" name="postId" value="${param.postId}" />
				<textarea name="commentText" placeholder="댓글을 입력하세요"
					style="width: 640px; height: 80%;"></textarea>
				<input type="submit" value="전송">
			</form>
		</div>
		<br>
		<c:forEach var="reply" items="${replyList}">
			<div id="comment">
				<img id="profile" src="<c:url value='/images/profile.png'/>"
					width="40px" height="40px">
				<div>
					<span id="username">${reply.userId}</span> &nbsp; <span
						id="commcontent">${reply.commentDate}</span>
					<div id="commcontent">${reply.commentText}</div>
				</div>
			</div>
		</c:forEach>
	</div>

</body>
</html>