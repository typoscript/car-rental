$(document).ready(() => {
	$("form").submit(e => {
		e.preventDefault();

		if (!isValidInput())
			return;
		
		e.target.submit();
	})
});

function isValidInput() {
	const title = $("#title").val();
	const content = $("#content").val();
		
	let isValid = true;

	if (!title || title.length === 0) {
		isValid = false;
		$(".err-msg-title").show();
	} else {
		$(".err-msg-title").hide();
	}

	if (!content || content.length === 0) {
		isValid = false;
		$(".err-msg-content").show();
	} else {
		$(".err-msg-content").hide();
	}
	
	return isValid;
}