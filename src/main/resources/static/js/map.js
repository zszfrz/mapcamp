var map;
var marker;
var center;
var data;

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
		$.getJSON(res, function(resData){
			data = resData;

			centerCulc();
		});
	}
}

function initMap(mapbox) {
	map = new google.maps.Map(mapbox, {
		center: center,
		zoom: 14
	});  

	marker = new google.maps.Marker({ // マーカーの追加
		position: center, // マーカーを立てる位置を指定
		map: map // マーカーを立てる地図を指定
	});
}

function centerCulc(){
	var centerLat;
	var centerLon;
	var minLat = data[0].lat;
	var minLon = data[0].lon;
	var maxLat = data[0].lat;
	var maxLon = data[0].lon;

	for(var i in data){
		if(data[i].lat > maxLat){
			maxLat = data[i].lat;
		}
		if(data[i].lat < minLat){
			minLat = data[i].lat;
		}
		if(data[i].lon > maxLon){
			maxLon = data[i].lon;
		}
		if(data[i].lon < minLon){
			minLon = data[i].lon;
		}               
	}

	centerLat = (maxLat - minLat)/2 + minLat
	centerLon = (maxLon - minLon)/2 + minLon

	center = { lat: centerLat, lng: centerLon}
}

