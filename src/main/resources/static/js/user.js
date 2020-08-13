 let index = {
	 init: function(){
		 $("#btn-save").on("click",()=>{
			this.save();
		 });
	 },
	 save:function(){
		//alert("회원가입입니다.");
		let data = {
			username:$("#username").val(), //joinForm 에서 받은 네임,패스워드,이메일을 변수에 넣는다.
			password:$("#password").val(),
			email:$("#email").val()
		}
		//console.log(data);
		//ajax처리
		//ajax호출시 dafault가 비동기 호출
		$.ajax({
			type:"POST",
			url:"/blog1/api/user",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8"//body타입이 어떤 mime타입인
			// dataType:"json"
		}).done(function(resp){
			alert("회원가입이 완료되었다");
			location.href="/blog1";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax통신을 이요해 3개의 데이터를 json으로 변경하여 insert요
	 }
 }

 index.init();