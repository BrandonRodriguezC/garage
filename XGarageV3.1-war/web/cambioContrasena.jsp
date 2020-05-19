<%-- 
    Document   : cambioContrasena
    Created on : 25/03/2020, 09:33:47 AM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" xml:lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assests/styles.css">
        <title>Cambio Contraseña</title>
    </head>
    <body style="background: #fafafa">
        <div id="menu" >
            <table style="width: 100vw">
                <tr>
                    <th style="width: 150px"><div class="logo" onclick="location.href = 'index.jsp';"></div>
                    </th>
                    <th>Cambiar Contraseña</th>
                </tr>
            </table>  
        </div>

        <div class="middle flex">

            <div id="image-pass"></div>
            <div id="register-pass">
                <h1>Cambio de Contraseña</h1>
                <form action="./SolicitudIngreso" method="POST">
                    <table>
                        <tr>
                            <th><label for="usuario">Usuario</label></th>
                            <th> <input type="text" name="usuario" id="usuario" required></th>
                        </tr>
                        <tr>
                            <th><label for="actualContrasena">Contraseña Actual</label></th>
                            <th><input type="password" name="actualContrasena" id="actualContrasena" required></th>
                        </tr>
                        <tr>
                            <th><label for="nuevaContrasena">Nueva Contraseña</label></th>
                            <th><input type="password" name="nuevaContrasena" id="nuevaContrasena" required></th>
                        </tr>
                        <tr>
                            <th><label for="confirmacionNuevaContrasena">Confirmar Nueva Contraseña</label></th>
                            <th><input type="password" name="confirmacionNuevaContrasena" id="confirmacionNuevaContrasena" required></th>
                        </tr>
                        <tr>
                            <th></th>
                            <th><input type="submit" name="cambioContrasena" value="Cambiar Contraseña"></th>
                        </tr>
                    </table>
                </form>
            </div>
        </div>


    </body>
</html>