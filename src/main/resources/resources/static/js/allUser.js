
function userDetail(userId){
	var jwt = getLocalStorage("jwt");
	window.location.href='/web/user/userDetail.html?userId='+userId+'&jwt='+jwt;
}