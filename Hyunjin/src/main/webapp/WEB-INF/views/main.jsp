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
<!-- w3School CSS --> 
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inconsolata">
<!-- mySlides -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<!-- Google Map API -->
<script async defer
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDDEZFcrW6ZzcsGdx8cq_Lu8kIZ-xNwZvk&callback=initMap">
</script>
	<title>㈜현 진</title>
</head>
<style>
body, html {
  height: 100%;
  font-family: "Inconsolata", sans-serif;
}

.menu {
  display: none;
}

.mySlides {
	display:none;
}
</style>
<body bgcolor="#FAF7F5" onload="initialize()">

<!-- Links (sit on top) -->
<div class="w3-top">
  <div class="w3-row w3-padding w3-black">
    <div class="w3-col s3">
      <a href="#" class="w3-button w3-block w3-black">홈</a>
    </div>
    <div class="w3-col s3">
      <a href="#introduction" class="w3-button w3-block w3-black">사업소개</a>
    </div>
    <div class="w3-col s3">
      <a href="#board" class="w3-button w3-block w3-black">견적문의</a>
    </div>
    <div class="w3-col s3">
      <a href="#where" class="w3-button w3-block w3-black">회사정보</a>
    </div>
  </div>
</div>

<!-- 배너  -->
<header class="w3-display-container" style="max-width:2000px;">
<br>
	<!-- w3-content w3-section container -->
	<div class="w3-display-container">
		<img class="mySlides" src="/Hyunjin/resources/hyunjinMain1.jpg" style="width:100%; height:450px;">
		<img class="mySlides" src="/Hyunjin/resources/hyunjinMain2.jpg" style="width:100%; height:450px;">
	</div>
</header>
<script>
	var myIndex = 0;
	carousel();
	
	function carousel() {
	  	var i;
	  	var x = document.getElementsByClassName("mySlides");
	  	for (i = 0; i < x.length; i++) {
	    	x[i].style.display = "none";  
	  	}
	  	myIndex++;
	  	if (myIndex > x.length) {
	  		myIndex = 1
	  	}    
	  	x[myIndex-1].style.display = "block";  
	  	setTimeout(carousel, 4000); // Change image every 2 seconds
	}
</script>

<!-- About Container -->
<div class="w3-container" id="introduction">
  <div class="w3-content" style="max-width:1000px">
    <h2 class="w3-center w3-padding-64">
    	<span class="w3-tag w3-wide">
    		현진은 이런 일을 합니다.
    	</span>
    </h2>
    <p>The Cafe was founded in blabla by Mr. Smith in lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
    <p>In addition to our full espresso and brew bar menu, we serve fresh made-to-order breakfast and lunch sandwiches, as well as a selection of sides and salads and other good stuff.</p>
    <div class="w3-panel w3-leftbar w3-light-grey">
      <p><i>"Use products from nature for what it's worth - but never too early, nor too late." Fresh is the new sweet.</i></p>
      <p>Chef, Coffeeist and Owner: Liam Brown</p>
    </div>
    <img src="/Hyunjin/resources/2.png" style="width:100%;max-width:1000px" class="w3-margin-top" alt="사진">
    <p><strong>Opening hours:</strong> everyday from 6am to 5pm.</p>
    <p><strong>Address:</strong> 15 Adr street, 5015, NY</p>
  </div>
</div>

<!-- 게시판 -->
<%
	String Page = request.getParameter("page");
	if(Page == null)
		Page = "home";
	String targetPage = Page + ".jsp";
%>	
<div class="w3-container" id="board">
  <div class="w3-content" style="max-width:1000px">
	<jsp:include page="<%= targetPage %>" flush="false" />
  </div>
</div>

<!-- Contact/Area Container -->
<div class="w3-container w3-light-grey" id="where" style="padding-bottom:32px;">
  <div class="w3-content" style="max-width:700px">
    <h2 class="w3-center w3-padding-48">
    	<span class="w3-tag w3-wide">
    		회사정보
    	</span>
    </h2>
    <p><i class="fa fa-building contact-icon"></i>&nbsp;현진 (대표이사 : 김은희)</p>
    <p><i class="fa fa-map-marker contact-icon"></i>&nbsp;(14454) 경기도 부천시 부천로409번길 59 와이제이전자 4층</p>
	<p style="text-align: left;"><i class="fa fa-envelope contact-icon"></i> <span style="color: #34bfed;"><a style="color: #34bfed;" href="mailto:rbs0721@naver.com">rbs0721@naver.com</a></span></p>
	<p><i class="fa fa-link contact-icon"></i>&nbsp;전화번호 : 010-5392-7482
	<div id="map_view" style="width:650px; height:300px; margin-bottom:50px;"></div>
	</div>
</div>

<!-- Footer -->
<footer class="w3-center w3-padding-24 w3-large">
2019 © 현진. All rights reserved.
</footer>

<!-- Google Map Open API -->
<script>
	function initialize() { 
		//37.520510, 126.781394 와이제이전자 좌표값
		var Y_point = 37.520510; // Y 좌표
		var X_point = 126.781394; // X 좌표
		var zoomLevel = 18; // 첫 로딩시 보일 지도의 확대 레벨
		var markerTitle = "와이제이전자"; // 현재 위치 마커에 마우스를 올렸을때 나타나는 이름
		var markerMaxWidth = 300; // 마커를 클릭했을때 나타나는 말풍선의 최대 크기
		
		var contentString = '<div id="content">' +
		'<div id="siteNotice">' +
		'</div>' +
		'<h4 id="firstHeading" class="firstHeading">와이제이전자 4층</h4>' +
		'<div id="bodyContent">' +
		'<p>경기도 부천시 부천로409번길 59<br />' +
		'Tel. 010-5392-7482</p>' +
		'</div>' +
		'</div>';
	
		 var myLatlng = new google.maps.LatLng(Y_point, X_point);
		 var mapOptions = {
		 zoom: zoomLevel,
		 center: myLatlng,
		 mapTypeId: google.maps.MapTypeId.ROADMAP
		 }
		 var map = new google.maps.Map(document.getElementById('map_view'), mapOptions);
	
		 var marker = new google.maps.Marker({
		 position: myLatlng,
		 map: map,
		 title: markerTitle
		 });
	
	 var infowindow = new google.maps.InfoWindow(
	 {
	 content: contentString,
	 maxWidth: markerMaxWidth
	 }
	 );
	
	 google.maps.event.addListener(marker, 'click', function() {
	 infowindow.open(map, marker);
	 });
	}
</script>
</body>
</html>