<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qmain.jsp</title>
<script type="text/javascript">
	function withDrawal(){
		var bool = confirm("are you sure about that?");
		if(bool){
			location.href="qdrawal.do?userid=${qloginUser.userid}";
		}
	}
</script>
</head>
<body>
	<table>
		<tr>
			<td>Hello ${qloginUser.userid }(${qloginUser.name }) login ${message }</td>
		</tr>
		<tr>
			<td>email : ${qloginUser.email }</td>
		</tr>
		<tr>
			<td>phone : ${qloginUser.phone}</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="logout" onClick="location.href='qlogout.do'">
				<input type="button" value="update id" onClick="location.href='qupdate.do?userid=${qloginUser.userid}'">
				<input type="button" value="withdrawal" onClick="withDrawal();">
			</td>
		</tr>
	</table>
	<c:if test="${qloginUser.admin == 1 }">
		<table align="center" width="800" bgcolor="black" cellspacing="1">
			<tr bgcolor="white">
				<th>id</th>
				<th>name</th>
				<th>email</th>
				<th>phone</th>
				<th>grade</th>
				<th>change grade</th>
			</tr>
			<c:forEach items="${qmemberList }" var="qmem">
				<tr bgcolor="white" align="center">
					<td>${qmem.userid }</td>
					<td>${qmem.name}</td>
					<td>${qmem.email }</td>
					<td>${qmem.phone }</td>
					<td>
						<c:choose>
							<c:when test="${qmem.admin==0 }">일반사용자</c:when>
							<c:otherwise>관리자</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:if test="${qmem.userid !=qloginUser.userid }">
							<c:if test="${qmem.admin==0 }">
								<input type="button" value="change to admin" onClick="location.href='qeditadmin.do?userid=${qmem.userid}'">
							</c:if>
							<c:if test="${qmem.admin==1 }">
								<input type="button" value="change to user" onClick="location.href='qeditadmin.do?userid=${qmem.userid}'">
							</c:if>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>