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
        <link rel="stylesheet" href="assests/styles.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    </head>
    <body>

        <% Operaciones ope = new Operaciones();
            String tipoParqueadero[] = ope.getTipoParqueadero();
        %>

        <div id="menu" >
            <table style="width: 100vw">
                <tr><th><a href="index.jsp" ><i class="material-icons" style="font-size:36px; color: white">keyboard_arrow_left</i></a></th>
                   
                   
                    <th>Actualizar Precios</th>
                     <th style="width: 150px"><div class="logo" onclick="location.href = 'index.jsp';"></div>
                    </th>
                </tr>
            </table>  
        </div>
        <div class="middle flex">

            <div id="imagen-Precios"></div>
            <div id="register-pass">
                <h1>Actualizar Precios</h1>

                <form method="POST" action="./Reportes">
                    <table>
                        <tr>
                            <th><label id="tipoPrecio">Tipo de Parqueadero</label></th>
                            <th><select name="tipoPrecio" id="tipoPrecio">
                        <%
                            for (int i = 0; i < tipoParqueadero.length; i++) {
                                out.println("<option>" + tipoParqueadero[i] + "</option>");
                            }
                        %>
                    </select></th>
                        </tr>
                        <tr>
                            <th><label id="precioMinuto">Precio Minuto</label></th>
                            <th><input type="text" name="precioMinuto" id="precioMinuto"></th>
                        </tr>
                        <tr>
                            <th><label id="precioHora">Precio Hora</label></th>
                            <th><input type="text" name="precioHora" id="precioHora"></th>
                        </tr>
                        <tr>
                            <th></th>
                            <th><input type="submit" name="actualizarPrecios" value="Actualizar Precios"></th>
                        </tr>
                    </table>
                </form>
            </div>
        </div>

    </body>
</html>
