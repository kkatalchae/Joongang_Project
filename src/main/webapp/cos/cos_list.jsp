<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<table border=1 width=700 align=center>
	<caption>코스목록</caption>
	<tr>
		<th>등산코스명</th>
		<th>난이도</th>
		<th>소요시간</th>
		<th>길찾기</th>
	</tr>

	<c:forEach var="cos" items="${coslist}">
		<tr>
			<td>
				<a href="/GetCosInfo.do?cosName=${cos.cosName}">${cos.cosName}</a>
			</td>
			<td>${cos.cosDifficulty}</td>
			<td>${cos.cosTakeTime}</td>
			<td><a href="${cos.cosLink}">길찾기</a></td>
		</tr>
	</c:forEach>


</table>
<br>
<br>

<!-- 페이지 처리 -->
<center>
	<c:if test="${listcount > 0}">

		<!-- 1페이지로 이동 -->
		<a href="./CosListAction.do?page=1" style="text-decoration: none">
			<< </a>

		<!-- 이전 블럭으로 이동 -->
		<c:if test="${startPage > 10 }">
			<a href="./BoardListAction.do?page=${startPage-10}">[이전]</a>
		</c:if>

		<!-- 각 블럭에 10개의 페이지 출력 -->
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<c:if test="${i == page}">
				<!-- 현재 페이지 -->
		[${i}]
	</c:if>
			<c:if test="${i != page}">
				<!-- 현재 페이지가 아닌 경우 -->
				<a href="./BoardListAction.do?page=${i}">[${i}]</a>
			</c:if>
		</c:forEach>

		<!-- 다음 블럭으로 이동 -->
		<c:if test="${endPage < pageCount}">
			<a href="./BoardListAction.do?page=${startPage+10}">[다음]</a>
		</c:if>

		<!-- 마지막 페이지로 이동 -->
		<a href="./BoardListAction.do?page=${pageCount}"
			style="text-decoration: none"> >> </a>

	</c:if>

</center>



