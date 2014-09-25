/**
 * Go back to the previous window.
 */
function goBack() {
    window.history.back()
}

/**
 * This function validates the user input. Returns false is any validation fails.
 * @returns True if validation passed. False if validation failed.
 */
function validateForm() {
	// check that at least one course is selected
	var courses = document.forms["info"]["courses"].value;
	if (courses.length < 1) {
		alert("At least one course is required.")
		return false;
	}
	
	// check that an employment status is selected
	var status = document.forms["info"]["employmentStatus"].value;
	if (status == null || status == "") {
		alert("An employment status is required.")
		return false;
	}
}