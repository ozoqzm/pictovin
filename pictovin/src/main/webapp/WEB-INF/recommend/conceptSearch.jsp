<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>컨셉 추천 페이지</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/conceptSearch.css' />">
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
	cursor: pointer;
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

#photoBox {
	background-color: white;
	padding: 20px;
	width: 700px;
	height: 500px;
	border-radius: 20px;
	margin-top: 10px;
	overflow-y: scroll;
}

img {
    width: 180px;
    height: 500px;
    object-fit: cover; /* 이미지가 박스에 꽉 차게 조정 */
}
</style>
</head>
<body>
	<script>
		function toggleRadio(id) {
			const radio = document.getElementById(id);
			radio.checked = !radio.checked;
		}
	</script>

	<div id="all">
		<form name="form" method="post"
			action="<c:url value='/recommend/search'/>">
			<div id="tag">
				<div id="text1">컨셉 & 포즈 추천</div>
				<div id="tagBox">
					<div id="studio">
						<p>어느 사진관에서 촬영하시나요?</p>
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
						<p>원하는 컨셉을 선택해주세요</p>
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
						<p>몇명이서 찍나요?</p>
						<div class="select">
							<input type="radio" id="number1" name="people" value="혼자">
							<label for="number1" onclick="toggleRadio('number1')">혼자</label>
							<input type="radio" id="number2" name="people" value="2명">
							<label for="number2" onclick="toggleRadio('number2')">2명</label>
							<input type="radio" id="number3" name="people" value="3명">
							<label for="number3" onclick="toggleRadio('number3')">3명</label>
							<input type="radio" id="number4" name="people" value="4명 이상">
							<label for="number4" onclick="toggleRadio('number4')">4명
								이상</label>
						</div>
					</div>
					<div id="age">
						<p>연령대를 선택해주세요</p>
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

					<input type="submit" id="completeButton" value="사진 컨셉 추천받기 🔎">
				</div>
			</div>
		</form>
		<div id="photo">
			<span id="text2">추천된 사진의 컨셉을 참고해 촬영해보세요</span>
			<!-- 사진 있을 때만 뜸!! -->
			<c:if test="${havePhoto eq 'True'}">
				<div id="photoBox">
					<div id="text3">다른 사람들은 이렇게 찍었어요!</div>
					<div id="text4">※ 추천을 허용한 사용자의 사진입니다</div>
					<c:forEach var="photo" items="${photoList}">
				 <img alt="추천된
							사진"
							src="<c:url value='/upload/${photo.imagePath}' />">
				
					</c:forEach>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>

