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
    
	$("#confirm").click(function() {
		$.ajax({
			url: "confirm",
			type: "POST",
			success: function() {
				window.location = "confirmation.jsp";
			}
		});
	});
});