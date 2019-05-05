window.onload = function(){
	for(var i = 1; i != 10; i++){
		document.getElementById("btn_" + i).onclick = function() {insertNumber(this)};
 
	}
	document.getElementById("btn_div").onclick = function() {insertNumber(this)};
	document.getElementById("btn_prod").onclick = function() {insertNumber(this)};
	document.getElementById("btn_min").onclick = function() {insertNumber(this)};
	document.getElementById("btn_plus").onclick = function() {insertNumber(this)};
	document.getElementById("btn_clear").onclick = function() {clear()};
	document.getElementById("btn_eq").onclick = function() {result()};
};

function insertNumber(button){
	var value = button.innerHTML;
	var result = document.getElementById("display").innerHTML;
	if(result == "0"){
		if(value != "+" &&value != "-"&&value != "*"&&value != "/"){
		 document.getElementById("display").innerHTML = value;
		}else{
			alert("calculation can't start with a operator");
		}
	}else{
		 document.getElementById("display").innerHTML = result + value;
	}
}
function clear(){
	document.getElementById("display").innerHTML = "0";
}
function result(){

	var result = document.getElementById("display").innerHTML;
	document.getElementById("display").innerHTML = eval(result);
}