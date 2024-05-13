$(document).ready(async () => {
	const carId = $("#carId").val();
	const dateRanges = await getReservationDateRanges(carId);

	$("form").submit(e => {
		e.preventDefault();

		if (!isValidInput())
			return;
		
		e.target.submit();
	})
});

function getReservationDateRanges(carId) {
    return fetch(`/reservationDateRangeView?carId=${carId}`, {
        method: "GET",
    })
    .then(response => response.json())
    .then(result => {
		return result;
    })
    .catch(err => {
        console.error(err);
    });
}


$("#startDate").change(() => {
	setPriceInputValue();
});

$("#endDate").change(() => {
	setPriceInputValue();
});

function setPriceInputValue() {
	const startDate = $("#startDate").val();
	const endDate = $("#endDate").val();
	
	let price = 0;
	
	if (isValidDateRange(startDate, endDate)) {
		price = getTotalPrice(startDate, endDate);
	}

	$("#price").val(price);
}

function getTotalPrice(startDate, endDate) {
	const diffTime = (new Date(endDate)).getTime() - (new Date(startDate)).getTime(); 
	const diffDays = Math.floor(diffTime / (1000 * 60 * 60 * 24)) + 1;
	const pricePerDay = parseInt($("#feePerDay").val());
	
	return diffDays * pricePerDay;
}

function isValidInput() {
	const startDate = $("#startDate").val();
	const endDate = $("#endDate").val();
	const price = $("#price").val();
	const payAmount = $("#payAmount").val();
	
	let isValid = true;
	
	if (!startDate) {
		isValid = false;
		$(".err-msg-startDate-invalid").show();
	} else {
		$(".err-msg-startDate-invalid").hide();
	}

	if (!endDate) {
		isValid = false;
		$(".err-msg-endDate-invalid").show();
	} else {
		$(".err-msg-endDate-invalid").hide();
	}

	if (!payAmount || payAmount < price) {
		isValid = false;
		$(".err-msg-payAmount-invalid").show();
	} else {
		$(".err-msg-payAmount-invalid").hide();
	}
	
	if(!isValidDateRange(startDate, endDate)) {
		isValid = false;
		$(".err-msg-reservationDate-invalid").show();
	} else {
		$(".err-msg-reservationDate-invalid").hide();
	}
	
	return isValid;
}

function isValidDateRange(startDate, endDate) {
	return Date.parse(startDate) <= Date.parse(endDate);
}