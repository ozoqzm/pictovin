<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
   href="<%=request.getContextPath()%>/css/createAlbum.css">

<title>앨범 생성 페이지</title>
<script>
   function albumCreate() {
      if (form.name.value == "") {
         alert("이름을 입력하십시오.");
         form.name.focus();
         return false;
      }
      if (form.desc.value == "") {
         alert("설명을 입력하십시오.");
         form.desc.focus();
         return false;
      }
      form.submit();
   }
</script>
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
         href="<c:url value='#'/>"><span>Profile</span></a> <span><a
            href="<c:url value='/user/alarm'/>"><img
               src="<c:url value='/images/alarm.png' />"></a></span>
      </span>
   </nav>
   <hr id="line">
   <div id="box1">
      <div id="albumname"></div>
   </div>
   <!-- 바탕 -->
   <div id="whiteBox">
      <form name="form" method="POST"
         action="<c:url value='/album/createForm' />">
         <div class="rectangle" id="r1"
            style="margin-top: 85px; margin-left: 205px;"></div>
         <div class="albumName">앨범명</div>
         <div class="line1"></div>

         <input type="text" style="margin-left: 359px; margin-top: 100px;"
            name="albumName"
            <c:if test="${creationFailed}">value="${album.name}"</c:if>>
         
         <div class="rectangle" id="r2"
            style="margin-left: 205px; margin-top: 144px;"></div>
         <div class="share">공 유</div>
         <div style="margin-left:360px; margin-top:155px">
          <label>
    <input type="radio" name="isShared" value="True" checked> 공유
  </label>
  <label>
    <input type="radio" name="isShared" value="False"> 비공유
  </label>
         </div>

         <div style="margin-left:400px; margin-top:100px">         
         <input type="submit" value="생성하기">

         </div>

      </form>
   </div>

</body>
</html>