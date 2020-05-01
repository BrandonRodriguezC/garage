<%-- 
    Document   : registro
    Created on : 25/03/2020, 09:33:59 AM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assests/styles.css">
        <title>Registro</title>
    </head>
    <body class="imagen2">
        <%
            String localidades[] = {"Usaquen", "Chapinero", "Santa Fe", "Sancristobal", "Usme", "Tunjuelito", "Bosa",
                "Kennedy", "Fontibon", "Engativa", "Suba", "Barrios Unidos", "Teusaquillo", "Los Mártires",
                "Antonio Nariño", "Puente Aranda", "La Candelaria", "Rafael Uribe Uribe", "Ciudad Bolivar",
                "Sumapaz"};
            String tipoParqueadero[] = {"Alturas o Subterraneos", "Concreto, Asfalto o Gravilla",
                "Piso en Afirmado o Cesped"};
        %>

        <div class="imagen2">
            <div class="center">
                <h1 style="color:white">Registro</h1>
                <div class ="flex">
                    <div class="register-user">
                        <h2>Usuario</h2>
                        <form action="./SolicitudIngreso" method="POST" >

                            <label for="numeroLicencia">Numero Licencia</label>
                            <input type="text" name="numeroLicencia" id="numeroLicencia" required> <br><br>

                            <label for="usuario"> Usuario: </label>
                            <input type="text" name="usuario" id="usuario" required> <br><br>

                            <label for="contrasena"> Contraseña: </label>
                            <input type="password" name="contrasena" id="contrasena" required> <br><br>

                            <label for="nombre"> Nombre: </label>
                            <input type="text" name="nombre" id="nombre" required> <br><br>

                            <label for="apellido"> Apellido: </label>
                            <input type="text" name="apellido" id="apellido" required> <br><br>

                            <label for="tipoDocumento"> Tipo de Documento: </label>
                            <select name="tipoDocumento" id="tipoDocumento" required>
                                <option> C.C </option>
                                <option> T.I</option>
                                <option> C.E</option>
                            </select> <br><br>

                            <label for="documentoIdentidad"> Documento de Identidad: </label>
                            <input type="text" name="documentoIdentidad" id="documentoIdentidad" onkeypress='return event.charCode >= 48 && event.charCode <= 57'> <br><br>

                            <label for="correo"> E-mail: </label>
                            <input type="email" name="correo" id="correo"> <br><br>

                            <label for="telefono"> Telefono: </label>
                            <input type="text" name="telefono" id="telefono" onkeypress='return event.charCode >= 48 && event.charCode <= 57'> <br><br>

                            <input type="hidden" name="tipoCliente" value="usuario">

                            <input type="submit" name="registro" value="Registrar Usuario">
                        </form>
                    </div>

                    <div class="register-park">
                        <h2>Parqueadero </h2>
                        <form action="./SolicitudIngreso" method="POST">
                            <input type="hidden" name="formato" value="parqueadero">

                            <label for="nit">Nit</label>
                            <input type="text" name="nit" id="nit" required> <br><br>

                            <label for="usuario"> Usuario: </label>
                            <input type="text" name="usuario" id="usuario" required> <br><br>

                            <label for="contrasena"> Contraseña: </label>
                            <input type="password" name="contrasena" id="contrasena" required> <br><br>

                            <label for="nombreParqueadero"> Nombre Parqueadero: </label>
                            <input type="text" name="nombreParqueadero" id="nombreParqueadero" required> <br><br>

                            <label for="ciudad"> Ciudad: </label>
                            <select name="ciudad" id="ciudad">
                                <option> Bogota D.C </option>
                            </select> <br><br>

                            <label for="direccion"> Dirección: </label>
                            <input type="text" name="direccion" id="direccion" required> <br><br>

                            <label for="tipo_Parqueadero"> Tipo de Parqueadero: </label> <br><br>

                            <select name="tipo_Parqueadero" id="tipo_Parqueadero">
                                <%
                                    for (int i = 0; i < tipoParqueadero.length; i++) {
                                        out.println("<option>" + tipoParqueadero[i] + "</option>");
                                    }
                                %>
                            </select> <br><br>

                            <label for="localidad"> Localidad: </label>
                            <select name="localidad" id="localidad">
                                <%
                                    for (int i = 0; i < localidades.length; i++) {
                                        out.println("<option>" + (i + 1) + " " + localidades[i] + "</option>");
                                    }
                                %>
                            </select> <br><br>

                            <label for="latitud"> Latitud: </label>
                            <input type="text" name="latitud" id="latitud" > <br><br>

                            <label for="longitud"> Longitud: </label>
                            <input type="text" name="longitud" id="longitud" > <br><br>

                            <input type="hidden" name="tipoCliente" value="parqueadero">

                            <input type="submit" name="registro" value="Registrar Parqueadero">
                        </form>
                    </div>
                </div>
            </div>

        </div>



    </body>
</html>