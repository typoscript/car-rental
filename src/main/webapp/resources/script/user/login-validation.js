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

	let isValid = true;

	if (!isValidId(id)) {
		isValid = false;
		$(".err-msg-id").show();
	} else {
		$(".err-msg-id").hide();
	}

	if (!isValidPassword(password)) {
		isValid = false;
		$(".err-msg-password").show();
	} else {
		$(".err-msg-password").hide();
	}

	return isValid;
}

function isValidId(id) {
	return 5 <= id.length && id.length <= 20;
}

function isValidPassword(password) {
	return 10 <= password.length && password.length <= 20;	
}