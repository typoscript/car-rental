$(document).ready(() => {
	$("form").submit(e => {
		e.preventDefault();

		if (!isValidInput())
			return;
		
		e.target.submit();
	})
});

function isValidInput() {
	const password = $("#password").val();
	const newPassword = $("#newPassword").val();
	const address = $("#address").val();
	const phone = $("#phone").val();
		
	let isValid = true;

	if (!isValidPassword(password)) {
		isValid = false;
		$(".err-msg-password").show();
	} else {
		$(".err-msg-password").hide();
	}

	if (!isValidPassword(newPassword)) {
		isValid = false;
		$(".err-msg-new-password").show();
	} else {
		$(".err-msg-new-password").hide();
	}

	if (!isValidAddress(address)) {
		isValid = false;
		$(".err-msg-address").show();
	} else {
		$(".err-msg-address").hide();
	}

	if (!isValidPhone(phone)) {
		isValid = false;
		$(".err-msg-phone").show();
	} else {
		$(".err-msg-phone").hide();
	}
	
	return isValid;
}

function isValidPassword(password) {
	return 10 <= password.length && password.length <= 20;	
}

function isValidAddress(address) {
	return address.length >= 5;
}

function isValidPhone(phone) {
	return /\d{3}-\d{4}-\d{4}/.test(phone);
}