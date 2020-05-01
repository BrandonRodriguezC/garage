<%-- 
    Document   : actualizarDatos
    Created on : 25/03/2020, 09:34:50 AM
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
        <title>Actualizar Datos</title>
    </head>
    <body >
        <div class="imagen2">
            
               
                <div class="menu" style="display: flex; justify-content: space-between">
                    <a href="parqueadero.jsp" ><i class="material-icons" style="font-size:36px">keyboard_arrow_left</i></a>
                    <h1 style="color: white">Actualizar Datos</h1>
                    <a href="index.jsp" ><i class="material-icons" style="font-size:30px; color:white  ">exit_to_app</i></a>
                </div>

                <div class="flex" style="position: absolute; justify-content: space-evenly; width: 100vw; height: 100vh;  ">
                    <div class="panel">
                        <h2>Plazas</h2>
                        <table class="tableQ" style="width: 90%">
                            <th>Plaza ID:</th>
                            <th>Tipo de Auto:</th>
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
                            <input type="hidden" name="usuario" value="${usuario}">
                            <input type="hidden" name="nit" value="${nit}">

                            <label for="nombrePlaza"> Nombre Plaza: </label>
                            <input type="text" name="nombrePlaza" id="nombrePlaza" required><br><br>

                            <label for="tipoPlaza"> Tipo Vehiculo: </label>
                            <select name="tipoPlaza" id="tipoPlaza">
                                <%
                                    String tipoPlaza[] = {"Ligeros", "Pesados", "Especial o Agricola"};
                                    for (int i = 0; i < tipoPlaza.length; i++) {
                                        out.println("<option>" + tipoPlaza[i] + "</option>");
                                    }
                                %>
                            </select><br><br>

<!--                            <label for="disponibilidad"> Disponibilidad: </label>
                            <select name="disponibilidad" id="disponibilidad">
                                <option> True </option>
                                <option> False </option>
                            </select>-->
                            <br><br>

                            <input type="submit" name="agregarPlaza" value="Agregar Plaza">
                        </form>
                    </div>
                    <div class="panel">
                        <h2>Actualizar Datos</h2>
                        <form action="./RespuestaParqueadero" method="POST">
                            <!-- Posiblemente Innecesario-->
                            <input type ="hidden" name="usuario" value="${usuario}">
                            <input type="hidden" name="nit" value="${nit}">

                            <label for="contrasena"> Contraseña: </label>
                            <input type="password" name="contrasena" id="contrasena"><br><br>

                            <label for="nombre"> Nombre: </label>
                            <input type="text" name="nombre" id="nombre"> <br><br>

                            <label for="ciudad"> Ciudad: </label>
                            <select name="ciudad" id="ciudad">
                                <option> Bogota D.C </option>
                            </select> <br><br>

                            <label for="direccion"> Direccion: </label>
                            <input type="text" name="direccion" id="direccion"><br><br>

                            <label for="localidad"> Localidad: </label>
                            <select name="localidad" id="localidad">
                                <%
                                    String localidades[] = {"Usaquen", "Chapinero", "Santa Fe", "Sancristobal", "Usme", "Tunjuelito", "Bosa",
                                        "Kennedy", "Fontibon", "Engativa", "Suba", "Barrios Unidos", "Teusaquillo", "Los Mártires",
                                        "Antonio Nariño", "Puente Aranda", "La Candelaria", "Rafael Uribe Uribe", "Ciudad Bolivar",
                                        "Sumapaz"};
                                    for (int i = 0; i < localidades.length; i++) {
                                        out.println("<option>" + (i + 1) + " " + localidades[i] + "</option>");
                                    }
                                %>
                            </select> <br><br>

                            <input type="submit" name="actualizarDatos" value="Actualizar Datos">
                        </form>
                        <br><br>

                        <h2>Eliminar Plaza</h2>            
                        <form action="./RespuestaParqueadero" method="DELETE">
                            <label for="nombrePlaza">Nombre Plaza:</label>
                            <input type="text" name="nombrePlaza" id="nombrePlaza">

                            <!-- Posiblemente Innecesario-->
                            <input type="hidden" name="usuario" value="${usuario}">
                            <input type="hidden" name="nit" value="${nit}">

                            <input type="submit" name="eliminarPlaza" value="Eliminar Plaza">
                        </form> 
                    </div>
                </div>
            </div>
      


        
    </body>
</html>