
//Información sobre Morris.js -> http://morrisjs.github.io/morris.js/bars.html

var error = [{label: "", value: NaN}];

var reporteDiario = crearBarras("reporteDiario");
var reportePlaza = crearBarras("reportePlaza");
var reporteIntervalo = crearBarras("reporteIntervalo");

var reporteMadrugada = crearDonut("reporteMadrugada");
var reporteManaina = crearDonut("reporteManaina");
var reporteTarde = crearDonut("reporteTarde");
var reporteNoche = crearDonut("reporteNoche");


function cambiarReporteDiario (datos){
    reporteDiario.setData(comprobarDatos(datos));
}

function cambiarReportePlaza (datos){
    reportePlaza.setData(comprobarDatos(datos));
}

function cambiarReportePorIntervalo (datos){
    reporteIntervalo.setData(comprobarDatos(datos));
}

function cambiarReporteHorario (mad, man, tar, noc){
    reporteMadrugada.setData(comprobarDatos(mad));
    reporteManaina.setData(comprobarDatos(man));
    reporteTarde.setData(comprobarDatos(tar));
    reporteNoche.setData(comprobarDatos(noc));
}

function comprobarDatos(dato){
    if (JSON.stringify(dato) === JSON.stringify(error)){
        return [{label: "Registros NO ENCONTRADOS", value: 1}];
    }
    return dato;
}

//---------------------------------------Metodos Funcionales -------------------------------------------------------

function transfomarJson(datos) {
    var json = [];
    for (var i = 0; i < datos.length; i++) {
        json.push({
            'label': datos[i][0],
            'value': Number(datos[i][1])
        });
    }
    return json;
}

function transformarString(datos) {
    var matriz = datos.split("%");
    for (var k = 0, fin = matriz.length; k < fin; k++) {
        matriz[k] = matriz[k].split("$");
    }
    return matriz;
}

//------------------------------------Crear Graficas ------------------------------
function crearBarras(nombre) {
    return new Morris.Bar({
        element: nombre,
        data: [{label: 'Ingrese el Parqueadero', value: 0}],
        xkey: 'label',
        ykeys: ['value'],
        labels: ['Total de Reservas:'],
        fillOpacity: 0.6,
        hideHover: 'auto',
        resize: false,
        barColors: ['#76D7C4', '#6C3483 '],
        gridTextColor: '#FFFFFF'
    });
}

function crearDonut(nombre) {
    return new Morris.Donut({
        element: nombre,
        data: [
            {label: "Ingrese el Parqueadero", value: 1}
        ],
        colors: ["#8e54e9", "#4776e6"],
        resize: false
    });
}
