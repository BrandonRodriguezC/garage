/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var a = '${variable}';
var leftPanel = 0;
var latitud = -74.092233;
var longitud = 4.653557;

function checkInputs() {
    var titulo = document.getElementById('Parqueadero_Titulo').value;
    if (titulo != null) {
        document.getElementById('ParqueaderoTitulo').innerHTML = titulo;
    }
    var direccion = document.getElementById('Parqueadero_Direccion').value;
    if (direccion != null) {
        document.getElementById('ParqueaderoDireccion').innerHTML = direccion;
    }
    var tarifa = document.getElementById('Parqueadero_Tarifa').value;
    if (tarifa != null) {
        document.getElementById('ParqueaderoTarifa').innerHTML = tarifa;
    }
}


mapboxgl.accessToken = 'pk.eyJ1IjoiYnJhbmRvbnJvZHJpZ3VleiIsImEiOiJjazkwZDh1MTUwMGpyM2hvMGo1a2p5bnQzIn0.T-GzUebrNkyoXWR7v9u4qQ';
var map = new mapboxgl.Map({
    container: 'map',
    style: 'mapbox://styles/mapbox/outdoors-v11',
    center: [latitud, longitud],
    zoom: 11
});

map.addControl(
        new mapboxgl.GeolocateControl({
            positionOptions: {
                enableHighAccuracy: true
            },
            trackUserLocation: true
        })
        );

function geoJSON(lista) {
    var jsonObj = {
        'type': 'geojson',
        'data': {
            'type': 'FeatureCollection',
            'features': []}
    };

    if (lista != null) {
        for (var i = 0, max = lista.length; i < max; i++) {
            jsonObj['data']['features'].push({
                'type': 'Feature',
                'properties': {
                    'description': '<strong> ' + lista[i][2] + '</strong><br> Direccion:' + lista[i][3] + '<br> tarifa: $' + lista[i][5] + '/min <br><!--' + lista[i][4] + '-->',
                    'icon': 'rocket'
                },
                'geometry': {
                    'type': 'Point',
                    'coordinates': [parseFloat(lista[i][1]), parseFloat(lista[i][0])]
                }
            });

        }
    }

    map.on('load', function () {
        map.addSource('places', jsonObj);

        var popup = new mapboxgl.Popup({
            closeButton: false,
            closeOnClick: false
        });

        map.on('mouseenter', 'places', function (e) {

            map.getCanvas().style.cursor = 'pointer';

            var coordinates = e.features[0].geometry.coordinates.slice();
            var description = e.features[0].properties.description;

            while (Math.abs(e.lngLat.lng - coordinates[0]) > 180) {
                coordinates[0] += e.lngLat.lng > coordinates[0] ? 360 : -360;
            }

            popup
                    .setLngLat(coordinates)
                    .setHTML(description)
                    .addTo(map);
        });

        map.on('mouseleave', 'places', function () {
            map.getCanvas().style.cursor = '';
            popup.remove();
        });

        map.on('click', 'places', function (e) {
            var info = e.features[0].properties.description.split('<br>');
            document.getElementById('ParqueaderoTitulo').innerHTML = info[0];
            document.getElementById('Parqueadero_Titulo').setAttribute('value', info[0].replace('<strong>', '').replace('</strong>', ''));
            document.getElementById('ParqueaderoTarifa').innerHTML = info[2];
            document.getElementById('Parqueadero_Tarifa').setAttribute('value', info[2]);
            document.getElementById('ParqueaderoDireccion').innerHTML = info[1];
            document.getElementById('Parqueadero_Direccion').setAttribute('value', info[1]);
            document.getElementById('Parqueadero_Nit').setAttribute('value', info[3].replace('<!--', '').replace('-->', ''));
            document.getElementById('buscarPlazaForm').click();
        });

        map.on('mouseenter', 'places', function () {
            map.getCanvas().style.cursor = 'pointer';
        });

        map.on('mouseleave', 'places', function () {
            map.getCanvas().style.cursor = '';
        });

        map.addLayer({
            'id': 'places',
            'type': 'symbol',
            'source': 'places',
            'layout': {
                'icon-image': '{icon}-15',
                'icon-allow-overlap': true
            }
        });
    });
}