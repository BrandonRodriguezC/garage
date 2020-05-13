<%-- 
    Document   : actualizarPrecios
    Created on : 12/05/2020, 08:08:42 AM
    Author     : Diego
--%>

<%@page import="com.garage.datos.Operaciones"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" xml:lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualización de Precios</title>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    </head>
    <body>
        <% Operaciones ope = new Operaciones();
            String tipoParqueadero[] = ope.getTipoParqueadero();
        %>

        <div id="menu" style="background: none"><a href="index.jsp"><i class="material-icons" style="font-size:30px; color:black  ">exit_to_app</i></a>
        </div>
        <h1>Actualizar Precios</h1>

        <form method="POST" action="./Reportes">
            <label id="tipoPrecio">Tipo de Parqueadero</label>
            <select name="tipoPrecio" id="tipoPrecio">
                <%
                    for (int i = 0; i < tipoParqueadero.length; i++) {
                        out.println("<option>" + tipoParqueadero[i] + "</option>");
                    }
                %>
            </select>
            <label id="precioMinuto">Precio Minuto</label>
            <input type="text" name="precioMinuto" id="precioMinuto">
            <label id="precioHora">Precio Hora</label>
            <input type="text" name="precioHora" id="precioHora">

            <input type="submit" name="actualizarPrecios" value="Actualizar Precios">

        </form>
    </body>
</html>
