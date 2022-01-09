<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qjoinForm</title>
<script type="text/javascript">
	function qidCheck(){
		if(document.frm.id.value.length== 0){
			alert("input duplicate id")
			document.frm.userid.focus();
			return false;
		}
		var inputid = document.frm.id.value;
		var opt="toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=500, height=300";
		window.open("qidcheck.do?userid=" + inputid, "qidcheck", opt);
	}
	
	function qjoinCheck(){
		if(document.frm.name.value==""){
			alert("name null");
			document.frm.name.focus();
			return false;
		}else if(document.frm.id.value.length==0){
			alert("id null")
			document.frm.id.focus();
			return false;
		}else if(document.frm.id.value != document.frm.reid.value){
			alert("id duplicate check");
			document.frm.id.focus();
			return false;
		}else if(document.frm.pwd.value.length==0){
			alert("pwd null");
			document.frm.pwd.focus();
			return false;
		}else if(document.frm.pwd.value != document.frm.pwd_re.value){
			alert("pwd != pwd_re");
			document.frm.pwd_re.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<!-- userid name pwd email phone admin -->
	<h2>sign up</h2>
	<form action="qjoin.do" method="post" name="frm">
		<table>
			<tr>
				<td>name</td>
				<td><input type="text" name="name" size="20">&nbsp;*</td>
			</tr>			
			<tr>
				<td>id</td>
				<td><input type="text" name="id" size="20">&nbsp;*
						<input type="button" value="duplicate check" onClick="qidCheck();">
						<input type="hidden" name="reid" value=""> </td>
			</tr>			
			<tr>
				<td>pwd</td>
				<td><input type="password" name="pwd" size="20">&nbsp;*</td>
			</tr>			
			<tr>
				<td>pwd_re</td>
				<td><input type="password" name="pwd_re" size="20">&nbsp;*</td>
			</tr>			
			<tr>
				<td>email</td>
				<td><input type="text" name="email" size="20"></td>
			</tr>			
			<tr>
				<td>phone</td>
				<td><input type="text" name="phone" size="20"></td>
			</tr>			
			<tr>
				<td>admin grade</td>
				<td><input type="radio" name="admin" value="0" checked="checked">일반사용자&nbsp;
						<input type="radio" name="admin" value="1">관리자&nbsp;</td>
			</tr>	
			<tr>
				<td>
					<input type="submit" value="sign up" onClick="return qjoinCheck()">
					<input type="reset" value="reset">				
				</td>
			</tr>
		</table>
	</form>
</body>
</html>