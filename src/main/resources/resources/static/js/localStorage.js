var localStorage = window.localStorage;

//从lcoalStorage中取值
function getLocalStorage(name){
	return localStorage.getItem(name);
}

//往localStorage中设置值
function setLocalStorage(name, val){
	localStorage.setItem(name, val);
}

//清空lcoalStorage
function clearLocalStorage(){
	localStorage.clear();
}

//删除lcoalStorage值
function removeLocalStorage(name){
	localStorage.removeItem(name);
}
