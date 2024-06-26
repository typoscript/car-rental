let dateRanges;

$(document).ready(async () => {
	const carId = $("#carId").val();
	setDateRanges(carId);

	$("form").submit(e => {
		e.preventDefault();

		if (!isValidInput()) {
			return;
		}
		
		e.target.submit();
	});
});

async function setDateRanges(carId) {
    await fetch(`/reservationDateRangeView?carId=${carId}`, {
        method: "GET"
    })
    .then(response => response.json())
    .then(result => {
		dateRanges = result;
    })
    .catch(err => {
		console.log(err)
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
	
	if (!payAmount || Number(payAmount) < Number(price)) {
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

	if(hasReservedDate(startDate, endDate)) {
		isValid = false;
		$(".err-msg-reservationDate-exist").show();
	} else {
		$(".err-msg-reservationDate-exist").hide();
	}
	
	return isValid;
}

function isValidDateRange(startDate, endDate) {
	return Date.parse(startDate) <= Date.parse(endDate);
}

function hasReservedDate() {
	const startDate = $("#startDate").val();
	const endDate = $("#endDate").val();
	
	for (const dateRange of dateRanges) {
		if (dateRange.startDate <= startDate && startDate <= dateRange.endDate)
			return true;	
		else if (dateRange.startDate <= endDate && endDate <= dateRange.endDate)
			return true;	
	}
	
	return false;
}