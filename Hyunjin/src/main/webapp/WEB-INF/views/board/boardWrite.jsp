<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<meta charset="UTF-8">
	<title>견적 문의</title>
<!-- bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<style>
	input[type=text] {
	    padding:5px 15px;
	    -webkit-border-radius: 5px;
	    border-radius: 5px; 
	}
	
	input[type=password] {
	    padding:5px 15px;
	    -webkit-border-radius: 5px;
	    border-radius: 5px; 
	}
</style>
<body>
<div class="container" align="center">
<form name="frm" method="post" action="insertBoard">
<table>
	<tr>
		<td>
			NAME <br/>
			<input type="text" class="form-control" name="name" 
			placeholder = "이름을 입력해주세요." autofocus required />
		</td>
		<td>
			PASSWORD <br/>
			<input type="password" class="form-control" name="password" maxlength="4"
			placeholder = "숫자 4자리를 입력해주세요." autofocus required />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			PHONE <br/>
			<input type="text" class="form-control" name="phone" 
			placeholder = "'-'없이 입력해 주세요." autofocus required />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			TITLE <br/>
			<input type="text" class="form-control" name="title"
			placeholder = "제목을 입력해주세요." autofocus required />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			COMMENT <br/>
			<textarea class="form-control" name="comment" rows="10" cols="30"></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="작성" class="btn btn-outline-info" />
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>