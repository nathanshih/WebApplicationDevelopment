$(document).ready(function() {
	
	$(".remove").click(function() {
		$.ajax({
			url: "remove",
			data: "course=" + $(this).attr("value"),
			type: "POST",
			success: function() {
				window.location.href = window.location.href;
			}
		});
	});
	
    $(".edit").click(function(){
    	parent.history.back();
    	return false;
    });
	
	$("#checkout").click(function() {
		$.ajax({
			success: function() {
				window.location = "checkout.jsp";
			}
		});
	});
    
	/**
	 * Confirm registration if credit card information is valid.
	 */
	$("#confirm").click(function() {
		/**
		 * Validation here checks for:
		 * 1. Credit card entered passes the Luhn algorithm test
		 * 2. Security code is at least 3 characters
		 * 3. Name on card is supplied
		 * 4. Expiration date is a future date
		 */
		if (creditcardjs.isValid()) {
			$.ajax({
				url: "confirm",
				type: "POST",
				success: function() {
					window.location = "confirmation.jsp";
				}
			});
		} else {
			alert("Please enter a valid credit card information.");
		}
	});
	
});