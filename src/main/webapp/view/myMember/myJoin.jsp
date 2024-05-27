<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
    document.getElementById('nextBtn').disabled = true;

    function checkConsent() {
      const consent1 = document.querySelector('input[name="check1"]:checked');
      const consent2 = document.querySelector('input[name="check2"]:checked');
      const consent3 = document.querySelector('input[name="check3"]:checked');

      if (consent1 && consent2 && consent3) {
        document.getElementById('nextBtn').disabled = false;
      } else {
        document.getElementById('nextBtn').disabled = true;
      }
    }

    document.querySelectorAll('input[type="radio"]').forEach(item => {
      item.addEventListener('change', checkConsent);
    });
  </script>

<style>
	#container {
		max-width: 800px;
		margin: 0 auto;
		background-color: #fff;
		border-radius: 8px;
		padding: 30px;
		box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	}
	
	.list {
		font-size: 18px;
		color: #333;
		margin-bottom: 10px;
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
</style>
<script>

function ttt() {
	if (document.querySelector("#check12").checked) {
        document.querySelector("#check12").focus();
        alert("동의 해주세용");
        return;
    }
    if (document.querySelector("#check22").checked) {
        document.querySelector("#check22").focus();
        alert("동의 해주세용");
        return;
    }
    if (document.querySelector("#check32").checked) {
        document.querySelector("#check32").focus();
        alert("동의 해주세용");
        return;
    }
    if (document.querySelector("#check11").checked && document.querySelector("#check21").checked && document.querySelector("#check31").checked) {
        location.href = "myJoin2";
    }
	
}
</script>
</head>
<body>
	<div id="container">
		<h3 class="list">개인정보 수집/이용동의</h3>
		<div id="informationConsent">
			<h3>제1조 (약관목적)</h3>
			<p>본 약관은 회사와 회원의 권리, 의무 및 책임사항을 규정함을 목적으로 합니다.</p>

			<h3>제2조 (정의)</h3>
			<p>1. "회사"라 함은 회원가입약관을 동의한 자에게 회사가 제공하는 서비스를 의미합니다.</p>
			<p>2. "회원"이라 함은 회사와 서비스 이용계약을 체결한 자로서, 회사가 제공하는 서비스를 이용할 수 있는 자를
				말합니다.</p>

			<h3>제3조 (약관의 효력 및 변경)</h3>
			<p>1. 회사는 필요한 경우 관련 법령을 위배하지 않는 범위 내에서 약관을 개정할 수 있습니다.</p>
			<p>2. 회사는 약관을 개정할 경우 변경된 약관을 회원에게 통지하고, 회원이 변경된 약관에 동의할 수 있는 기회를
				제공합니다.</p>
		</div>
		<label><input type="radio" name="check1" value="동의"  id="check11">동의</label>
		<label><input type="radio" name="check1" value="비동의" id="check12"
			checked="checked">비동의</label>

		<h3 class="list">개인정보 수집/이용동의</h3>
		<div id="informationConsent">
			<h3>제1조 (약관목적)</h3>
			<p>본 약관은 회사와 회원의 권리, 의무 및 책임사항을 규정함을 목적으로 합니다.</p>

			<h3>제2조 (정의)</h3>
			<p>1. "회사"라 함은 회원가입약관을 동의한 자에게 회사가 제공하는 서비스를 의미합니다.</p>
			<p>2. "회원"이라 함은 회사와 서비스 이용계약을 체결한 자로서, 회사가 제공하는 서비스를 이용할 수 있는 자를
				말합니다.</p>

			<h3>제3조 (약관의 효력 및 변경)</h3>
			<p>1. 회사는 필요한 경우 관련 법령을 위배하지 않는 범위 내에서 약관을 개정할 수 있습니다.</p>
			<p>2. 회사는 약관을 개정할 경우 변경된 약관을 회원에게 통지하고, 회원이 변경된 약관에 동의할 수 있는 기회를
				제공합니다.</p>
		</div>
		<label><input type="radio" name="check2" value="동의" id="check21">동의</label>
		<label><input type="radio" name="check2" value="비동의" id="check22"
			checked="checked">비동의</label>

		<h3 class="list">개인정보 수집/이용동의</h3>
		<div id="informationConsent">
			<h3>제1조 (약관목적)</h3>
			<p>본 약관은 회사와 회원의 권리, 의무 및 책임사항을 규정함을 목적으로 합니다.</p>

			<h3>제2조 (정의)</h3>
			<p>1. "회사"라 함은 회원가입약관을 동의한 자에게 회사가 제공하는 서비스를 의미합니다.</p>
			<p>2. "회원"이라 함은 회사와 서비스 이용계약을 체결한 자로서, 회사가 제공하는 서비스를 이용할 수 있는 자를
				말합니다.</p>

			<h3>제3조 (약관의 효력 및 변경)</h3>
			<p>1. 회사는 필요한 경우 관련 법령을 위배하지 않는 범위 내에서 약관을 개정할 수 있습니다.</p>
			<p>2. 회사는 약관을 개정할 경우 변경된 약관을 회원에게 통지하고, 회원이 변경된 약관에 동의할 수 있는 기회를
				제공합니다.</p>
		</div>
		<label><input type="radio" name="check3" value="동의" id="check31">동의</label>
		<label><input type="radio" name="check3" value="비동의" id="check32"
			checked="checked">비동의</label>
		<div class="btn_area">
			<a href="#"><input type="button" value="다음단계"
				id="nextBtn" class="btn"  onclick="ttt()"></a>
		</div>
	</div>
</body>
</html>