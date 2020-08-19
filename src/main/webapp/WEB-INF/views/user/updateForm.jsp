<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<input type="hidden"  id="id" value="${principal.user1.id}" />
		<div class="form-group">
			<label for="username">username:</label> 
			<input type="text"  class="form-control"  value=" ${principal.user1.username}" placeholder="Enter username" id="username" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" class="form-control"  placeholder="Enter password" id="password">
		</div>
		<div class="form-group">
			<label for="email">Email address:</label> 
			<input type="email"  class="form-control" value=" ${principal.user1.email}" placeholder="Enter email" id="email">
		</div>
	</form>
		<button id="btn-update" class="btn btn-primary">수정하기</button>
</div>
<script src="/js/user.js"></script>  <!-- 입력이면 바로statc을 찾아감. -->
<%@ include file="../layout/footer.jsp"%>



