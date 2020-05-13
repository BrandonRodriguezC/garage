<%-- 
    Document   : registro
    Created on : 25/03/2020, 09:33:59 AM
    Author     : Diego
--%>

<%@page import="com.garage.datos.Operaciones"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" xml:lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assests/styles.css">
        <title>Registro</title>
    </head>
    <body class="imagen2">
        <%
            Operaciones ope = new Operaciones();
            String localidades[] = ope.getLocalidades();
            String tipoParqueadero[] = ope.getTipoParqueadero();
        %>

        <div class="imagen2">
            <div class="center">
                <h1 style="color:white">Registro</h1>
                <div class ="flex">
                    <div class="register-user">
                        <h2>Usuario</h2>
                        <form action="./SolicitudIngreso" method="POST" >

                            <label for="numeroLicencia">Numero Licencia</label>
                            <input type="text" name="numeroLicencia" id="numeroLicencia" maxlength="20" required> <br><br>

                            <label for="usuario"> Usuario: </label>
                            <input type="text" name="usuario" id="usuario" maxlength="30" required> <br><br>

                            <label for="contrasena"> Contraseña: </label>
                            <input type="password" name="contrasena" id="contrasena" maxlength="30" required> <br><br>

                            <label for="nombre"> Nombre: </label>
                            <input type="text" name="nombre" id="nombre"  maxlength="50" required> <br><br>

                            <label for="apellido"> Apellido: </label>
                            <input type="text" name="apellido" id="apellido" maxlength="50" required> <br><br>

                            <label for="tipoDocumento"> Tipo de Documento: </label>
                            <select name="tipoDocumento" id="tipoDocumento" required>
                                <option> C.C </option>
                                <option> T.I</option>
                                <option> C.E</option>
                            </select> <br><br>

                            <label for="documentoIdentidad"> Documento de Identidad: </label>
                            <input type="text" name="documentoIdentidad" id="documentoIdentidad" maxlength="20" >
                            <!--onkeypress='return event.charCode >= 48 && event.charCode <= 57'-->
                            <br><br>

                            <label for="correo"> E-mail: </label>
                            <input type="email" name="correo" id="correo" maxlength="50"> <br><br>

                            <label for="telefono"> Telefono: </label>
                            <input type="text" name="telefono" id="telefono" maxlength="10" > 
                            <!--onkeypress='return event.charCode >= 48 && event.charCode <= 57'-->
                            <br><br>

                            <input type="hidden" name="tipoCliente" value="usuario">

                            <input type="submit" name="registro" value="Registrar Usuario">
                        </form>
                    </div>

                    <div class="register-park">
                        <h2>Parqueadero </h2>
                        <form action="./SolicitudIngreso" method="POST">
                            <input type="hidden" name="formato" value="parqueadero">

                            <label for="nit">Nit</label>
                            <input type="text" name="nit" id="nit" maxlength="20" required> <br><br>

                            <label for="usuario"> Usuario: </label>
                            <input type="text" name="usuario" id="usuario" maxlength="30" required> <br><br>

                            <label for="contrasena"> Contraseña: </label>
                            <input type="password" name="contrasena" id="contrasena" maxlength="30" required> <br><br>

                            <label for="nombreParqueadero"> Nombre Parqueadero: </label>
                            <input type="text" name="nombreParqueadero" id="nombreParqueadero" maxlength="20" required> <br><br>

                            <label for="ciudad"> Ciudad: </label>
                            <select name="ciudad" id="ciudad">
                                <option> Bogota D.C </option>
                            </select> <br><br>

                            <label for="direccion"> Dirección: </label>
                            <input type="text" name="direccion" id="direccion" maxlength="30" required> <br><br>

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
                            <input type="text" name="latitud" id="latitud" maxlength="52" > <br><br>

                            <label for="longitud"> Longitud: </label>
                            <input type="text" name="longitud" id="longitud" maxlength="52" > <br><br>

                            <input type="hidden" name="tipoCliente" value="parqueadero">

                            <input type="submit" name="registro" value="Registrar Parqueadero">
                        </form>
                    </div>
                </div>
            </div>

        </div>



    </body>
</html>