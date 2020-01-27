<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- c태그를 사용하기위한 태그 라이브러리 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- fmt를 사용하기위한 태그 라이브러리 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
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
<!-- w3School CSS --> 
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inconsolata">
<!-- bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>댓글 수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div align="center">
		<!-- 댓글 입력 -->
		<form method="POST" action="replyUpdate">
			<c:forEach items="${ReplyOne}" var="reply">
				<input type="hidden" name="uid" value="${reply.uid}" />		<!-- 댓글번호 -->
				<input type="hidden" name="bid" value="${reply.bid}" />		<!-- 게시글번호 -->
				<input type="hidden" name="page" value="${page}" />			<!-- 페이징 -->
				<input type="hidden" name="range" value="${range}" />		<!-- 페이징 -->
				<table class="container">
					<tr>
						<td style="width: 150px;">NAME</td>
						<td>
							<input type="text" name="name" class="form-control" value="${reply.name}" />
						</td>
					</tr>
					<tr>
						<td>PASSWORD</td>
							<td>
							<input type="password" name="password" class="form-control" maxlength="4" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea class="form-control" name="comment" rows="5" cols="30">${reply.comment}</textarea>
							<br />
							<input type="submit" value="등록" class="form-control" style="width: 100px;"/>
						</td>
					</tr>
				</table>
			</c:forEach>
		</form>
	</div>
</body>
</html>