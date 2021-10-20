<%--
  Created by IntelliJ IDEA.
  User: byeon
  Date: 2021-10-13
  Time: 오후 4:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
    <title>등산코스 페이지</title>

</head>
<body>
<table border="1">
    <caption>${cosInfo.cosName}</caption>
    <tr>
        <td colspan="2">
            <div id="map" style="width: 500px; height: 400px;"></div>
            <script
                    type="text/javascript"
                    src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4f2288fb9ad2f5f8a631f9dd4490a2ff">
            </script>
            <script>
                var container = document.getElementById('map');
                var options = {
                    center: new kakao.maps.LatLng(${cosInfo.cosLatitude}, ${cosInfo.cosLongitude}),
                    level: 3
                };



                var map = new kakao.maps.Map(container, options);

                // 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다
                var positions = [];


                positions.push( {
                    content: '<div>헬기 착륙장</div>',
                    latlng: new kakao.maps.LatLng(33.450705, 126.570677)
                })


                for (var i = 0; i < positions.length; i++) {
                    // 마커를 생성합니다
                    var marker = new kakao.maps.Marker({
                        map: map, // 마커를 표시할 지도
                        position: positions[i].latlng // 마커의 위치
                    });

                    // 마커에 표시할 인포윈도우를 생성합니다
                    var infowindow = new kakao.maps.InfoWindow({
                        content: positions[i].content // 인포윈도우에 표시할 내용
                    });

                    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
                    // 이벤트 리스너로는 클로저를 만들어 등록합니다
                    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
                    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
                    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
                }

                // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
                function makeOverListener(map, marker, infowindow) {
                    return function () {
                        infowindow.open(map, marker);
                    };
                }

                // 인포윈도우를 닫는 클로저를 만드는 함수입니다
                function makeOutListener(infowindow) {
                    return function () {
                        infowindow.close();
                    };
                }
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
        <td>길찾기</td>
        <td><a href="${cosInfo.cosLink}">길찾기</a></td>
    </tr>

</table>

<table>
    <caption>맛집 & 명소 목록</caption>
    <tr>
        <th>이름</th>
        <th>전화번호</th>
        <th>주소</th>
        <th>운영 시간</th>
        <th>교통편</th>
    </tr>
    <c:forEach var="foodandplace" items="${list}">
        <tr>
            <td>${foodandplace.name}</td>
            <td>${foodandplace.tel}</td>
            <td>${foodandplace.address}</td>
            <td>${foodandplace.time}</td>
            <td>${foodandplace.trans}</td>
        </tr>

    </c:forEach>
</table>
<br>
<br>

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
