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

function createCarInfoContainer(car) {
	const container = document.createElement("div");
	
	const brand = document.createElement("p");
	const name = document.createElement("p");
	const type = document.createElement("p");
	const fuelType = document.createElement("p");
	const year = document.createElement("p");
	const fee = document.createElement("p");
	const mileage = document.createElement("p");
	
	$(brand).text(car.brand);
	$(name).text(car.name);
	$(type).text(car.type);
	$(fuelType).text(car.fuelType);
	$(year).text(car.year);
	$(fee).text(car.fee);
	$(mileage).text(car.mileage);
	
	container.append(brand);
	container.append(name);
	container.append(type);
	container.append(fuelType);
	container.append(year);
	container.append(fee);
	container.append(mileage);

	return container;
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