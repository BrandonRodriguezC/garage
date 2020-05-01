/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var a = '${variable}';
var leftPanel = 0;
//var mbAttr = 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
//        '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
//        'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
//        mbUrl = 'https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw';
//
//var grayscale = L.tileLayer(mbUrl, {id: 'mapbox/light-v9', tileSize: 512, zoomOffset: -1, attribution: mbAttr}),
//        streets = L.tileLayer(mbUrl, {id: 'mapbox/streets-v11', tileSize: 512, zoomOffset: -1, attribution: mbAttr});
//
//var marcadores = L.featureGroup().on('click', function (e) {
//    var info = e.propagatedFrom['_popup']['_content'].split("<br>");
//    var direccion = info[1].split('<!--');
//    document.getElementById('ParqueaderoTitulo').innerHTML = info[0];
//    document.getElementById('Parqueadero_Titulo').setAttribute('value', info[0]);
//    document.getElementById('ParqueaderoTarifa').innerHTML = "Tarifa: " + direccion[0];
//    document.getElementById('Parqueadero_Tarifa').setAttribute('value', direccion[0]);
//    document.getElementById('ParqueaderoDireccion').innerHTML = "Direccion: " + direccion[1].replace(/-->/, '');
//    document.getElementById('Parqueadero_Direccion').setAttribute('value', direccion[1].replace(/-->/, ''));
//    document.getElementById('Parqueadero_Nit').setAttribute('value', direccion[2].replace(/-->/, '') + '');
//
//    document.getElementById('buscarPlazaForm').click();
//});
//
//var map = L.map('map', {
//    center: [4.655588, -74.112844],
//    zoom: 12,
//    layers: [streets, marcadores]
//});
//
//var baseLayers = {
//    "Grayscale": grayscale,
//    "Streets": streets
//};
//
//var overlays = {
//    "Marcadores": marcadores
//};
//
//L.control.layers(baseLayers, overlays).addTo(map);

function marcadoresG() {
    for (var item in a) {
        L.marker([a[item][0], a[item][1]]).bindPopup("<b>" + a[item][2] + "</b><br>$ "+a[item][5]+"/min  <!-- " + a[item][3] + "-->" + "<!--" + a[item][4] + "-->").addTo(marcadores);
    }
}

function checkInputs() {
    var titulo = document.getElementById('Parqueadero_Titulo').value;
    if (titulo != null) {
        document.getElementById('ParqueaderoTitulo').innerHTML = titulo;
    }
    var direccion =  document.getElementById('Parqueadero_Direccion').value;
    if (direccion != null) {
        document.getElementById('ParqueaderoDireccion').innerHTML = direccion;
    }
    var tarifa =  document.getElementById('Parqueadero_Tarifa').value;
    if (tarifa != null) {
        document.getElementById('ParqueaderoTarifa').innerHTML = tarifa;
    }
}


mapboxgl.accessToken = 'pk.eyJ1IjoiYnJhbmRvbnJvZHJpZ3VleiIsImEiOiJjazkwZDh1MTUwMGpyM2hvMGo1a2p5bnQzIn0.T-GzUebrNkyoXWR7v9u4qQ';
var map = new mapboxgl.Map({
    container: 'map',
    style: 'mapbox://styles/mapbox/outdoors-v11',
    center: [-74.092233, 4.653557],
    zoom: 15
});

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
                    'description': '<strong> ' + lista[i][2] + '</strong><br> Direccion:' + lista[i][3] + '<br> tarifa: $'+ lista[i][5] +'/min <br><!--' + lista[i][4] + '-->',
                    'icon': 'rocket'
                },
                'geometry': {
                    'type': 'Point',
                    'coordinates': [parseFloat(lista[i][1]), parseFloat(lista[i][0])]
                }
            });

        }
    }
//            
//            jsonObj['data']['features'].push({
//                'type': 'Feature',
//                'properties': {
//                    'description':
//                            '<strong>TEST</strong><p><a href="http://www.truckeroodc.com/www/" target="_blank">Truckeroo</a> brings dozens of food trucks, live music, and games to half and M Street SE (across from Navy Yard Metro Station) today from 11:00 a.m. to 11:00 p.m.</p>',
//                    'icon': 'car'
//                },
//                'geometry': {
//                    'type': 'Point',
//                    'coordinates': [-77.007481, 38.876516]
//                }
//            });
//            jsonObj['data']['features'].push({
//                'type': 'Feature',
//                'properties': {
//                    'description':
//                            '<strong>TEST 2</strong><p>The Arlington Players\' production of Stephen Sondheim\'s  <a href="http://www.thearlingtonplayers.org/drupal-6.20/node/4661/show" target="_blank" title="Opens in a new window"><em>A Little Night Music</em></a> comes to the Kogod Cradle at The Mead Center for American Theater (1101 6th Street SW) this weekend and next. 8:00 p.m.</p>',
//                    'icon': 'car'
//                },
//                'geometry': {
//                    'type': 'Point',
//                    'coordinates': [-77.020945, 38.878241]
//                }
//            });
//            jsonObj['data']['features'].push({
//                'type': 'Feature',
//                'properties': {
//                    'description':
//                            '<strong>TEST 3</strong><p>Jazz-influenced hip hop artist <a href="http://www.muhsinah.com" target="_blank" title="Opens in a new window">Muhsinah</a> plays the <a href="http://www.blackcatdc.com">Black Cat</a> (1811 14th Street NW) tonight with <a href="http://www.exitclov.com" target="_blank" title="Opens in a new window">Exit Clov</a> and <a href="http://godsilla.bandcamp.com" target="_blank" title="Opens in a new window">Gods’illa</a>. 9:00 p.m. $12.</p>',
//                    'icon': 'car'
//                },
//                'geometry': {
//                    'type': 'Point',
//                    'coordinates': [-77.031706, 38.914581]
//                }
//            });

    console.log(jsonObj);


    map.on('load', function () {
        map.addSource('places', jsonObj);

//        {
//                    'type': 'geojson',
//                    'data': {
//                        'type': 'FeatureCollection',
//                        'features': [
//                            {
//                                'type': 'Feature',
//                                'properties': {
//                                    'description':
//                                            '<strong>Make it Mount Pleasant</strong><p><a href="http://www.mtpleasantdc.com/makeitmtpleasant" target="_blank" title="Opens in a new window">Make it Mount Pleasant</a> is a handmade and vintage market and afternoon of live entertainment and kids activities. 12:00-6:00 p.m.</p>',
//                                    'icon': 'theatre'
//                                },
//                                'geometry': {
//                                    'type': 'Point',
//                                    'coordinates': [-77.038659, 38.931567]
//                                }
//                            },
//                            {
//                                'type': 'Feature',
//                                'properties': {
//                                    'description':
//                                            '<strong>Mad Men Season Five Finale Watch Party</strong><p>Head to Lounge 201 (201 Massachusetts Avenue NE) Sunday for a <a href="http://madmens5finale.eventbrite.com/" target="_blank" title="Opens in a new window">Mad Men Season Five Finale Watch Party</a>, complete with 60s costume contest, Mad Men trivia, and retro food and drink. 8:00-11:00 p.m. $10 general admission, $20 admission and two hour open bar.</p>',
//                                    'icon': 'theatre'
//                                },
//                                'geometry': {
//                                    'type': 'Point',
//                                    'coordinates': [-77.003168, 38.894651]
//                                }
//                            },
//                            {
//                                'type': 'Feature',
//                                'properties': {
//                                    'description':
//                                            '<strong>Big Backyard Beach Bash and Wine Fest</strong><p>EatBar (2761 Washington Boulevard Arlington VA) is throwing a <a href="http://tallulaeatbar.ticketleap.com/2012beachblanket/" target="_blank" title="Opens in a new window">Big Backyard Beach Bash and Wine Fest</a> on Saturday, serving up conch fritters, fish tacos and crab sliders, and Red Apron hot dogs. 12:00-3:00 p.m. $25.grill hot dogs.</p>',
//                                    'icon': 'bar'
//                                },
//                                'geometry': {
//                                    'type': 'Point',
//                                    'coordinates': [-77.090372, 38.881189]
//                                }
//                            },
//                            {
//                                'type': 'Feature',
//                                'properties': {
//                                    'description':
//                                            '<strong>Ballston Arts & Crafts Market</strong><p>The <a href="http://ballstonarts-craftsmarket.blogspot.com/" target="_blank" title="Opens in a new window">Ballston Arts & Crafts Market</a> sets up shop next to the Ballston metro this Saturday for the first of five dates this summer. Nearly 35 artists and crafters will be on hand selling their wares. 10:00-4:00 p.m.</p>',
//                                    'icon': 'art-gallery'
//                                },
//                                'geometry': {
//                                    'type': 'Point',
//                                    'coordinates': [-77.111561, 38.882342]
//                                }
//                            },
//                            {
//                                'type': 'Feature',
//                                'properties': {
//                                    'description':
//                                            '<strong>Seersucker Bike Ride and Social</strong><p>Feeling dandy? Get fancy, grab your bike, and take part in this year\'s <a href="http://dandiesandquaintrelles.com/2012/04/the-seersucker-social-is-set-for-june-9th-save-the-date-and-start-planning-your-look/" target="_blank" title="Opens in a new window">Seersucker Social</a> bike ride from Dandies and Quaintrelles. After the ride enjoy a lawn party at Hillwood with jazz, cocktails, paper hat-making, and more. 11:00-7:00 p.m.</p>',
//                                    'icon': 'bicycle'
//                                },
//                                'geometry': {
//                                    'type': 'Point',
//                                    'coordinates': [-77.052477, 38.943951]
//                                }
//                            },
//                            {
//                                'type': 'Feature',
//                                'properties': {
//                                    'description':
//                                            '<strong>Capital Pride Parade</strong><p>The annual <a href="http://www.capitalpride.org/parade" target="_blank" title="Opens in a new window">Capital Pride Parade</a> makes its way through Dupont this Saturday. 4:30 p.m. Free.</p>',
//                                    'icon': 'rocket'
//                                },
//                                'geometry': {
//                                    'type': 'Point',
//                                    'coordinates': [-77.043444, 38.909664]
//                                }
//                            },
////                            LISTO
//                            {
//                                'type': 'Feature',
//                                'properties': {
//                                    'description':
//                                            '<strong>Muhsinah</strong><p>Jazz-influenced hip hop artist <a href="http://www.muhsinah.com" target="_blank" title="Opens in a new window">Muhsinah</a> plays the <a href="http://www.blackcatdc.com">Black Cat</a> (1811 14th Street NW) tonight with <a href="http://www.exitclov.com" target="_blank" title="Opens in a new window">Exit Clov</a> and <a href="http://godsilla.bandcamp.com" target="_blank" title="Opens in a new window">Gods’illa</a>. 9:00 p.m. $12.</p>',
//                                    'icon': 'music'
//                                },
//                                'geometry': {
//                                    'type': 'Point',
//                                    'coordinates': [-77.031706, 38.914581]
//                                }
//                            },
////                            LISTO
//                            {
//                                'type': 'Feature',
//                                'properties': {
//                                    'description':
//                                            '<strong>A Little Night Music</strong><p>The Arlington Players\' production of Stephen Sondheim\'s  <a href="http://www.thearlingtonplayers.org/drupal-6.20/node/4661/show" target="_blank" title="Opens in a new window"><em>A Little Night Music</em></a> comes to the Kogod Cradle at The Mead Center for American Theater (1101 6th Street SW) this weekend and next. 8:00 p.m.</p>',
//                                    'icon': 'music'
//                                },
//                                'geometry': {
//                                    'type': 'Point',
//                                    'coordinates': [-77.020945, 38.878241]
//                                }
//                            },
////                            listo
//                            {
//                                'type': 'Feature',
//                                'properties': {
//                                    'description':
//                                            '<strong>Parking</strong><p><a href="http://www.truckeroodc.com/www/" target="_blank">Truckeroo</a> brings dozens of food trucks, live music, and games to half and M Street SE (across from Navy Yard Metro Station) today from 11:00 a.m. to 11:00 p.m.</p>',
//                                    'icon': 'car', 'marker-color': '#3bb2d0'
//                                },
//                                'geometry': {
//                                    'type': 'Point',
//                                    'coordinates': [-77.007481, 38.876516]
//                                }
//                            }
//                        ]
//                    }
//                });
//                
//                // Add a layer showing the places.
//                map.addLayer({
//                    'id': 'places',
//                    'type': 'symbol',
//                    'source': 'places',
//                    'layout': {
//                        'icon-image': '{icon}-15',
//                        'icon-allow-overlap': true
//                    }
//                });
        var popup = new mapboxgl.Popup({
            closeButton: false,
            closeOnClick: false
        });

        map.on('mouseenter', 'places', function (e) {
            // Change the cursor style as a UI indicator.
            map.getCanvas().style.cursor = 'pointer';

            var coordinates = e.features[0].geometry.coordinates.slice();
            var description = e.features[0].properties.description;

            // Ensure that if the map is zoomed out such that multiple
            // copies of the feature are visible, the popup appears
            // over the copy being pointed to.
            while (Math.abs(e.lngLat.lng - coordinates[0]) > 180) {
                coordinates[0] += e.lngLat.lng > coordinates[0] ? 360 : -360;
            }

            // Populate the popup and set its coordinates
            // based on the feature found.
            popup
                    .setLngLat(coordinates)
                    .setHTML(description)
                    .addTo(map);
        });

        map.on('mouseleave', 'places', function () {
            map.getCanvas().style.cursor = '';
            popup.remove();
        });
        // When a click event occurs on a feature in the places layer, open a popup at the
        // location of the feature, with description HTML from its properties.
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
//            
//            // Ensure that if the map is zoomed out such that multiple
            // copies of the feature are visible, the popup appears
            // over the copy being pointed to.
//            while (Math.abs(e.lngLat.lng - coordinates[0]) > 180) {
//                coordinates[0] += e.lngLat.lng > coordinates[0] ? 360 : -360;
//            }

//            new mapboxgl.Popup()
//                    .setLngLat(coordinates)
//                    .setHTML(description)
//                    .addTo(map);
//                    console.log("click en " + e.features[0].geometry.coordinates);
        });

        // Change the cursor to a pointer when the mouse is over the places layer.
        map.on('mouseenter', 'places', function () {
            map.getCanvas().style.cursor = 'pointer';
        });

        // Change it back to a pointer when it leaves.
        map.on('mouseleave', 'places', function () {
            map.getCanvas().style.cursor = '';
        });


        // Add a layer showing the places.
        map.addLayer({
            'id': 'places',
            'type': 'symbol',
            'source': 'places',
            'layout': {
                'icon-image': '{icon}-15',
                'icon-allow-overlap': true
            }
        });
        // 
        // 

        //// Create a popup, but don't add it to the map yet.
        //var popup = new mapboxgl.Popup({
        //closeButton: false,
        //closeOnClick: false
        //});
        // 
        //map.on('mouseenter', 'places', function(e) {
        //// Change the cursor style as a UI indicator.
        //map.getCanvas().style.cursor = 'pointer';
        // 
        //var coordinates = e.features[0].geometry.coordinates.slice();
        //var description = e.features[0].properties.description;
        // 
        //// Ensure that if the map is zoomed out such that multiple
        //// copies of the feature are visible, the popup appears
        //// over the copy being pointed to.
        //while (Math.abs(e.lngLat.lng - coordinates[0]) > 180) {
        //coordinates[0] += e.lngLat.lng > coordinates[0] ? 360 : -360;
        //}
        // 
        //// Populate the popup and set its coordinates
        //// based on the feature found.
        //popup
        //.setLngLat(coordinates)
        //.setHTML(description)
        //.addTo(map);
        //});
        // 
        //map.on('mouseleave', 'places', function() {
        //map.getCanvas().style.cursor = '';
        //popup.remove();
        //});
    });
}
