$(document).ready(() => {
	console.log("reverstaion");
	
	const URL = "/CarGetAllAction";
	
	fetch(URL, {
		method: "GET",
	}).then(response => response.json())
	.then(data => console.log(data))
	.catch(e => console.log(e));
})