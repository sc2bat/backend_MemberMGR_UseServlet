<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateForm</title>
<script type="text/javascript">
	function joinCheck(){
		if(document.frm.name.value.length==0){
			alert("이름은 필수 입력");
			document.frm.name.focus();
			return false;
		}
		if(document.frm.pwd.valeu.lenght == 0){
			alert("비밀번호 입력은 필수")
			document.frm.pwd.focus();
			return false;
		}
		if(document.frm.pwd.value != document.frm.pwd_check.value){
			alert("비밀번호 확인 일치 안함");
			document.frm.pwd_check.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<h2>회원정보수정</h2>
	<form action="member.do" method="post" name="frm">
		<input type="hidden" name="command" value="update">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="${loginUser.name }"></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>${loginUser.userid }<input type="hidden" name="userid" value="${loginUser.userid }"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pwd" value="${loginUser.pwd }"></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="pwd_check" size="20"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" value="${loginUser.email }"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone" value="${loginUser.phone }"></td>
			</tr>
			<tr>
				<td>등급</td>
				<td>
					<c:choose>
						<c:when test="${mdto.admin==0 }">
							<input type="radio" name="admin" value="0" checked="checked">일반회원
							<input type="radio" name="admin" value="1">관리자
						</c:when>
						<c:otherwise>
							<input type="radio" name="admin" value="0">일반회원
							<input type="radio" name="admin" value="1" checked="checked">관리자
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="확인" onClick="return joinCheck()">&nbsp;&nbsp;
					<input type="reset" value="취소">
					<input type="button" value="메인으로" onClick="location.href='member.do?command=main'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>