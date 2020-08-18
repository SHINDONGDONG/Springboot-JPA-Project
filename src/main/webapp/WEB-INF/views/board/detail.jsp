<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">

	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<button id="btn-update" class="btn btn-warning">수정</button>
	<button id="btn-delete" class="btn btn-danger">삭제</button>
	<br />
	<br />
		<div>
			<label for="title">Title</label> 
			<h3>${board.title}</h3>
		</div>
		<div>
			<label for="content">Content</label>
			<div>${board.content}</div>
		</div>
		<hr />
</div>
<script src="/js/board.js"></script>  <!-- 입력이면 바로statc을 찾아감. -->
<%@ include file="../layout/footer.jsp"%>



