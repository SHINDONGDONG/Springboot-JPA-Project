let index = {
	init: function () {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		/* 	 $("#btn-login").on("click",()=>{
				this.login();
			 }); */
	},
	/* login:function(){
	   let data={
		   username :$("#username").val(),
		   password :$("#password").val()
	   }
	   $.ajax({
		   type:"POST",
		   url:"/api/user/login",
		   data:JSON.stringify(data),
		   contentType:"application/json; charset=utf-8"
	   }).done(function(){
		   alert("로그인이 완료되었습니다.");
		   location.href="/";
	   }).fail(function(error){
		   alert(json.stringify(error));
	   });
	},
*/
	save: function () {
		//alert("회원가입입니다.");
		let data = {
			username: $("#username").val(), //joinForm 에서 받은 네임,패스워드,이메일을 변수에 넣는다.
			password: $("#password").val(),
			email: $("#email").val()
		}
		//console.log(data);
		//ajax처리
		//ajax호출시 dafault가 비동기 호출
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8"//body타입이 어떤 mime타입인
			// dataType:"json"
		}).done(function (resp) {
			alert("회원가입이 완료되었다");
			location.href = "/";
		}).fail(function (error) {
			alert(JSON.stringify(error));
		}); //ajax통신을 이요해 3개의 데이터를 json으로 변경하여 insert요
	},
	update: function () {
		let data = {
			id: $("#id").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		//console.log(data);
		//ajax처리
		//ajax호출시 dafault가 비동기 호출
		console.log(data);
		
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8"//body타입이 어떤 mime타입인
			// dataType:"json"
		}).done(function (resp) {
			alert("수정이 완료 되었습니다. ");
			location.href = "/";
		}).fail(function (error) {
			alert(JSON.stringify(error));
		}); //ajax통신을 이요해 3개의 데이터를 json으로 변경하여 insert요
	}


}

index.init();