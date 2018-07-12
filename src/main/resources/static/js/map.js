$("#route").on("click", function() {
  getLatLon()
});

function getLatLon(){
	$.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        type: 'POST',
        dataType: 'json',
        url: '/latlon'
    })
    .done(function(res) {
        $('ul').append($(`<li>${res.text}</li>`));
    });
}


    
    
    