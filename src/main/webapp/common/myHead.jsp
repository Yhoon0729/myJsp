<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<style>
.nav {
	color: #142745;
	font-weight: 900;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-light">
		<ul class="navbar-nav">
			<a class="navbar-brand "
				href="${pageContext.request.contextPath}/myMember/myPage"> <img
				alt="Logo" src="${pageContext.request.contextPath}/image/logo.png"
				style="width: 50%">
			</a>

			<li class="nav-item"><a class="nav-link ${nav eq 'myPage'?'nav': ' '}" 
			href="${pageContext.request.contextPath}/myMember/myPage">홈페이지</a></li>

			<c:if test="${sessionScope.id==null}">
				<li class="nav-item"><a class="nav-link ${nav eq 'myJoin'?'nav':' '}"
					href="${pageContext.request.contextPath}/myMember/myJoin">회원가입</a></li>
				<li class="nav-item"><a class="nav-link ${nav eq 'myLogin'?'nav': ' '}" 
				href="${pageContext.request.contextPath}/myMember/myLogin">로그인</a></li>
			</c:if>

			<c:if test="${sessionScope.id!=null}">
				<li class="nav-item"><a class="nav-link ${nav eq 'myMemberInfo'?'nav': ' '}"
					href="${pageContext.request.contextPath}/myMember/myMemberInfo">
						회원정보[<%=session.getAttribute("id")%>]
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/myMember/myLogout">로그아웃</a></li>
			</c:if>

			<c:if test="${sessionScope.id eq 'admin'}">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/myMember/myMemberList">회원리스트</a></li>
			</c:if>

			<li class="nav-item"><a class="nav-link ${boardid eq '1'?'nav': ' '}"
				href="${pageContext.request.contextPath}/myBoard/boardList?boardid=1">공지사항</a></li>
			<li class="nav-item"><a class="nav-link ${boardid eq '2'?'nav': ' '}"
				href="${pageContext.request.contextPath}/myBoard/boardList?boardid=2">자유게시판</a></li>
			<li class="nav-item"><a class="nav-link ${boardid eq '3'?'nav': ' '}"
				href="${pageContext.request.contextPath}/myBoard/boardList?boardid=3">Q&A</a></li>
		</ul>
	</nav>
	<br>
</body>
</html>