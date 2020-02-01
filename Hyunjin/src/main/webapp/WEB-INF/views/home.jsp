<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- c태그를 사용하기위한 태그 라이브러리 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- fmt를 사용하기위한 태그 라이브러리 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<html>
<head>
<!-- Favicon -->
<link rel="apple-touch-icon" sizes="120x120" href="/Hyunjin/resources/icon/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="/Hyunjin/resources/icon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="/Hyunjin/resources/icon/favicon-16x16.png">
<link rel="manifest" href="/Hyunjin/resources/icon/site.webmanifest">
<link rel="mask-icon" href="/Hyunjin/resources/icon/safari-pinned-tab.svg" color="#5bbad5">
<meta name="msapplication-TileColor" content="#da532c">
<meta name="theme-color" content="#ffffff">
<!-- End Favicon -->
<!-- bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<!-- Font -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Permanent+Marker">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nanum+Gothic">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Jua">
	<title>Home</title>
</head>
<style>
body,a {
	font-family: 'Nanum Gothic', sans-serif;
}
td {
	text-align: center;
}

.board-first {
	background-color: #F0F0F0;
    font-weight: bold;
    text-align: center;
}

    #link_bar a:link { color:#000000;}
    #link_bar a:visited { color:#000000;}
    #link_bar a:hover { color:000000; }
    #link_bar a { text-decoration:none; }
</style>
<body>

<div align="center" id="link_bar">
	<div align="right">
		<p>
			<a href="<c:url value='boardList'/>">
			<button type="button" class="btn btn-outline-info">게시판</button>
			</a>
		</p>
	</div>
<table class="table">
	<tr>
		<td class="board-first" width="45%">
			제목
		</td>
		<td class="board-first">
			글쓴이
		</td>
		<td class="board-first">
			날짜
		</td>
		<td class="board-first">
			조회수
		</td>
	</tr>
	<c:if test="${empty list}">
	<tr>
		<td colspan="4" align="center">
			게시글이 존재하지 않습니다.
		</td>
	</tr>
	</c:if>
	<c:forEach items="${list}" var="list">
	<tr>
		<td>
			<a href="<c:url value='boardView'/>?bid=${list.bid}">
				${list.title}
			</a>
		</td>
		<td>
			${list.name}
		</td>
		<td>
			<fmt:formatDate value="${list.reg_date}" pattern="yyyy-MM-dd" timeZone="KST" />
		</td>
		<td>
			${list.ref}
		</td>
	</tr>
	</c:forEach>
</table>
</div>

</body>
</html>
