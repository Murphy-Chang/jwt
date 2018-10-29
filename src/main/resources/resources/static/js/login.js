$(function(){
	
});

function login() {
	var formData = new FormData($("form")[0]);
	$.ajax({
		url: '/web/login/login?rdm='+Math.random(),
		data: formData,
		dataType: 'JSON',
		cache: false,
		type: 'POST',
		processData: false,
		contentType: false,
		success: function(res, status){
			console.log(res);
			let jwt = res.data.jwt;
			setLocalStorage("jwt", jwt);
			window.location.href='/web/user/allUser.html?jwt='+jwt;
		},
		error: function (xhr, error, exception) {
			let msg = xhr.responseJSON.msg;

			$("#errorMessage").text(msg);
		}
	});
}