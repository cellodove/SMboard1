<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>어깨동무 게시판</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="./css/jboard.css">
</head>
<body>
	<div id="contentsArea">
		<section id="titlename" class="qnaBoard">
			<h1>게시판 검색 결과</h1>
			<p class="allPost">
				검색 글: &nbsp; <strong></strong>개
			</p>
			<table class="boardTable">
				<caption>게시판 검색</caption>

				<thead>
					<tr>
						<th scope="col" class="bbsNumber">번호</th>
						<th scope="col" class="bbsTitle">제목</th>
						<th scope="col" class="bbsAuthor">글쓴이</th>
						<th scope="col" class="bbsDate">등록일</th>
						<th scope="col" class="bbsHit">조회수</th>
					</tr>
				</thead>
				<tbody>
					<%--검색할 내용을 확인하여 반복 출력한다.--%>

					<tr>
						<td></td>
						<td><a> </a></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>

				</tbody>

			</table>
			<div align="center">
				<table id="boardTableNe" class="boardTableNe">
					<tbody>

						<tr>
							<td colspan="4"></td>
							<td>검색된 글이 없습니다</td>
						</tr>

						<tr>
							<td colspan="5"><a>[이전]</a>&nbsp; <a>[]</a>&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<div class="btnJoinAreb">
					<button type="button" value="button" onclick="location.href='./BoardList.do'" class="btnOk">목록</button>
				</div>
			</div>
		</section>
	</div>
</body>
</html>