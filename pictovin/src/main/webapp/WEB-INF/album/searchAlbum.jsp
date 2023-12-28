<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공유 앨범 검색 페이지</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/searchAlbum.css' />">
</head>
<body>
<form name="form" method="POST" action="<c:url value='/album/search/algo' />">
	<div class="title">공유 앨범 검색</div>
	<div class="searchWrap">
		<input type="text" class="search" name="search" placeholder="참가하고 싶은 공유 앨범을 검색하세요">
		<img src="<c:url value='/images/search.png' />" alt="search icon" class="searchIcon">
	</div>
	<div class="box">
	<c:if test="${searchFailed}">
	  	  <br><font color="red"><c:out value="${exception.getMessage()}" /></font><br>
	</c:if>
	<c:forEach var="album" items="${albumList}">
            <div class="albumSet">
                <span class="albumInfo">
                    <img src="<c:url value='/images/folder.png' />" alt="folder1">
                    <div class="text">
                        <p style="font-size: 20px;">${album.albumName}</p>
                        <p>생성자: ${album.creatorName}</p>
                    </div>
                    <button class="requestbtn">참가하기</button>
                </span>
                <hr>
            </div>
 </c:forEach>
 </div>
	</form>  
 	</body>
</html>