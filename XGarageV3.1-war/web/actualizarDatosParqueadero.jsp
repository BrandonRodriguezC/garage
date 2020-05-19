<%-- 
    Document   : actualizarDatos
    Created on : 25/03/2020, 09:34:50 AM
    Author     : Diego
--%>

<%@page import="com.garage.datos.Operaciones"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es" xml:lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assests/styles.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Actualizar Datos</title>
    </head>


    <body >
        <%
            Operaciones ope = new Operaciones();
            String tipoPlaza[] = ope.getTipoPlaza();
            String localidades[] = ope.getLocalidades();
        %>



        <div id="menu" >
            <table style="width: 100vw">
                <tr>
                    <th><a href="parqueadero.jsp" ><i class="material-icons" style="font-size:36px; color: white">keyboard_arrow_left</i></a></th>
                   
                    <th>Parqueadero</th>
                    <th><a href="index.jsp"><i class="material-icons" style="font-size:30px; color:white  ">exit_to_app</i></a></th>
                </tr>
            </table>  
        </div>

        <div class="flex" style="position: absolute; justify-content: space-evenly; width: 100vw; height: 100vh; background: #fafafa  ">
            <div class="panel">
                <h2>Plazas</h2>
                <table class="compacto" style="width: 90%">
                    <caption style="visibility: hidden">Plazas</caption>
                    <th id="Plaza">Plaza ID:</th>
                    <th id="TipoAuto">Tipo de Auto:</th>
                    <!--<th>Disponibilidad:</th>-->
                    <c:forEach items="${plazas}" var="plaza">
                        <tr>
                            <td>${plaza[0]}</td>
                            <td>${plaza[1]}</td>
                            <!--<td>${plaza[2]}</td>-->
                        </tr>
                    </c:forEach> 
                </table>

                <form action="./RespuestaParqueadero" method="GET">
                    <!-- Posiblemente Innecesario-->
                    <input type ="hidden" name="nit" value="${nit}">
                    <input type="submit" name="actualizarPlazas" value="Actualizar Plazas">
                </form>
            </div>
            <div class="panel">
                <h2>Agregar Plaza</h2>
                <form action="./RespuestaParqueadero" method="POST">
                    <table>
                        <tr>
                            <th><input type="hidden" name="usuario" value="${usuario}"></th>
                            <th><input type="hidden" name="nit" value="${nit}"></th>
                        </tr>
                        <tr>
                            <th><label for="nombrePlaza"> Nombre Plaza: </label></th>
                            <th> <input type="text" name="nombrePlaza" id="nombrePlaza" required></th>
                        </tr>
                        <tr>
                            <th><label for="tipoPlaza"> Tipo Vehiculo: </label></th>
                            <th><select name="tipoPlaza" id="tipoPlaza">
                                    <%
                                        for (int i = 0; i < tipoPlaza.length; i++) {
                                            out.println("<option>" + tipoPlaza[i] + "</option>");
                                        }
                                    %>
                                </select></th>
                        </tr>
                        <tr>
                             <th></th>
                            <th><input type="submit" name="agregarPlaza" value="Agregar Plaza"></th>
                        </tr>
                    </table>
                    
                </form>

                <h2>Eliminar Plaza</h2>            
                <form action="./RespuestaParqueadero" method="DELETE">
                    <table>
                        <tr>
                            <th><label for="nombrePlaza">Nombre Plaza:</label></th>
                            <th><input type="text" name="nombrePlaza" id="nombrePlaza"></th>
                        </tr>
                        <tr>
                            <th><input type="hidden" name="usuario" value="${usuario}"></th>
                            <th><input type="hidden" name="nit" value="${nit}"></th>
                        </tr>
                        <tr>
                             <th></th>
                             <th><input type="submit" name="eliminarPlaza" value="Eliminar Plaza"></th>
                        </tr>
                    </table>
                    
                </form> 

            </div>
            <div class="panel">
                <h2>Actualizar Datos</h2>
                <form action="./RespuestaParqueadero" method="POST">
                    <!-- Posiblemente Innecesario-->
                    <input type="hidden" name="nit" value="${nit}">
                    <table>
                        <tr>
                            <th><label for="contrasena"> Contraseña: </label></th>
                            <th><input type="password" name="contrasena" id="contrasena"></th>
                        </tr>
                        <tr>
                            <th><label for="nombre"> Nombre: </label></th>
                            <th><input type="text" name="nombre" id="nombre"></th>
                        </tr>
                        <tr>
                            <th><label for="ciudad"> Ciudad: </label></th>
                            <th><select name="ciudad" id="ciudad">
                                    <option> Bogota D.C </option>
                                </select></th>
                        </tr>
                        <tr>
                            <th><label for="direccion"> Direccion: </label></th>
                            <th><input type="text" name="direccion" id="direccion"></th>
                        </tr>
                        <tr>
                            <th><label for="localidad"> Localidad: </label></th>
                            <th><select name="localidad" id="localidad">
                                    <%
                                        for (int i = 0; i < localidades.length; i++) {
                                            out.println("<option>" + (i + 1) + " " + localidades[i] + "</option>");
                                        }
                                    %>
                                </select></th>
                        </tr>
                        <tr>
                            <th><label for="latitud"> Latitud: </label></th>
                            <th><input type="text" name="latitud" id="latitud" ></th>
                        </tr>
                        <tr>
                            <th><label for="longitud"> Longitud: </label></th>
                            <th><input type="text" name="longitud" id="longitud" ></th>
                        </tr>
                        <tr>
                            <th></th>
                            <th><input type="submit" name="actualizarDatos" value="Actualizar Datos"></th>
                        </tr>
                    </table>

                </form>
            </div>
        </div>


    </body>
</html>