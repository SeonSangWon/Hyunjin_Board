<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MovePage</title>
</head>
<body>
<!-- BoardInsert -->
<c:if test="${url eq 'insertBoard'}">
	<meta charset="UTF-8" http-equiv="refresh" content="0;url=<c:url value='/'/>">
</c:if>

<!-- BoardUpdate (url변경) -->
<c:if test="${url eq 'updateBoard'}">
	<meta charset="UTF-8" http-equiv="refresh" content="0;url=<c:url value='/'/>">
</c:if>

<!-- BoardDelete (url변경) -->
<c:if test="${url eq 'deleteBoard'}">
	<meta charset="UTF-8" http-equiv="refresh" content="0;url=<c:url value='/'/>">
</c:if>

<!-- BoardRefUpdate / home.jsp -->
<c:if test="${url eq 'boardView'}">
	<meta charset="UTF-8" http-equiv="refresh" content="0;url=<c:url value='boardView'/>?bid=${bid}">
</c:if>

<!-- replyUpdateGet / home.jsp -->
<c:if test="${url eq 'replyUpdateGet'}">
	<meta charset="UTF-8" http-equiv="refresh" content="0;url=<c:url value='boardView'/>?bid=${bid}">
</c:if>

<!-- ReplyUpdate -->
<c:if test="${url eq 'boardView'}">
</c:if>
</body>
</html>