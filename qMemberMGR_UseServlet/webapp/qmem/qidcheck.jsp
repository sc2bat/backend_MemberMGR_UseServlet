<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qidcheck</title>
<script type="text/javascript">
	function idok(id){
		opener.document.frm.id.value=id;
		opener.document.frm.reid.value=id;
		self.close();
	}
</script>
</head>
<body>
	<form action="qidcheck.do" method="get" name="frm">
		id : <input type="text" name="id" value="${userid }">
		<input type="submit" value="duplicate check">
		<c:if test="${result == 2 }">
			${userid } can use
			<input type="button" value="use id" onClick="idok('${userid}');">
		</c:if>
		<c:if test="${result == 1}">
			${userid } can't use
			<script type="text/javascript">opener.document.frm.id.value="";</script>
		</c:if>
	</form>
</body>
</html>