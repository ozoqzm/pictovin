<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>태그 추가하기</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/addTag.css' />">
<script>
	function toggleRadio(id) {
		const radio = document.getElementById(id);
		radio.checked = !radio.checked;
	}
</script>
<style>
.select {
	position: relative;
	margin: auto;
	padding: 8px 8px;
	display: inline-block;
	bottom: 0px;
}

.select input[type=radio] {
	display: none;
}

.select label {
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
	cursor: pointer; /* 추가: 마우스 커서를 포인터로 변경 */
}

.select input[type=radio]:checked+label {
	border: 2px solid #C39FFF;
}
#completeButton {
	background-color: #C39FFF;
	border: #C39FFF;
	color: #ffffff;
	width: 450px;
	font-size: 18px;
	font-weight: 1;
	padding: 10px;
	border-radius: 50px;
}
</style>
</head>
<body>
	<form name="form" method="post" action="<c:url value='/post/addtag'/>">
		<div id="tag">
			<div id="tagBox">
				<div id="studio">
					<p>어느 사진관에서 촬영하셨나요?</p>
					<div class="select">
						<input type="radio" id="studio1" name="studio" value="하루필름">
						<label for="studio1" onclick="toggleRadio('studio1')">하루필름</label>
						<input type="radio" id="studio2" name="studio" value="포토이즘">
						<label for="studio2" onclick="toggleRadio('studio2')">포토이즘</label>
						<input type="radio" id="studio3" name="studio" value="인생네컷">
						<label for="studio3" onclick="toggleRadio('studio3')">인생네컷</label>
					</div>
				</div>
				<div id="concept">
					<p>사진의 컨셉을 선택해주세요</p>
					<div class="select">
						<input type="radio" id="concept1" name="concept" value="재밌는">
						<label for="concept1" onclick="toggleRadio('concept1')">재밌는</label>
						<input type="radio" id="concept2" name="concept" value="귀여운">
						<label for="concept2" onclick="toggleRadio('concept2')">귀여운</label>
						<input type="radio" id="concept3" name="concept" value="진지한">
						<label for="concept3" onclick="toggleRadio('concept3')">진지한</label>
						<input type="radio" id="concept4" name="concept" value="매력발산">
						<label for="concept4" onclick="toggleRadio('concept4')">매력발산</label>
					</div>
				</div>
				<div id="number">
					<p>몇명이서 찍었나요?</p>
					<div class="select">
						<input type="radio" id="number1" name="people" value="혼자">
						<label for="number1" onclick="toggleRadio('number1')">혼자</label> <input
							type="radio" id="number2" name="people" value="2명"> <label
							for="number2" onclick="toggleRadio('number2')">2명</label> <input
							type="radio" id="number3" name="people" value="3명"> <label
							for="number3" onclick="toggleRadio('number3')">3명</label> <input
							type="radio" id="number4" name="people" value="4명 이상"> <label
							for="number4" onclick="toggleRadio('number4')">4명 이상</label>
					</div>
				</div>
				<div id="age">
					<p>촬영 대상자의 연령대를 선택해주세요</p>
					<div class="select">
						<input type="radio" id="age1" name="ageGroup" value="1020">
						<label for="age1" onclick="toggleRadio('age1')">1020</label> <input
							type="radio" id="age2" name="ageGroup" value="30대"> <label
							for="age2" onclick="toggleRadio('age2')">30대</label> <input
							type="radio" id="age3" name="ageGroup" value="40대 이상"> <label
							for="age3" onclick="toggleRadio('age3')">40대 이상</label> <input
							type="radio" id="age4" name="ageGroup" value="다양함"> <label
							for="age4" onclick="toggleRadio('age4')">다양함</label>
					</div>
				</div>
				<c:set var="postId" value="${param.postId}" />
				<c:set var="albumId" value="${param.albumId}" />
			

				<input type="hidden" name="postId" value="${param.postId}" /> <input
					type="hidden" name="albumId" value="${param.albumId}" /> <input
					type="submit" id="completeButton" value="태그 선택 완료">

			</div>
		</div>
	</form>
</body>
</html>