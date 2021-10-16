<%--
  Created by IntelliJ IDEA.
  User: byeon
  Date: 2021-10-13
  Time: 오후 4:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>등산코스 페이지</title>

</head>
<body>
	<table border="1">
		<caption>등산 코스명</caption>
		<tr>
			<td colspan="2">
				<div id="map" style="width: 500px; height: 400px;"></div> <script
					type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4f2288fb9ad2f5f8a631f9dd4490a2ff"></script>
				<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(${cosInfo.cosLatitude}, ${cosInfo.cosLongitude}),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
	</script>
			</td>
		</tr>
		<tr>
			<td>난이도</td>
			<td>${cosInfo.cosDifficulty}</td>
		</tr>
		<tr>
			<td>길이</td>
			<td>${cosInfo.cosLength}</td>
		</tr>
		<tr>
			<td>소요 시간</td>
			<td>${cosInfo.cosTakeTime}</td>
		</tr>
		<tr>
			<td>관련 링크</td>
			<td><a href="${cosInfo.cosLink}">${cosInfo.cosLink}</a></td>
		</tr>

	</table>

	<div>
		<div>
			<h3>댓글</h3>
		</div>
		<div>
			<div>
				<textarea placeholder="댓글을 남겨보세요"></textarea>
			</div>
			<div>
				<button>등록</button>
				<button></button>
			</div>
		</div>
	</div>

</body>
</html>
