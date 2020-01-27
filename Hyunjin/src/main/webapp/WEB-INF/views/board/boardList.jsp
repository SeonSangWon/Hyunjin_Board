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
	<title>견적문의</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
			<c:if test="${empty listAll}">
				<tr>
					<td colspan="4" align="center">
						등록된 게시글이 없습니다.
					</td>
				</tr>
			</c:if>
			<c:forEach items="${listAll}" var="listAll">
				<tr>
					<td align="center">
						<!-- boardView로 넘겨줘야할 값 : page / range / bid -->
						<a href="<c:url value='boardView'/>?page=${pagination.page}&range=${pagination.page}&bid=${listAll.bid}">
							${listAll.title}
						</a>
					</td>
					<td>
						${listAll.name}
					</td>
					<td>
						<fmt:formatDate value="${listAll.reg_date}" pattern="yyyy-MM-dd" timeZone="KST" />
					</td>
					<td>
						${listAll.ref}
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<!-- Pagination -->
	<div id="paginationBox" align="center">
		<ul class="pagination">
			<c:if test="${pagination.prev}">
				<li class="page-item"><a class="page-link" href="#"
					onClick="fn_prev('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}')">Previous</a>
				</li>
			</c:if>

			<c:forEach begin="${pagination.startPage}"
				end="${pagination.endPage}" var="idx">
				<li
					class="page-item <c:out value="${pagination.page == idx ? 'active' : ''}"/> "><a
					class="page-link" href="#"
					onClick="fn_pagination('${idx}', '${pagination.range}', '${pagination.rangeSize}')">
						${idx} </a></li>
			</c:forEach>

			<c:if test="${pagination.next}">
				<li class="page-item"><a class="page-link" href="#"
					onClick="fn_next('${pagination.range}', '${pagination.range}', '${pagination.rangeSize}')">Next</a>
				</li>
			</c:if>
		</ul>
	</div>
	<!-- End Pagination -->

	<c:url var="getBoardListURL" value="adminIdentify">
		<c:param name="page" value="${pagination.page}" />
		<c:param name="range" value="${pagination.range}" />
	</c:url>

	<script>
		//이전 버튼 클릭
		function fn_prev(page, range, rangeSize) {

			var page = ((range - 2) * rangeSize) + 1;
			var range = range - 1;

			var url = "boardList";

			url = url + "?page=" + page;
			url = url + "&range=" + range;

			location.href = url;
		}

		//페이지 번호 클릭
		function fn_pagination(page, range, rangeSize, searchType, keyword) {

			var url = "boardList";

			url = url + "?page=" + page;
			url = url + "&range=" + range;

			location.href = url;
		}

		//다음 버튼 클릭
		function fn_next(page, range, rangeSize) {

			var page = parseInt((range * rangeSize)) + 1;
			var range = parseInt(range) + 1;

			var url = "boardList";

			url = url + "?page=" + page;
			url = url + "&range=" + range;

			location.href = url;
		}
	</script>	
</body>
</html>