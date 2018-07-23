var map;
var marker = [];
var data = [];
var request;
var infoWindow = [];

function getLatLon(){
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	$.getJSON('/latlon', function(res){		
		localStorage.data = JSON.stringify(res);
		window.location.href = '/route';	
	});	
}

function initMap() {
	var data1 = localStorage.data;
	Array.prototype.push.call(data, JSON.parse(data1));
	
	if (!navigator.geolocation) {
		alert('現在地が取得できません');
		return false;
	}

	navigator.geolocation.getCurrentPosition(function(position) {
		
		//経由地
		var points = [];
		for(var i=0; i < data[0].length-1; i++){
			points.push({ location: new google.maps.LatLng(data[0][i].store.lat , data[0][i].store.lon) });
		}
		
		//ルート探索条件
		request = {
				origin: new google.maps.LatLng(position.coords.latitude, position.coords.longitude), // 出発地
				destination: new google.maps.LatLng(data[0][data.length].store.lat, data[0][data.length].store.lon), // 目的地
				waypoints: [ // 経由地点(指定なしでも可)
					...points
					],
					travelMode: google.maps.DirectionsTravelMode.WALKING, // 交通手段(歩行。DRIVINGの場合は車)
		};	
		
		map = new google.maps.Map(document.getElementById('mapbox'), {
			center: {lat: 34.7019399, lng: 135.51002519999997},
			zoom: 14
		});  

		//マーカー
		var markerLatLng;
		for (var i = 0; i < data[0].length; i++) {
			markerLatLng = new google.maps.LatLng({lat: data[0][i].store.lat, lng: data[0][i].store.lon}); // 緯度経度のデータ作成
			marker[i] = new google.maps.Marker({ // マーカーの追加
				position: markerLatLng, // マーカーを立てる位置を指定
				map: map // マーカーを立てる地図を指定
			});

			infoWindow[i] = new google.maps.InfoWindow({ // 吹き出しの追加
				content: '<div class="sample">' + data[0][i].store.name + '</div>' // 吹き出しに表示する内容
			});

			markerEvent(i); // マーカーにクリックイベントを追加
		}

		function markerEvent(i) {
			marker[i].addListener('click', function() { // マーカーをクリックしたとき
				infoWindow[i].open(map, marker[i]); // 吹き出しの表示
			});
		}

		var d = new google.maps.DirectionsService(); // ルート検索オブジェクト
		var r = new google.maps.DirectionsRenderer({ // ルート描画オブジェクト
			map: map, // 描画先の地図
			preserveViewport: false, // 描画後に中心点をずらさない
		});
		
		// ルート検索
		d.route(request, function(result, status){
			// OKの場合ルート描画
			if (status == google.maps.DirectionsStatus.OK) {
				r.setDirections(result);
			}else{
				console.error(status);
				alert('ルート探索失敗');
			}
		});
		
	}, function() {
		alert('位置情報取得に失敗しました');
	}, {timeout: 5000}
	);
	
	
}


