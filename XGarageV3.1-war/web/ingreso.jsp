<%-- 
    Document   : ingresro
    Created on : 25/03/2020, 09:32:47 AM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" xml:lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assests/styles.css">
        <title>Ingreso</title>
    </head>
    <body >

        <div class="flex imagen">
            <div class="left-panel">
                <div class="content-index">
                    <h1>Ingreso</h1>
                    <form action="./SolicitudIngreso" method="POST">
                        <input type="text" name="usuario" placeholder="Usuario *" required><br>
                        <input type="password" name="contrasena" placeholder="Contraseña *" required><br>
                        <input type="submit" name="ingreso" value="Ingresar">
                    </form>
                    <a href="registro.jsp">Registro</a>
                    <a href="cambioContrasena.jsp">Cambio de Contraseña</a>
                </div>
            </div>
            
        </div>
    </body>
</html>