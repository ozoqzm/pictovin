<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="<c:url value='/css/createPost.css' />"
	type="text/css">
<title>일기 작성</title>
<style>
.select {
	position: relative;
	margin: auto;
	padding: 15px 10px;
	display:inline-block;
	bottom: 10px;
	left: 20px;
}

.select input[type=radio] {
	display: none;
}

.select input[type=radio]+label {
	background-color: #E9E9E9;
	border: #E9E9E9;
	border-radius: 50px;
	padding: 5px;
	width: 75px;
	font-size: 13px;
	font-weight: bold;
	margin: 5px;
	text-align: center;
	display: inline-block;
}

.select input[type=radio]:checked+label {
	border: 2px solid #C39FFF;
}
</style>

<script>
	function postCreate() {
		if (form.content.value == "") {
			alert("내용을 입력하십시오.");
			form.name.focus();
			return false;
		}
		if (form.imagePath.value == "") {
			alert("사진을 입력하십시오.");
			return false;
		}

		form.submit();
	}

	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				document.getElementById('preview').src = e.target.result;
			};
			reader.readAsDataURL(input.files[0]);
		} else {
			document.getElementById('preview').src = "";
		}
	}

	function triggerImageInput() {
		var imageInput = document.getElementById('imageInput');
		imageInput.click();
	}
</script>
</head>
<body>
	<form name="form" method="post" action="<c:url value='/post/create'/>"
		enctype="multipart/form-data">
		<div id="whiteBox">
			<input type="text" style="width: 240" name="postDate" id="dateBar"
				value=<%=java.time.LocalDate.now().toString()%> readonly>
			<div id="side">
				<div id="imageZone" onclick="triggerImageInput()">
					<img id="preview" />
					<div id="plusIcon">
						<img src="<c:url value='/images/addpic.png'/>" width="90px">
					</div>
					<!-- 사진 파일 전송 -->
					<input type="file" name="imagePath" id="imageInput" size="11"
						multiple="multiple" onchange="readURL(this);">
				</div>
				<input type="hidden" name="albumId" value="${param.albumId}" />
				<c:if test="${creationFailed}">
					<font color="red"><c:out value="${exception.getMessage()}" /></font>
				</c:if>
				<textarea id="textBox" name="content" placeholder="내용을 입력하세요"
					<c:if test="${creationFailed}"> value="${post.content}"</c:if>></textarea>
			</div>
			<div class="select">
				<input type="radio" id="select" name="privacyStatus" value="Public"
					checked="checked"><label for="select">공개</label> <input
					type="radio" id="select2" name="privacyStatus" value="Private"><label
					for="select2">비공개</label>
			</div>
		</div>
		<img id="nextbtn" onClick="postCreate()" src="<c:url value='/images/nextbtn2.png'/>" width="150px">
	</form>
</body>
</html>