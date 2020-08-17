<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form>
		<div class="form-group">
			<label for="username">username:</label> 
			<input type="text"  class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<div class="form-group">
			<label for="email">Email address:</label> 
			<input type="email"  class="form-control" placeholder="Enter email" id="email">
		</div>
		<button id="btn-save" class="btn btn-primary">회원가입</button>
	</form>
</div>
<script src="/js/user.js"></script>  <!-- 입력이면 바로statc을 찾아감. -->
<%@ include file="../layout/footer.jsp"%>



