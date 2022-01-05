<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<script type="text/javascript">
	function withDrawConfirm(){
		var bool = confirm('정말 탈퇴하시겠습니까');
		if(bool){
			location.href="withdraw.do?userid=" + "${loginUser.userid}";
		}else{
		}
	}
</script>
</head>
<body>
<%-- 	<h2>${loginUser.userid }(${loginUser.name }) 님 안녕하세요</h2> --%>

	<table>
		<tr>
			<td>안녕하세요. ${loginUser.userid }(${loginUser.name }) 님 로그인하셨습니다 &nbsp;&nbsp; ${message }</td>
		</tr>
		<tr>
			<td>email : ${loginUser.email}</td>
		</tr>
		<tr>
			<td>전화번호 : ${loginUser.phone}</td>
		</tr>
		<tr>
			<td>
				<input type="button" value="로그아웃" onClick="location.href='logout.do'">
				<input type="button" value="회원정보변경" onClick="location.href='update.do?userid=${loginUser.userid}'">
				<!-- 문자데이터들 사이에 EL 문법이 들어가 조합문자데이터 구성 가능 -->
				<input type="button" value="회원 탈퇴" onClick="withDrawConfirm();">
			</td>
		</tr>
	</table>
	
	<c:if test="${loginUser.admin ==1 }">
		<table align="center" width="800" bgcolor="black" cellspacing="1">
			<tr bgcolor="white">
				<th>아이디</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>이메일</th>
				<th>등급</th>
				<th>등급변경</th>
			</tr>
			<c:forEach items="${memberList}" var="mem">
				<tr bgcolor="white" align="center">
					<td>${mem.userid }</td>
					<td>${mem.name}</td>
					<td>${mem.phone }</td>
					<td>${mem.email }</td>
					<td>
						<c:choose>
							<c:when test="${mem.admin==0 }">일반사용자</c:when>
							<c:otherwise>관리자</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:if test="${mem.userid != loginUser.userid }">
							<c:if test="${mem.admin==0 }">
								<input type="button" value="관리자로 변경" onClick="location.href='editadmin.do?userid=${mem.userid}'">
								<!-- 서블릿 이름 : EditAdminServlet -->
							</c:if>
							<c:if test="${mem.admin==1 }">
								<input type="button" value="일반회원으로 변경" onClick="location.href='editadmin.do?userid=${mem.userid}'">
							</c:if>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	
</body>
</html>