 let index = {
	 init: function(){
		 $("#btn-save").on("click",()=>{
			this.save();
		 });
	 },
	 save: function(){
		//alert("회원가입입니다.");
		let data = {
			title:$("#title").val(),
			content:$("#content").val()
		}
		$.ajax({
			type:"POST",
			url:"/api/board",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8"//body타입이 어떤 mime타입인
			// dataType:"json"
		}).done(function(resp){
			alert("글작성이 완료 되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	 }


 }

 index.init();