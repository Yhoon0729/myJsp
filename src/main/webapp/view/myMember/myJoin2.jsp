<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #646e77;
	padding: 20px;
}

#container {
	max-width: 800px;
	margin: 0 auto;
	background-color: #fff;
	border-radius: 8px;
	padding: 30px;
	box-shadow: 0 5px 10px rgba(0, 0, 0, 0.5);
}

header {
	text-align: center;
	margin-bottom: 20px;
}

h1 {
	color: #000;
}

.userInput {
	margin-bottom: 20px;
}

.list {
	font-size: 18px;
	color: #333;
	margin-bottom: 10px;
}

.box {
	display: flex;
	align-items: center;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.box input {
	flex: 1;
	padding: 10px;
	border: none;
	outline: none;
}

select {
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	margin-left: 10px;
}

.btn_area {
	text-align: center;
}

.btn {
	padding: 12px 24px;
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 4px;
	font-size: 16px;
	cursor: pointer;
}

.btn:hover {
	background-color: #0056b3;
}

.terms {
	color: #333;
	line-height: 1.5;
}

.terms h2 {
	font-size: 24px;
	margin-bottom: 20px;
}

#informationConsent {
	max-width: 600px;
	margin: 50px auto;
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	text-align: left;
	overflow-y: scroll;
	height: 300px;
}
</style>

<script>
function chkpwd(f) {
	let chk = f.pwd.value==f.pwd2.value
	if(!chk) {
		alert("비밀번호 확인이 틀렸습니다.")
		f.pwd2.focus()
		return chk;
	} else {
		return chk;
	}
}
</script>

</head>
<body>
	<div id="container">
		<form method="post" action="myJoinPro.jsp" onsubmit="return chkpwd(this)">
			<header>
				<h1>
					<p>KIC 회원가입</p>
				</h1>
			</header>
			<div class="userInput">
				<h3 class="list">아이디</h3>
				<span class="box"><input type="text" name="id" maxlength="20"
					placeholder="5 ~ 12자리" required></span>
			</div>
			<div class="userInput">
				<h3 class="list">
					비밀번호<small> 대문자, 소문자, 특수문자 사용</small> <span class="box"><input
						type="password" name="pwd" maxlength="20" placeholder="8 ~ 20자리"
						required></span>
			</div>
			<div class="userInput">
				<h3 class="list">비밀번호 확인</h3>
				<span class="box"><input type="password" maxlength="20"
					name="pwd2" required></span>
			</div>
			<div class="userInput">
				<h3 class="list">
					성명<span></span>
				</h3>
				<span class="box"><input type="text" name="name"
					maxlength="20" required></span>
			</div>
			<div class="userInput">
				<h3 class="list">
					생년월일<span></span>
				</h3>
				<span class="box"><input type="text" name="birth"
					maxlength="20" placeholder="ex)19001010" required></span>
			</div>
			<div class="userInput">
				<h3 class="list">
					전화번호("-" 제외)<span></span>
				</h3>
				<span class="box"><input type="tel" name="tel" maxlength="11"
					placeholder="01012345678" required></span>
			</div>
			<div class="userInput">
				<h3 class="list">자택주소</h3>
				<input type="text" name="address" placeholder="우편번호">
			</div>
			<div class="userInput">
				<h3 class="list">
					이메일<small>(선택사항)</small>
				</h3>
				<input type="email" name="email" maxlength="20">
			</div>
			<div class="btn_area">
				<input type="submit" value="가입하기" class="btn">
			</div>
		</form>
	</div>

</body>
</html>