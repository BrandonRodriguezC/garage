<%-- 
    Document   : parqueadero
    Created on : 25/03/2020, 09:34:40 AM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es" xml:lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assests/styles.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Parqueadero</title>
    </head>
    <body >




        <div>
            <div id="menu" >
                <table style="width: 100vw">
                    <tr>
                        <th style="width: 150px"><div class="logo" onclick="location.href = 'index.jsp';"></div>
                            </th>
                        <th>Parqueadero</th>
                        <th><a href="index.jsp"><i class="material-icons" style="font-size:30px; color:white  ">exit_to_app</i></a></th>
                    </tr>
                </table>  
            </div>
            <div class="flex" style="position: absolute; width: 100vw; height: auto; background: #fafafa">
                <div class="left-dahsboard">

                    <h2 >Parqueadero</h2>
                    <h4>NIT: ${nit}</h4>
                    <h4>Nombre: ${nombre}</h4>
                    <h4>Ciudad: ${ciudad}</h4>
                    <h4>Direccion ${direccion}</h4>
                    <h4>Localidad: ${localidad}</h4>
                    <!--<a href="actualizarDatosParqueadero.jsp">Actualizar Datos</a>-->
                    <form action="./RespuestaParqueadero" method="POST">
                        <!-- Posiblemente Innecesario-->
                        <input type="hidden" name="usuario" value="${usuario}">
                        <input type="hidden" name="nit" value="${nit}">
                        <input type="button"  onclick="location.href = 'actualizarDatosParqueadero.jsp';" value="Actualizar Datos" />
                        <input type="submit" name="eliminarCuenta" value="Eliminar Cuenta">
                    </form>
                    <br>

                </div>
                <div class="center-dashboard">


                    <h2>Ingresar Vehiculo</h2>
                    <form action="./RespuestaParqueadero"  method="GET">
                        <label for="tipoDeAuto"> Tipo Vehiculo: </label>
                        <select name="tipoDeAuto" id="tipoDeAuto">
                            <%
                                String tipoPlaza[] = {"Ligeros", "Pesados", "Especial o Agricola"};
                                for (int i = 0; i < tipoPlaza.length; i++) {
                                    out.println("<option>" + tipoPlaza[i] + "</option>");
                                }
                            %>
                        </select>
                        <br><br>
                        <!-- Posiblemente Innecesario-->
                        <input type="hidden" name="nit" value="${nit}">
                        <input type="submit" name="plazasDisponibles" value="Plazas Disponibles">
                    </form>

                    <form action="./RespuestaParqueadero"  method="POST">
                        <table class="compacto">
                            <caption style="visibility: hidden">Reservar</caption>
                            <th id="adquirirPlaza">Plaza ID</th>
                            <th id="adquirirPlaza">Tipo de Auto</th>
                            <th id="adquirirPlaza">Reservar</th>
                                <c:forEach items="${plazasDisponibles}" var="plazaDisponible">
                                <tr>
                                    <td>${plazaDisponible[0]}</td>
                                    <td>${plazaDisponible[1]}</td>
                                    <td><input type="radio" name="plazaId" value="${plazaDisponible[0]}"></td>
                                </tr>
                            </c:forEach> 
                        </table><br><br>

                        <label for="modelo"> Modelo del Vehiculo:</label>
                        <input type="text" name="modelo" id="modelo"> <br><br>

                        <label for="placa"> Placa del Vehiculo:</label>
                        <input type="text" name="placa" id="placa">

                        <!-- Posiblemente Innecesario-->
                        <input type="hidden" name="nit" value="${nit}">

                        <input type="submit" name="adquirirPlaza" value="Adquirir Plaza">
                    </form>
                </div>


                <div class="right-dashboard" >

                    <h2>Reservas Activas</h2>
                    <form action="./RespuestaParqueadero" method="GET"> 
                        <table class="compacto" >
                            <caption style="visibility: hidden">Registros</caption>
                            <th id="reservaActivaSeleccionada">Plaza ID</th>
                            <th id="reservaActivaSeleccionada">Placa</th>
                            <!--<th style="display: none">Fecha </th>-->
                            <th id="reservaActivaSeleccionada">Fecha de Reserva</th>
                            <th id="reservaActivaSeleccionada">Fecha de Entrada</th>
                            <th id="reservaActivaSeleccionada">Fecha de Salida</th>
                            <th id="reservaActivaSeleccionada">Valor</th>
                            <th id="reservaActivaSeleccionada">Seleccionar</th>
                                <c:forEach items="${reservasActivas}" var="reservaActiva" varStatus="status">
                                <tr style="color:${color[status.index]}">
                                    <td >${reservaActiva[0]}</td>
                                    <td >${reservaActiva[1]}</td>
                                    <!--<td >${reservaActiva[2]}</td>-->
                                    <td >${reservaActiva[3]}</td>
                                    <td >${reservaActiva[4]}</td>
                                    <td >${reservaActiva[5]}</td>
                                    <td >${reservaActiva[6]}</td>
                                    <td ><input type="radio" name="reservaActivaSeleccionada" value="${reservaActiva[0]}/${reservaActiva[2]}/${color[status.index]}/${reservaActiva[1]}/${reservaActiva[4]}"></td>
                                </tr>
                            </c:forEach> 
                        </table>
                        <br><br>
                        <!-- Posiblemente Innecesario-->
                        <input type="hidden" name="nit" value="${nit}">

                        <input type="datetime-local" id="fecha"
                               name="fecha" min="2020-04-00T00:00" max="2020-12-31T23:00"> <br><br>

                        <input type="submit" name="reservasActivas" value="Actualizar Reservas">
                        <input type="submit" name="ingresarVehiculoReserva" value="Ingresar Vehiculo">
                        <input type="submit" name="retirarVehiculoReserva" value="Retirar Vehiculo">
                        <input type="submit" name="penalizar" value="Penalizar">
                        <input type="submit" name="historial" value="Historial">
                        <input type="submit" name="pendientes" value="Pendientes">
                    </form>
                </div>

            </div>

        </div>


        <div class="filter" style="display:${filterV}" onclick="this.style.display = 'none'">
            <div class="popUp">
                <h1>${popUpH}</h1>
                <p>${popUpD}</p>
            </div>
        </div>


    </body>
</html>