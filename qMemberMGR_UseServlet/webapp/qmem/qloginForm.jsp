<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qloginForm</title>
<script type="text/javascript">
	function qloginCheck(){
		if(document.frm.id.value==""){
			alert("input id");
			document.frm.id.focus();
			return false;
		}
		if(document.frm.pwd.value==""){
			alert("input password");
			document.frm.pwd.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form action="qLogin.do" method="post" name="frm">
		<table>
			<tr>
				<td>id</td>			
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>pwd</td>			
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="login" onClick="return qloginCheck();">
					<input type="reset" value="reset">
					<input type="button" value="sign up" onClick="location.href='qjoin.do'">
				</td>
			</tr>
			<tr>
				<td colspan="2">${message }</td>
			</tr>
		</table>
	</form>
</body>
</html>