$(document).ready(() => {
	$("form").submit(e => {
		e.preventDefault();

		if (!isValidInput())
			return;
		
		e.target.submit();
	})
});

function isValidInput() {
	const id = $("#id").val();
	const password = $("#password").val();
	const name = $("#name").val();
	const address = $("#address").val();
	const phone = $("#phone").val();
		
	let isValid = true;

	if (!isValidId(id)) {
		isValid = false;
		$("#err-msg-id").show();
	} else {
		$("#err-msg-id").hide();
	}

	if (!isValidPassword(password)) {
		isValid = false;
		$("#err-msg-password").show();
	} else {
		$("#err-msg-password").hide();
	}

	if (!isValidName(name)) {
		isValid = false;
		$("#err-msg-name").show();
	} else {
		$("#err-msg-name").hide();
	}

	if (!isValidAddress(address)) {
		isValid = false;
		$("#err-msg-address").show();
	} else {
		$("#err-msg-address").hide();
	}

	if (!isValidPhone(phone)) {
		isValid = false;
		$("#err-msg-phone").show();
	} else {
		$("#err-msg-phone").hide();
	}
	
	return isValid;
}

function isValidId(id) {
	return 5 <= id.length && id.length <= 20;
}

function isValidPassword(password) {
	return 10 <= password.length && password.length <= 20;	
}

function isValidName(name) {
	return name.length > 0;
}

function isValidAddress(address) {
	return address.length >= 5;
}

function isValidPhone(phone) {
	return /\d{3}-\d{4}-\d{4}/.test(phone);
}