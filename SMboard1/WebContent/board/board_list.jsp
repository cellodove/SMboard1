<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>어깨동무 게시판</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="./css/jboard.css">
</head>
<body>
	<div id="contentsArea">
		<section id="titlename" class="qnaBoard">
			<h1>어깨동무 게시판</h1>
			<div id="infoArea">
				<section class="search">
					<form name="search" action="./BoardSearchList.do" method="post">
						<fieldset>
							<legend> 검색 </legend>
							<label for="keyword"></label> 
							<select name="keyfield" class="b_search">
								<%--해당 항목을 기본 선택으로 지정하여 검색한다.--%>
								<option value="all" selected="selected">전체 검색</option>
								<option value="subject">제목</option>
								<option value="name">글쓴이</option>
								<option value="content">내용</option>
							</select> 
							<input type="search" id="keyword" name="keyword" required="required" placeholder="검색어 입력">
							<button type="submit">검색</button>
						</fieldset>
					</form>
				</section>
			</div>
			<p class="allPost">
				<%--전체 글의 개수를 호출한다.--%>
				전체 글: &nbsp; <strong></strong>개
			</p>
			<table class="boardTable">
				<caption>게시판 리스트</caption>
				<%--게시글이 존재할 조건을 지정한다.--%>


				<thead>
					<tr>
						<th scope="col" class="bbsNumber">번호</th>
						<th scope="col" class="bbsTitle">제목</th>
						<th scope="col" class="bbsAuthor">글쓴이</th>
						<th scope="col" class="bbsDate">등록일</th>
						<th scope="col" class="bbsHit">조회수</th>
					</tr>
				</thead>
				<%--해당 페이지에 저장된 글을 호출한다. --%>

				<tbody>
					<tr>
						<%--글 번호를 표시한다.--%>
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
						<%--등록된 글이 없을 때 출력한다.--%>

						<tr>
							<td colspan="4"></td>
							<td>등록된 글이 없습니다.</td>
						</tr>

						<tr>
							<td colspan="5">
								<%--페이지 이동 처리를 한다.--%>> <a>[이전]</a>&nbsp; <a> </a>&nbsp; <a>[다음]</a>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="btnJoinAreb">
					<button type="button" value="button" onclick="location.href='./BoardWrite.do'" class="btnOk">글쓰기</button>
				</div>
			</div>
		</section>
	</div>
</body>
</html>