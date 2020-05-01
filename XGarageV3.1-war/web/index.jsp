<%-- 
    Document   : index
    Created on : 25/03/2020, 09:32:07 AM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assests/styles.css">
        <title>Garage</title>
    </head>
    <body >
        <% 
            session.invalidate();
        %>
        <div class="flex imagen">
        <div class="left-panel">
            <div class="content-index">
                <h1 style="font-size:56px">garage</h1>
            <p>No te preocupes...<br>
                Te encontraremos un estacionamiento de manera sencilla, segura y rapida</p>
            <a href="ingreso.jsp" style="font-size: 20px; margin-top: 10px">Ingreso</a>
            <a href="reportes.jsp" style="font-size: 19px; margin-top: 10px">Reportes</a>
            </div>
            
        </div>
            <div>
                
            </div>
            </div>
    </body>
</html>