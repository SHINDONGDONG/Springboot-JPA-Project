let index = {
	init: function () {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		$("#btn-reply-save").on("click", () => {
			this.replySave();
		});
	},
	save: function () {
		//alert("회원가입입니다.");
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8"//body타입이 어떤 mime타입인
			// dataType:"json"
		}).done(function (resp) {
			alert("글작성이 완료 되었습니다.");
			location.href = "/";
		}).fail(function (error) {
			alert(JSON.stringify(error));
		});
	},
	replySave: function () {
		let data = {
			content: $("#reply-content").val()
		}
		let boardId = $("#boardId").val();
		
		$.ajax({
			type: "POST",
			url: `/api/board/${boardId}/reply`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8"//body타입이 어떤 mime타입인
			// dataType:"json"
		}).done(function (resp) {
			alert("댓글작성이 완료 되었습니다.");
			location.href = `/board/${boardId}`;
		}).fail(function (error) {
			alert(JSON.stringify(error));
		});
	},
	 	update: function () {
		let id = $("#id").val();
		let data = {
			title :$("#title").val(),
			content : $("#content").val()
		}
		console.log(id);
		console.log(data);
		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function (resp) {
			alert("글 수정이 완료 되었습니다.");
			location.href = "/";
		}).fail(function (error) {
			alert(JSON.stringify(error));
		});
	},
		deleteById: function () {
		let id = $("#id").val();

		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
		}).done(function (resp) {
			alert("삭제 완료 되었습니다.");
			location.href = "/";
		}).fail(function (error) {
			alert(JSON.stringify(error));
		});
	}
}

index.init();