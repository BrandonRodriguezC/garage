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
    <body >
        <div class="imagen2">
            <div class="center">
                <div class="menu" style="display: flex; justify-content: space-between">
                    <a href="usuario.jsp" ><i class="material-icons" style="font-size:36px">keyboard_arrow_left</i></a>
                    <a href="index.jsp" ><i class="material-icons" style="font-size:30px; color:white  ">exit_to_app</i></a>
                </div>
                <h1 style="color:white">Actualizar Datos</h1>
                <h1>Informacion</h1>

                <form action="./RespuestaUsuario" method="POST">

                    <input type="hidden" name="usuario" value="${usuario}">
                    <input type="hidden" name="numeroLicencia" value="${numeroLicencia}">

                    <label for="nombre"> Nombre: </label>
                    <input type="text" name="nombre" id="nombre"> 
                    <br><br>

                    <label for="apellido"> Apellido: </label>
                    <input type="text" name="apellido" id="apellido"> 
                    <br><br>

                    <label for="contrasena"> Contraseña: </label>
                    <input type="password" name="contrasena" id="contrasena"> 
                    <br><br>

                    <label for="tipoDocumento"> Tipo de Documento: </label>
                    <select name="tipoDocumento" id="tipoDocumento">
                        <option> C.C </option>
                        <option> T.I</option>
                        <option> C.E</option>
                    </select> 
                    <br><br>

                    <label for="documentoIdentidad"> Documento de Identidad: </label>
                    <input type="text" name="documentoIdentidad" id="documentoIdentidad"> 
                    <br><br>

                    <label for="correo"> E-mail: </label>
                    <input type="email" name="correo" id="correo"> 
                    <br><br>

                    <label for="telefono"> Telefono: </label>
                    <input type="text" name="telefono" id="telefono"> 
                    <br><br>

                    <input type="submit" name="actualizarDatos" value="Actualizar Datos">

                </form>
            </div>
        </div>

    </body>
</html>