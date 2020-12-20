//파일명 : 04JsData.js
function MyAlert(str1, str2){
	alert(str1 + str2);
}

//여기에 써도 되긴하네요 !? (교안에선 04Ajax에 적음)
function sucFunc(resData){
	alert("$.ajax()메소드 요청성공");
	$("#ajaxDisplay").html(resData);
}