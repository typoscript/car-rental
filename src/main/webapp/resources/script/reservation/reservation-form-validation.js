
$(document).ready(() => {
	$("form").submit(e => {
		e.preventDefault();

		if (!isValidInput())
			return;
		
		e.target.submit();
	})
});

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
	
	if(Date.parse(startDate) > Date.parse(endDate)) {
		isValid = false;
		$(".err-msg-reservationDate-invalid").show();
	} else {
		$(".err-msg-reservationDate-invalid").hide();
	}
	
	return isValid;
}