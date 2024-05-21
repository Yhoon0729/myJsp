<%@page import="myMember.myMember"%>
<%@page import="myMember.myMemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f0f0f0;
}

.container {
    margin-top: 20px;
}

.input-form-backgroud {
    background-color: #fff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.input-form {
    max-width: 600px;
    margin: 0 auto;
}

.table {
    width: 100%;
    border-collapse: collapse;
}

.table th, .table td {
    padding: 10px;
    border: 1px solid #ddd;
}

.table th {
    background-color: #f2f2f2;
    text-align: left;
}

.btn {
    display: inline-block;
    padding: 8px 16px;
    text-align: center;
    text-decoration: none;
    color: #fff;
    background-color: #007bff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.btn-primary {
    background-color: #007bff;
}

.btn-primary:hover {
    background-color: #0056b3;
}
</style>
</head>
<body>
	<%
	String id = (String) session.getAttribute("id");
	myMemberDAO dao = new myMemberDAO();
	myMember mem = dao.getMember(id);
	%>

	<div class="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<h4 class="mb-3">회원정보</h4>
				<table class="table">
					<tr>
						<th>name</th>
						<th>value</th>
					</tr>

					<tr>
						<td>id</td>
						<td><%=mem.getId()%></td>
					</tr>

					<tr>
						<td>name</td>
						<td><%=mem.getName()%></td>
					</tr>

					<tr>
						<td>birth</td>
						<td><%=mem.getBirth()%></td>
					</tr>

					<tr>
						<td>address</td>
						<td><%=mem.getAddress()%></td>
					</tr>

					<tr>
						<td>tel</td>
						<td><%=mem.getTel()%></td>
					</tr>

					<tr>
						<td>email</td>
						<td><%=mem.getEmail()%></td>
					</tr>

					<tr>
						<td colspan="2"><a class="btn btn-primary"
							href="../ch08_kicmember/memberUpdateForm.jsp"> 회원정보수정</a> <a
							class="btn btn-primary"
							href="../ch08_kicmember/memberDeleteForm.jsp"> 회원탈퇴</a> <a
							class="btn btn-primary"
							href="../ch08_kicmember/memberPassForm.jsp"> 비밀번호수정</a></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>