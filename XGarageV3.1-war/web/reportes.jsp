<%-- 
    Document   : reportes
    Created on : 28/03/2020, 02:24:20 PM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assests/styles.css">
        <title>Reportes</title>
    </head>
    <body >

        <div class="imagen2">
            <div class="center">
                <h1 >Reportes</h1>
                <form action="./Reportes" method="GET">

                    <label for="parqueadero">Parqueadero</label>
                    <input type="text" name="nombreParqueadero">
                    <br><br>

                    <label for="localidad"> Localidad: </label>
                    <select name="localidad" id="localidad">
                        <%
                            String localidades[] = {"Usaquen", "Chapinero", "Santa Fe", "Sancristobal", "Usme", "Tunjuelito", "Bosa",
                                "Kennedy", "Fontibon", "Engativa", "Suba", "Barrios Unidos", "Teusaquillo", "Los Mártires",
                                "Antonio Nariño", "Puente Aranda", "La Candelaria", "Rafael Uribe Uribe", "Ciudad Bolivar",
                                "Sumapaz", "Todos"};
                            for (int i = 0; i < localidades.length; i++) {
                                if ((i + 1) != localidades.length) {
                                    out.println("<option>" + (i + 1) + " " + localidades[i] + "</option>");
                                } else {
                                    out.println("<option>" + localidades[i] + "</option>");
                                }
                            }
                        %>
                    </select>
                    <br><br>
                    <label for="fecha_Diaria">Fecha</label>
                    <input type="datetime-local" id="fecha_Diaria" name="fecha_Diaria" 
                           min="2020-04-00T00:00" max="2020-12-31T23:00">
                    
                    
                    <p>Reporte de: </p>
                    <input type="datetime-local" id="fecha_Inicio" name="fecha_Inicio" 
                           min="2020-04-00T00:00" max="2020-12-31T23:00"> 
                    <p> a </p>
                    <input type="datetime-local" id="fecha_Fin" name="fecha_Fin" 
                           min="2020-04-00T00:00" max="2020-12-31T23:00"> <br><br>
                    
                    <input type="submit" name="reportesSecretariaTransportes" value="Reporte Diario">
                    <input type="submit" name="reportesSecretariaTransportes" value="Reporte por Intervalo">
                    <input type="submit" name="reportesSecretariaTransportes" value="Reporte por Horario">
                </form>
                    <p>${advertencia}</p>
                <br><br>
                <table class="tableQ">
                    <th>Nombre</th>
                    <th>Tipo Vehiculo</th>
                    <th>Cantidad de Reservas</th>
                    <th>Ganacias</th>
                        <c:forEach items="${reportes}" var="reporte">
                        <tr>
                            <td>${reporte[0]}</td>
                            <td>${reporte[1]}</td>
                            <td>${reporte[2]}</td>
                            <td>${reporte[3]}</td>
                        </tr>
                    </c:forEach> 
                </table>
            </div>
        </div>



    </body>
</html>