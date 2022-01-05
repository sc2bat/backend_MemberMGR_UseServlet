<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>idcheck</title>
<script type="text/javascript">
	function idok(){
		opener.document.frm.userid.value = "${userid}";
		opener.document.frm.reid.value = "${userid}";
		self.close();
	}
	
	// 전달인수
// 	function idok(id){
// 		opener.document.frm.userid.value = id;
// 		opener.document.frm.reid.value = id;
// 		self.close();
// 	}
</script>
</head>
<body>
	<form action="idcheck.do" method="get" name="frm">
		아이디 : <input type="text" name="userid" value="${userid }">
		<input type="submit" value="중복 체크">
	</form> <br><br>
	<c:if test="${result == 1}">
		${userid } 는 이미 사용 중인 아이디입니다
		<script type="text/javascript">
			opener.document.frm.userid.value=""; // 팝업창을 오픈한 주체 : opener
		</script>
	</c:if>
	<c:if test="${result == -1 }">
		${userid } 는 사용 가능한 아이디입니다.
		<input type="button" value="사용하기" onClick="idok();">
<%-- 		<input type="button" value="사용하기" onClick="idok('${userid}');"> --%>
	</c:if>
</body>
</html>