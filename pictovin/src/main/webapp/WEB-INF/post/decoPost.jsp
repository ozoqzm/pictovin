<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다이어리 꾸미기 페이지</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/decoPost.css' />">
</head>

<body>
	<div id="diary">
		<div id="diaryBox">
			<p id="date"><%=java.time.LocalDate.now().toString()%></p>
			<div id="diaryContent">
				<img id="diaryImage"
					src="<c:url value='/images/lifesnapshot.png' />" alt="인생네컷 사진">
				<div id="diaryText">
					<p>오늘 오랜만에 댕댕씨를 만나서 알찬 수다를 떨었다. 오랜만에 맛집도 가고, 분위기 좋은 카페도 갔지만
						댕댕씨와 함께라서 더욱 특별하게 느껴지는 하루였다. 사진도 아주 만족스럽다. 다음에 또 봤으면 좋겠다~</p>
				</div>
			</div>
		</div>
	</div>

	<div id="decorations">
		<p id="sticker">STICKER</p>

		<div id="decoContainer">
			<div class="decoItem">🦊</div>
			<div class="decoItem">☃️</div>
			<div class="decoItem">🎄</div>
			<div class="decoItem">🍀</div>
			<div class="decoItem">🖥️</div>
			<div class="decoItem">🎈</div>
			<div class="decoItem">❤️</div>
			<div class="decoItem">🎶</div>
			<div class="decoItem">👻</div>
			<div class="decoItem">🐨</div>
			<div class="decoItem">👀</div>
			<div class="decoItem">🏆</div>
			<div class="decoItem">👄</div>
			<div class="decoItem">👽</div>
			<div class="decoItem">🫡</div>
			<div class="decoItem">😡</div>
			<div class="decoItem">🤩</div>
			<div class="decoItem">🙉</div>
			<div class="decoItem">😎</div>
			<div class="decoItem">☠️</div>
			<div class="decoItem">🍕</div>
			<div class="decoItem">✈️</div>
			<div class="decoItem">🍬</div>
			<div class="decoItem">🎀</div>
			<div class="decoItem">🎉</div>
			<div class="decoItem">🍂</div>
		</div>
		<a href="<c:url value='/post/tag'/>">
			<button id="completeButton" onclick="completeDiary()">
			다이어리 꾸미기 완료</button>
		</a>
	</div>

</body>

</html>