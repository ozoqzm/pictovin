<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='/css/editPost.css' />"
	type="text/css">
<title>일기 수정</title>

<script>
	function previewImage() {
		var preview = new FileReader();
		preview.onload = function(e) {
			document.getElementById("image").src = e.target.result;
		};
		preview.readAsDataURL(document.querySelector("input[type=file]").files[0]);
	};
	
	function postModify() {
		if (form.content.value == "") {
			alert("내용을 입력하십시오.");
			form.content.focus();
			return false;
		}
		
		// 여기서 formdata로 이미지이름 전송하는 코드 추가해야함
		// 추가한 파일 이름 ex. xxx.png
		 var imagename = document.getElementById("inputfile").files[0].name;
		 console.log(imagename);
		 
		form.submit();
		
	}

</script>
</head>
<body>
	<div id="backbtn">
		<img src="<c:url value='/images/backbtn.png'/>" width="30px;">
	</div>
	<form name="form" method="POST" action="<c:url value='/post/update' />">
	<div id="whiteBox">
		<div id="dateBar">날짜 데이터 불러오기</div>
		<div id="side">
			<div id="imageZone">
				<img id="image" src="<c:url value='/images/profile.png'/>" />
			</div>
			<textarea id="textBox" placeholder="내용을 입력하세요 " name="value" value="content"></textarea>
		</div>
		<input id=inputfile type="file" name="image" onchange="previewImage()">
		<input id="updatebtn" type="button" value="수정완료" onClick="postModify()"> 
	</div>
	</form>
</body>
</html>
