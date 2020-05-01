<%-- 
    Document   : parqueadero
    Created on : 25/03/2020, 09:34:40 AM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assests/styles.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Parqueadero</title>
    </head>
    <body class="imagen2">




        <div>
            <div class="flex" style="position: absolute; justify-content: space-evenly; width: 100vw; height: 100vh ">
                <div class="panel">
                    <div id="menu" style="background: none"><a href="index.jsp"><i class="material-icons" style="font-size:30px; color:white  ">exit_to_app</i></a>
                    </div>
                    <h1 >Parqueadero</h1>
                    <h2>NIT: ${nit}</h2>
                    <h2 >Nombre: ${nombre}</h2>
                    <h2>Ciudad: ${ciudad}</h2>
                    <h2>Direccion ${direccion}</h2>
                    <h2>Localidad: ${localidad}</h2>
                    <a href="actualizarDatosParqueadero.jsp">Actualizar Datos</a>
                    <form action="./RespuestaParqueadero" method="POST">
                        <!-- Posiblemente Innecesario-->
                        <input type="hidden" name="usuario" value="${usuario}">
                        <input type="hidden" name="nit" value="${nit}">

                        <input type="submit" name="eliminarCuenta" value="Eliminar Cuenta">
                    </form>
                    <br>



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
                        <table class="tableQ">
                            <th>Plaza ID</th>
                            <th>Tipo de Auto</th>
                            <th>Reservar</th>
                                <c:forEach items="${plazasDisponibles}" var="plazaDisponible">
                                <tr>
                                    <td>${plazaDisponible[0]}</td>
                                    <td>${plazaDisponible[1]}</td>
                                    <td><input type="radio" name="tipoReserva" value="${plazaDisponible[0]}"></td>
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

                <div class="panel">
                    <h2>Reservas Activas</h2>
                    <form action="./RespuestaParqueadero" method="GET"> 
                        <table >
                            <th>Plaza ID</th>
                            <th>Usuario</th>
                            <!--<th style="display: none">Fecha </th>-->
                            <th>Fecha de Reserva</th>
                            <th>Fecha de Entrada</th>
                            <th>Fecha de Salida</th>
                            <th>Valor</th>
                            <th>Seleccionar</th>
                                <c:forEach items="${reservasActivas}" var="reservaActiva">
                                <tr>
                                    <td>${reservaActiva[0]}</td>
                                    <td>${reservaActiva[1]}</td>
                                    <!--<td style="display: none">${reservaActiva[2]}</td>-->
                                    <td>${reservaActiva[3]}</td>
                                    <td>${reservaActiva[4]}</td>
                                    <td>${reservaActiva[5]}</td>
                                    <td>${reservaActiva[6]}</td>
                                    <td><input type="radio" name="reservaActivaSeleccionada" value="${reservaActiva[0]}/${reservaActiva[2]}"></td>
                                </tr>
                            </c:forEach> 
                        </table>
                        <br><br>

                        <!-- Posiblemente Innecesario-->
                        <input type="hidden" name="nit" value="${nit}">
                        
                        <input type="datetime-local" id="fecha"
                               name="fecha" min="2020-04-00T00:00" max="2020-12-31T23:00"> <br><br>
                        
                        <input type="submit" name="reservasActivas" value="Actualizar Reservas">
                        <input type="submit" name="ingresarVehiculoReserva" value="Ingresar Vehivulo Reserva">
                        <input type="submit" name="retirarVehiculoReserva" value="Retirar Vehivulo Reserva">
                        
                    </form>
                </div>

            </div>

        </div>





    </body>
</html>