<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
<style>
    body {
      font-family: Arial, sans-serif;
      background-color: #646e77;
    }

    #loginContainer {
      max-width: 400px;
      margin: 100px auto;
      padding: 30px;
      background-color: #fff;
      border-radius: 8px;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }

    h2 {
      text-align: center;
      margin-bottom: 30px;
    }

    input[type="text"],
    input[type="password"],
    input[type="button"] {
      width: 100%;
      padding: 10px;
      margin-bottom: 20px;
      border: 1px solid #ddd;
      border-radius: 4px;
      box-sizing: border-box;
    }

    input[type="button"] {
      background-color: #fff;
      color: #000;
      border: 1px solid #aaa;
      border-radius: 4px;
      font-size: 16px;
      cursor: pointer;
    }

    input[type="button"]:hover {
      background-color: #000;
      color: #fff;
    }
</style>
</head>
<body>
	<div id="loginContainer">
		<h2>회원 로그인</h2>
		<form action="myLoginPro.jsp" method="post">
			<input type="text" name="id" placeholder="아이디" required>
			<input type="password" name="pwd" placeholder="비밀번호" required>
			<input type="submit" value="로그인">
		</form>
	</div>

	<footer class="my-3 text-center text-small">
		<p class="mb-1">&copy; 2021 YD</p>
	</footer>
	<script> window.addEventListener('load', () => { const forms = document.getElementsByClassName('validation-form'); Array.prototype.filter.call(forms, (form) => { form.addEventListener('submit', function (event) { if (form.checkValidity() === false) { event.preventDefault(); event.stopPropagation(); } form.classList.add('was-validated'); }, false); }); }, false); </script>
</body>
</html>