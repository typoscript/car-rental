$(document).ready(() => {
	console.log("reverstaion");
	
	const URL = "/CarGetAllAction";
	
	fetch(URL, {
		method: "GET",
	}).then(response => response.json())
	.then(data => generateCarList(data))
	.catch(e => console.log(e));
});

function generateCarList(data) {
	for (const car of data.cars) {
		$(".root").append(createCarCard(car));
	}
}

function createCarCard(car) {
	const cardContainer = document.createElement("div");

	const img = document.createElement("img");
	const infoContainer = document.createElement("div");
	
	$(img).attr("src", car.imgUrl);
	
	infoContainer.append(createCarInfoContainer(car));
	
	cardContainer.appendChild(img);
	cardContainer.appendChild(infoContainer);

	return cardContainer;
}