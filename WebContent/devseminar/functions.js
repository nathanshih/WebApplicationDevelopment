$(document).ready(function() {
	
	$(".remove").click(function() {
		$.ajax({
			url: "remove",
			data: "course=" + $(this).attr("value"),
			type: "POST",
			success: function() {
				window.location.reload(false);
			}
		});
	});
	
    $(".edit").click(function(){
        parent.history.back();
        return false;
    });
    
});