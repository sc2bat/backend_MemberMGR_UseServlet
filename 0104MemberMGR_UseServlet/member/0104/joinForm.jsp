<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>joinForm</title>
<script type="text/javascript">
	function idCheck(){
		if(document.frm.userid.value.length == 0){
			alert("중복 조회할 아이디 입력해주세요");
			document.frm.userid.focus();
			return false;
		}
		var inputid = document.frm.userid.value;
		var opt = "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=500, height=200";
		window.open("idcheck.do?userid=" + inputid, "idcheck", opt);
		// 팝업창 오픈 메뉴의 idcheck.do 는 서블릿이며, 서블릿에서 아이디 중복체크 한후, 결과를 가지고 포워딩되는
		// 페이지에 팝업창에 표시될 예정입니다. 서블릿 호출시 파라미터를 위와 가팅 호출되는 주소에 ? 함께 전달할 수 있습니다
	}
</script>
</head>
<body>
	<h2>회원가입</h2>
	'*' 표시 항목은 필수 입력 항목입니다
	<form action="join.do" method="post" name="frm">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" size="20">&nbsp;* </td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid" size="20">&nbsp;* 
						<input type="button" value="중복체크" onClick="idCheck();">
						<input type="hidden" name="reid" value=""></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pwd" size="20">&nbsp;* </td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="pwd_check" size="20">&nbsp;* </td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" size="20">&nbsp;* </td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone" size="20">&nbsp;* </td>
			</tr>
			<tr>
				<td>등급</td>
				<td><input type="radio" name="admin" value="0" checked="checked">일반회원&nbsp;
						<input type="radio" name="admin" value="1">관리자&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="회원 가입" onClick="return joinCheck()">
					<input type="reset" value="취소">
				</td>
			</tr>			
		</table>
	</form>
</body>
</html>