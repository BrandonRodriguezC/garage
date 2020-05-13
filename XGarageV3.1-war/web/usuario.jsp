<%-- 
    Document   : cliente
    Created on : 25/03/2020, 09:35:09 AM
    Author     : Diego
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es" xml:lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assests/styles.css">

        <meta name="viewport" content="initial-scale=1,maximum-scale=1,user-scalable=no" />
        <script src="https://api.mapbox.com/mapbox-gl-js/v1.9.1/mapbox-gl.js"></script>
        <link href="https://api.mapbox.com/mapbox-gl-js/v1.9.1/mapbox-gl.css" rel="stylesheet" />
        <!--<link rel="stylesheet" href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css" integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ==" crossorigin=""/>-->
        <!--<script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js" integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew==" crossorigin=""></script>-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Cliente</title>
    </head>
    <%
        String localidades[] = {"Usaquen", "Chapinero", "Santa Fe", "Sancristobal", "Usme", "Tunjuelito", "Bosa",
            "Kennedy", "Fontibon", "Engativa", "Suba", "Barrios Unidos", "Teusaquillo", "Los Mártires",
            "Antonio Nariño", "Puente Aranda", "La Candelaria", "Rafael Uribe Uribe", "Ciudad Bolivar",
            "Sumapaz"};
        String tipoPlaza[] = {"Ligeros", "Pesados", "Especial o Agricola"};
    %>
    <body>

        <div class="flex" style="position: relative">
            <div class="slider">
                <div id="menu">
                    <!--Cerrar Sesión-->
                    <a href="index.jsp" ><i class="material-icons" style="font-size:30px; color:white ">exit_to_app</i></a>
                </div>
                <div id="ref">
                    <a href="#slide-1">Info</a>
                    <a href="#slide-2">Reservar</a>
                    <a href="#slide-3">Reservas Activas</a>
                    <a href="#slide-4">Historial</a>
                </div>
                <div class="slides">

                    <div id="slide-1" style=" display: flex;
                         justify-content: space-around;
                         align-items: center;
                         flex-direction: column;">
                        <h2>Informacion de usuario</h2>
                        <div>
                            <h3 style="display: none">No Licencia: ${numeroLicencia}</h3>
                            <h3>Nombre: ${nombre}</h3>
                            <h3>Apellido: ${apellido}</h3>
                            <h3>Telefono: ${telefono}</h3>
                            <h3>${tipodocumento}: ${documentoidentidad}</h3>
                        </div>


                        <form action="./RespuestaUsuario" method="DELETE">
                            <!-- Posiblemente Innecesario-->
                            <input type="hidden" name="usuario" value="${usuario}">
                            <input type="hidden" name="numeroLicencia" value="${numeroLicencia}">
                            <input type="submit" name="eliminarCuenta" value="Eliminar Cuenta">
                            <input type="button"  onclick="location.href = 'actualizarDatosUsuario.jsp';" value="Actualizar Datos" />
                        </form>

                    </div>

                    <div id="slide-2">
                        <h2>Realiza tu reserva</h2>
                        <form action="./RespuestaUsuario" method="GET">
                            <label for="direccion"> Direccion: </label>
                            <input type="text" name="direccion" id="direccion"> 
                            <br><br>
                            <label for="localidad"> Localidad: </label>
                            <select name="localidad" id="localidad">
                                <%  for (int i = 0; i < localidades.length; i++) {
                                        out.println("<option>" + (i + 1) + " " + localidades[i] + "</option>");
                                    }
                                %>
                            </select>
                            <br><br>
                            <label for="localidad"> Fecha: </label>
                            <input type="datetime-local" id="fecha"
                                   name="fecha" value="${fecha}"
                                   min="2020-04-00T00:00" max="2020-12-31T23:00">
                            <br><br>
                            <label for="tipoPlaza"> Tipo Vehiculo: </label>
                            <select name="tipoPlaza" id="tipoPlaza">
                                <%  for (int i = 0; i < tipoPlaza.length; i++) {
                                        out.println("<option>" + tipoPlaza[i] + "</option>");
                                    }
                                %>
                            </select>
                            <br><br>
                            <input type="submit" name="buscarParqueadero" value="Buscar Parqueaderos">
                        </form>
                    </div>

                    <div id="slide-3">
                        <div>
                            <h2>Tus reservas</h2>
                            <form action="./RespuestaUsuario" method="GET">
                                <table class="tableQ">
                                    <caption>Reservas</caption>
                                    <th id="tablaReservas">Parqueadero</th>
                                    <th id="tablaReservas">Direccion</th>
                                    <th id="tablaReservas">Plaza Id</th>
                                    <th id="tablaReservas">Fecha de Reserva</th>
                                    <th id="tablaReservas">Entrada de Vehiculo</th>
                                    <th id="tablaReservas">Seleccionar</th>
                                    
                                        <c:forEach items="${reservasActivas}" var="reservaActiva" varStatus="status">
                                        <tr>
                                            <td style="color:${color[status.index]}">${reservaActiva[0]}</td>
                                            <td style="color:${color[status.index]}">${reservaActiva[1]}</td>
                                            <td style="color:${color[status.index]}">${reservaActiva[2]}</td>
                                            <td style="color:${color[status.index]}">${reservaActiva[3]}</td>
                                            <td style="color:${color[status.index]}">${reservaActiva[5]}</td>
                                            <td><input type="radio" name="reservaPK" value="${reservaActiva[4]}/${reservaActiva[2]}/${reservaActiva[5]}"></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <!--Posible Innecesario-->
                                <input type="hidden" name="numeroLicencia" value="${numeroLicencia}" >
                                <div style="margin-top: 40px;"> 
                                    <input type="submit" name="reservasActivas" value="Actualizar Reservas">
                                    <input type="submit" name="costoDeReserva" value="Calcular Deuda Actual">
                                    <input type="submit" name="cancelarReservas" value="Cancelar Reserva">
                                </div>
                            </form> <br><br>
                            <p> ${costo}</p>
                        </div>
                    </div>    
                    <div id="slide-4">
                        <h2>Historial</h2>
                        <form action="./RespuestaUsuario" method="GET">
                            <label for="localidadReporte"> Localidad: </label>
                            <select name="localidadReporte" id="localidadReporte">
                                <%  for (int i = 0; i < localidades.length; i++) {
                                        out.println("<option>" + (i + 1) + " " + localidades[i] + "</option>");
                                    }
                                %>
                            </select> <br><br>

                            <input type="datetime-local" id="fechaReporte"
                                   name="fechaReporte" min="2020-04-00T00:00" max="2020-12-31T23:00">
                            <!--Posible Innecesario-->
                            <input type="hidden" name="numeroLicencia" value="${numeroLicencia}">
                            <input type="submit" name="reporteUsuario" value="Generar Reporte">
                        </form>
                        <table >
                            <caption>Reportes</caption>
                            <th id="tablaParqueadero">Parqueadero</th>
                            <th id="tablaParqueadero">Fecha de Reserva</th>
                            <th id="tablaParqueadero">Fecha de Entrada</th>
                            <th id="tablaParqueadero">Fecha de Salida</th>
                            <th id="tablaParqueadero">Valor</th>
                                <c:forEach items="${reportes}" var="reporte">
                                <tr>
                                    <td>${reporte[0]}</td>
                                    <td>${reporte[1]}</td>
                                    <td>${reporte[2]}</td>
                                    <td>${reporte[3]}</td>
                                    <td>${reporte[4]}</td>
                                </tr>
                            </c:forEach> 
                        </table>
                    </div>
                </div>
            </div>

            <div id='map' > </div>
            <!-- 73-->
            <div id="card-2" style="display: ${panelDerecho}">
                <h1 id="ParqueaderoTitulo" > </h1>
                <h2 id="ParqueaderoTarifa"> </h2>
                <p id="ParqueaderoDireccion"> </p>
                <form  action="./RespuestaUsuario" method="POST" >
                    <table class="tableQ" >
                        <caption>Plaza Reserva</caption>
                        <th id="plazaReserva">Plaza Id</th>
                        <th id="plazaReserva"> Reservar</th>
                            <c:forEach items="${plazasLista}" var="plaza">
                            <tr>
                                <td>${plaza}</td>
                                <td><input type="radio" name="plaza_Reservar" value="${plaza}" > </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <input type="hidden" id="Parqueadero_Nit" name="Parqueadero_Nit" value="${ParqueaderoNitI}" >
                    <input type="hidden" id="Parqueadero_Titulo" name="Parqueadero_Titulo" value="${ParqueaderoTituloI}" >
                    <input type="hidden" id="Parqueadero_Tarifa" name="Parqueadero_Tarifa" value="${ParqueaderoTarifaI}" >
                    <input type="hidden" id="Parqueadero_Direccion" name="Parqueadero_Direccion" value="${ParqueaderoDireccionI}" >
                    <input style="display:none" type="submit" id="buscarPlazaForm" name="BuscarPlaza" value="Buscar plaza" >
                    <input type="hidden" name="numero_Licencia" value="${numeroLicencia}" > <!--listo-->
                    <input type="hidden" name="tipoDeAuto" value="${tipoDeAuto}" > <!--listo-->
                    <input style="display:none" type="datetime-local" id="fecha"
                           name="fecha" value="${fecha}"
                           min="2020-04-00T00:00" max="2020-12-31T23:00">
                    <br>
                    <label for="modelo"> Modelo del Vehiculo:</label>
                    <input type="text" name="modelo" id="modelo" >
                    <br><br>
                    <label for="placa"> Placa del Vehiculo:</label>
                    <input type="text" name="placa" id="placa" >
                    <br><br>
                    <input type="submit" name="reservar" value="Reservar">
                    <p>${mensajeReserva}</p>
                </form>

            </div>
        </div>
    </body>

    <script src="assests/script.js"></script>
    <script>
        
                                checkInputs();
        <%            List<Object[]> json = new ArrayList<Object[]>();
            String result = "";
            if ((List<Object[]>) session.getAttribute("variable") != null) {
                json = (List<Object[]>) session.getAttribute("variable");
                for (int i = 0; i < json.size(); i++) {
                    for (int j = 0; j < json.get(i).length; j++) {
                        if (j != json.get(i).length - 1) {
                            result += json.get(i)[j] + "$";
                        } else {
                            result += json.get(i)[j];
                        }
                    }
                    if (i != json.size() - 1) {
                        result += "%";
                    }
                }
            }
        %>
                                var a = "<%= result%>";
                                a = a.split("%");
                                for (var i = 0, max = a.length; i < max; i++) {
                                    a[i] = a[i].split("$");
                                }
                                geoJSON(a);
    </script>
</html>