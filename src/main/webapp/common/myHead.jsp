<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-light">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link"
				href="../myMember/myPage.jsp">홈페이지</a></li>

			<%
			if (session.getAttribute("id") == null) {
			%>
			<li class="nav-item"><a class="nav-link"
				href="../myMember/myJoin.jsp">회원가입</a></li>
			<li class="nav-item"><a class="nav-link"
				href="../myMember/myLogin.jsp">로그인</a></li>
			<%
			} else {
			%>

			<li class="nav-item"><a class="nav-link"
				href="../myMember/myMemberInfo.jsp"> 회원정보[<%=session.getAttribute("id")%>]
			</a></li>
			<li class="nav-item"><a class="nav-link"
				href="../myMember/myLogout.jsp">로그아웃</a></li>
			<%
			}
			%>
			<li class="nav-item"><a class="nav-link" href="#">공지사항</a></li>
		</ul>
	</nav>
	<br>
</body>
</html>