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
    <body >

        <div class="imagen2">
            <div class="center"><h1 >Cambio de Contraseña</h1>
                <br>
                <form action="./SolicitudIngreso" method="POST">
                    <label for="usuario">Usuario</label>
                    <input type="text" name="usuario" id="usuario" required>
                    <br><br>
                    <label for="actualContrasena">Contraseña Actual</label>
                    <input type="password" name="actualContrasena" id="actualContrasena" required>
                    <br><br>
                    <label for="nuevaContrasena">Nueva Contraseña</label>
                    <input type="password" name="nuevaContrasena" id="nuevaContrasena" required>
                    <br><br>
                    <label for="confirmacionNuevaContrasena">Confirmar Nueva Contraseña</label>
                    <input type="password" name="confirmacionNuevaContrasena" id="confirmacionNuevaContrasena" required>
                    <br><br>

                    <input type="submit" name="cambioContrasena" value="Cambiar Contraseña">
                </form>
            </div>
        </div>

    </body>
</html>