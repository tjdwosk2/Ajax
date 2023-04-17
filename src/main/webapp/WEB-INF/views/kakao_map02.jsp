<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=97abe4b711a5b7e051d1dcbcf6787f7b"></script>
<script type="text/javascript">
	navigator.geolocation.getCurrentPosition(function(position) {
		var lat = position.coords.latitude ;
		var lon = position.coords.longitude ;
		
		document.getElementById("lat").innerHTML = lat;
		document.getElementById("lon").innerHTML = lon;
		
		geo_map(lat, lon);
	});
</script>
</head>
<body>
	<h1>카카오 맵 연습 02(내 위치 표시)</h1>
	
	위도 : <span id="lat"></span> , 경도 : <span id="lon"></span>
	<!-- 지도를 표시할 div 입니다 -->
	<div id="map" style="width: 50%; height: 350px;"></div>

	<script>
		function geo_map(lat, lon) {
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
				center : new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표
				level : 3	// 지도의 확대 레벨
			};
	
			// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
			var map = new kakao.maps.Map(mapContainer, mapOption);
		}
	</script>
</body>
</html>