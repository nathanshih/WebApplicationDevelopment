$(document).ready(function() {
	
	$(".remove").click(function() {
		$.ajax({
			url: "registration",
			data: "course=" + $(this).attr("value"),
			type: "POST"
		});
	});
	
});

/**
 * Go back to the previous window.
 */
function goBack() {
    window.history.back();
}