<%-- 
    Document   : actualizarDatos
    Created on : 25/03/2020, 09:35:31 AM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" xml:lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assests/styles.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Actualizar Datos</title>
    </head>
    <body style="background: #fafafa">
        <div id="menu" >
            <table style="width: 100vw">
                <tr>
                    <th><a href="usuario.jsp" ><i class="material-icons" style="font-size:36px; color: white">keyboard_arrow_left</i></a></th>
                   
                    <th>Actualizar datos</th>
                    <th style="width: 150px"><div class="logo" onclick="location.href = 'index.jsp';"></div>
                    </th>
                </tr>
            </table>  
        </div>
        <div class="middle flex">

            <div id="imagen-formulario"></div>
            <div id="register-pass">

                <h1>Informacion</h1>

                <form action="./RespuestaUsuario" method="POST">

                    <table>
                        <tr>
                            <th><input type="hidden" name="usuario" value="${usuario}"></th>
                            <th><input type="hidden" name="numeroLicencia" value="${numeroLicencia}"></th>
                        </tr>
                        <tr>
                            <th><label for="nombre"> Nombre: </label></th>
                            <th> <input type="text" name="nombre" id="nombre"> </th>
                        </tr>
                        <tr>
                            <th><label for="apellido"> Apellido: </label></th>
                            <th><input type="text" name="apellido" id="apellido"> </th>
                        </tr>
                        <tr>
                            <th><label for="contrasena"> Contraseña: </label></th>
                            <th><input type="password" name="contrasena" id="contrasena"> </th>
                        </tr>
                        <tr>
                            <th><label for="tipoDocumento"> Tipo de Documento: </label></th>
                            <th><select name="tipoDocumento" id="tipoDocumento">
                                    <option> C.C </option>
                                    <option> T.I</option>
                                    <option> C.E</option>
                                </select> </th>
                        </tr>
                        <tr>
                            <th><label for="documentoIdentidad"> Documento de Identidad: </label></th>
                            <th><input type="text" name="documentoIdentidad" id="documentoIdentidad"> </th>
                        </tr>
                        <tr>
                            <th><label for="correo"> E-mail: </label></th>
                            <th><input type="email" name="correo" id="correo"></th>
                        </tr>
                        <tr>
                            <th><label for="telefono"> Telefono: </label></th>
                            <th><input type="text" name="telefono" id="telefono"></th>
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