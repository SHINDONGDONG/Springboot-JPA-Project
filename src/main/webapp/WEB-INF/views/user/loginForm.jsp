<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form action="/auth/loginProc" method="post">
		<div>
		<label for="username">username:</label> 
			<input type="text"  name="username" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password"  name="password"  class="form-control" placeholder="Enter password" id="password">
		</div>
		<button id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=139427e5ebc4bbbdaf4f00c2a8696b7b&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img height="38px" alt="kakao_button_image" src="/image/kakao_login_button.png"></a>
	</form>
</div>
<!-- <script src="/js/user.js"></script> / 입력이면 바로statc을 찾아감. -->
<%@ include file="../layout/footer.jsp"%>



