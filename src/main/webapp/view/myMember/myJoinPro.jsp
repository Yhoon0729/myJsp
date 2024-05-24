<%@page import="dao.myMemberDAO"%>
<%@page import="model.myMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");
	int birth = Integer.parseInt(request.getParameter("birth"));
	String address = request.getParameter("address");
	String tel = request.getParameter("tel");
	String email = request.getParameter("email");

	myMemberDAO dao = new myMemberDAO();
	myMember my = new myMember(); // DTO bean
	my.setId(id);
	my.setPwd(pwd);
	my.setName(name);
	my.setBirth(birth);
	my.setAddress(address);
	my.setTel(tel);
	my.setEmail(email);

	int num = dao.insertMember(my);

	String msg = "";
	String url = "";

	if (num > 0) {
		msg = name + "님의 회원가입이 완료되었습니다.";
		url = "myLogin.jsp";
	} else {
		msg = "회원가입이 실패 하였습니다.";
		url = "myJoin.jsp";
	}
	%>
	
	<script>
		alert("<%=msg %>");
		location.href="<%=url %>";
	</script>
</body>
</html>