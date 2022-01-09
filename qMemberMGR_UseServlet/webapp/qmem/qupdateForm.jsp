<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qupdateForm</title>
</head>
<body>
	<h2>update member</h2>
	<form action="qupdate.do" method="post" name="frm">
		<table>
			<tr>
				<td>name</td>
				<td><input type="text" name="name" value="${qloginUser.name }">&nbsp;*</td>
			</tr>
			<tr>
				<td>id</td>
				<td>${qloginUser.userid }<input type="hidden" name="id" value="${qloginUser.userid}"></td>
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
				<td><input type="text" name="email" value="${qloginUser.email }"></td>
			</tr>
			<tr>
				<td>phone</td>
				<td><input type="text" name="phone" value="${qloginUser.phone }"></td>
			</tr>
			<tr>
				<td>admin grade</td>
				<td>
					<c:choose>
						<c:when test="${qloginUser.admin == 0 }">
							<input type="radio" name="admin" value="0" checked="checked">일반사용자
							<input type="radio" name="admin" value="1">관리자
						</c:when>
						<c:otherwise>
							<input type="radio" name="admin" value="0">일반사용자
							<input type="radio" name="admin" value="1" checked="checked">관리자
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="confirm" onClick="return qjoinCheck();">
					<input type="reset" value="reset">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>