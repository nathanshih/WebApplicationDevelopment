$(document).ready(function() {
	
	$(".remove").click(function() {
		$.ajax({
			url: "registration",
			data: "course=" + $(this).attr("value"),
			type: "POST"
		});
	});
	
    $("#edit").click(function(){
        parent.history.back();
        return false;
    });
    
});