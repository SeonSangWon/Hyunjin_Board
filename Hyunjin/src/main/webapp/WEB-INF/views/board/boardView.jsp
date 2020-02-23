<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- c태그를 사용하기위한 태그 라이브러리 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- fmt를 사용하기위한 태그 라이브러리 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- fn를 사용하기위한 태그 라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	pageContext.setAttribute("br", "<br>"); //br 태그
	pageContext.setAttribute("cn", "\n"); //Enter
%>
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
<!-- AJAX -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	var msg = <%= request.getParameter("msg") %>;

	<%-- 
	1: 댓글 등록
	2: 댓글 등록 실패
	3: 댓글 수정
	4: 댓글 수정 실패
	5: 댓글 삭제
	6: 댓글 삭제 실패
	9: 댓글 수정 시 비밀번호 유효성 검사 실패
	--%>
	if(msg == "1")
		alert("댓글을 등록했습니다.");
	else if(msg == "2")
		alert("Error!! 다시 한번 등록해주세요.");
	else if(msg == "3")
		alert("댓글을 수정했습니다.");
	else if(msg == "4")
		alert("Error!! 다시 한번 수정해주세요.");
	else if(msg == "5")
		alert("댓글을 삭제했습니다.");
	else if(msg == "6")
		alert("Error!! 다시 한번 시도해주세요.");
	else if(msg == "9")
		alert("Error!! 비밀번호가 일치하지 않습니다.");
</script>
</head>
<body>
	<div class="container" align="center">
		<!-- 게시글 내용 -->
		<c:forEach items="${view}" var="board">
			<table class="container">
				<tr>
					<td>${board.title}</td>
					<td align="right">조회수 : ${board.ref}</td>
				</tr>
				<tr>
					<td colspan="2">${board.name}</td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea class="form-control" name="comment"
							rows="10" cols="30" readonly="readonly"><c:out value="${fn:replace(board.comment, br, cn)}" escapeXml="true" /></textarea>
					</td>
				</tr>
			</table>
			<br />
		
			<!-- 댓글 입력 -->
			<form id="replyForm" method="POST" action="replyInsert">
				<table class="container">
					<tr>
						<td style="width: 150px;">NAME</td>
						<td>
							<input type="hidden" name="bid" value="${board.bid}" />
							<input type="hidden" name="page" value="<%= request.getParameter("page") %>" />
							<input type="hidden" name="range" value="<%= request.getParameter("range") %>" />
							<input type="text" name="name" class="form-control"/>
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
							<textarea class="form-control" name="comment" rows="5" cols="30"></textarea>
							<br />
							<input type="submit" value="등록" class="form-control" style="width: 100px;"/>
						</td>
					</tr>
				</table>
			</form>
		</c:forEach>
		<br />
		<br />
		
		<!-- 댓글 내용 -->
		<table class="container">
			<c:forEach items="${replyView}" var="reply">
				<tr>
					<td align="right" colspan="2">
						<hr>
						작성일자 : <fmt:formatDate value="${reply.reg_date}" pattern="yyyy-MM-dd HH:mm:ss" timeZone="KST"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						${reply.comment}						
					</td>
					<td align="right">
						작성자 : ${reply.name} <br />
						<span onclick="replyUpdate(${reply.bid}, ${reply.uid});">
							수정
						</span>
							&nbsp;&nbsp;&nbsp;
						<span onclick="replyDelete();">
							삭제
						</span>
					</td>
				</tr>
				<script>
					function replyUpdate(bid, uid) {
	
						var password = "";
						var url = "";
						var page = <%= request.getParameter("page") %>;
						var range = <%= request.getParameter("range") %>;
						
						//bid / uid / page / range / password
						password = prompt("비밀번호 4자리를 입력해주세요.");
						url = "passwordValidate?bid=" + bid + "&uid=" + uid +  
								"&page=" + page + "&range=" + range + "&password=" + password;
						
						location.href=url;
					}
					
					function replyDelete() {
						
					}
				</script>
			</c:forEach>
		</table>
		<div align="center">
			<button class="form-control" style="width: 100px;"
				onclick="location.href='boardList?page=<%= request.getParameter("page") %>&range=<%= request.getParameter("range") %>'">
				뒤로가기
			</button>
		</div>
	</div>
</body>
</html>