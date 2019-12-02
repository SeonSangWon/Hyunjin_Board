<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- c태그를 사용하기위한 태그 라이브러리 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- fmt를 사용하기위한 태그 라이브러리 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
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
<!-- bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<meta charset="UTF-8">
	<title>게시글</title>
<!-- bootstrap -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" align="center">
<table class="container">
	<c:forEach items="${view}" var="board">
		<tr>
			<td>
				${board.title}
			</td>
			<td align="right">
				조회수 : ${board.ref}
			</td>
		</tr>
		<tr>
			<td colspan="2">
				${board.name}
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<textarea class="form-control" name="comment" rows="10" cols="30"
					readonly="readonly">
					${board.comment}
				</textarea>
			</td>
		</tr>
	</c:forEach>
</table>
<br/>
</div>

<div>
<!-- 댓글 -->
<form method="POST" action="replyInsert">
	<table>
		<tr>
			<td>
				NAME 
			</td>
			<td>
				<c:forEach items="${view}" var="board">
					<input type="hidden" name="bid" value="${board.bid}" />
				</c:forEach>
				<input type="text" name="name" />
			</td>
		</tr>
		<tr>
			<td>
				PASSWORD 
			</td>
			<td>
				<input type="password" name="password" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<textarea class="form-control" name="comment" rows="5" cols="30"></textarea>
				<br/>
				<input type="submit" value="등록" />
			</td>
		</tr>
	</table>
</form>
<br/>
<br/>

<table>
	<c:forEach items="${replyView}" var="reply">
		<tr>
			<td align="right">
				작성일자 : <fmt:formatDate value="${reply.reg_date}" pattern="yyyy-MM-dd HH:mm:ss" timeZone="KST"/>
			</td>
		</tr>
		<tr>
			<td width="300px">
				${reply.comment}
			</td>
			<td>
				작성자 : ${reply.name} <br/>
				<!-- 
				<a href="<c:url value="replyUpdateGet"/>?uid=${reply.uid}&bid=${reply.bid}">
					수정 
				</a> -->
				<span onclick="msg();">
					수정
				</span>
					&nbsp;&nbsp;&nbsp;
				<a href="">
					삭제
				</a>
			</td>
		</tr>
<script>
	function msg(){
		
		var password = "";
		
		password = prompt("비밀번호 4자리를 입력해주세요.");
		location.href="<c:url value='replyUpdateGet'/>?password=" + password
				+ "&uid=${reply.uid}&bid=${reply.bid}";
	}
</script>
	</c:forEach>
</table>
</div>
</body>
</html>