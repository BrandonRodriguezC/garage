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
    <body style="background-color: #fafafa">

        <%
            Operaciones ope = new Operaciones();
            String localidades[] = ope.getLocalidades();
            String tipoParqueadero[] = ope.getTipoParqueadero();
        %>

        <div id="menu" >
            <table style="width: 100vw">
                <tr>
                    <th style="width: 150px"><div class="logo" onclick="location.href = 'index.jsp';"></div>
                    </th>
                    <th>Registro</th>
                </tr>
            </table>  
        </div>
        <div class="middle flex">
            <div class="images flex">
                <div id="image-user"></div>
                <div id="image-park"></div>
            </div>


            <div class="formularios">
                <div class ="flex" >
                    <div style="margin:5px">
                        <h1>Registro</h1>
                    </div>

                     <div style="margin:5px">
                        <h2 id="userF" onclick="parkForm()">Usuario</h2>
                        <h2 id="parkF" onclick="userForm()">Parqueadero </h2>
                    </div>

                </div>

                <div class ="flex">
                    <div id="register-user">

                        <form action="./SolicitudIngreso" method="POST" >
                            <table>
                                <tr>
                                    <th><label for="numeroLicencia">Numero Licencia</label></th>
                                    <th><input type="text" name="numeroLicencia" id="numeroLicencia" maxlength="20" required></th>
                                </tr>
                                <tr>
                                    <th><label for="usuario"> Usuario: </label></th>
                                    <th><input type="text" name="usuario" id="usuario" maxlength="30" required></th>
                                </tr>
                                <tr>
                                    <th><label for="contrasena"> Contraseña: </label></th>
                                    <th><input type="password" name="contrasena" id="contrasena" maxlength="30" required></th>
                                </tr>
                                <tr>
                                    <th><label for="nombre"> Nombre: </label></th>
                                    <th><input type="text" name="nombre" id="nombre"  maxlength="50" required></th>
                                </tr> 
                                <tr>
                                    <th><label for="apellido"> Apellido: </label></th>
                                    <th><input type="text" name="apellido" id="apellido" maxlength="50" required> </th>
                                </tr>
                                <tr>
                                    <th><label for="tipoDocumento"> Tipo de Documento: </label></th>
                                    <th><select name="tipoDocumento" id="tipoDocumento" required>
                                            <option> C.C </option>
                                            <option> T.I</option>
                                            <option> C.E</option>
                                        </select></th>
                                </tr>
                                <tr>
                                    <th><label for="documentoIdentidad"> Documento de Identidad: </label></th>
                                    <th><input type="text" name="documentoIdentidad" id="documentoIdentidad" maxlength="20" ></th>
                                </tr>
                                <tr>
                                    <th><label for="correo"> E-mail: </label></th>
                                    <th><input type="email" name="correo" id="correo" maxlength="50"></th>
                                </tr>
                                <tr>
                                    <th><label for="telefono"> Telefono: </label></th>
                                    <th><input type="text" name="telefono" id="telefono" maxlength="10" ></th>
                                </tr>
                                <tr>
                                    <th></th>
                                    <th><input type="submit" name="registro" value="Registrar Usuario"></th>
                                </tr>
                            </table>    
                            <input type="hidden" name="tipoCliente" value="usuario">

                        </form>
                    </div>
                    <div id="register-park" >

                        <form action="./SolicitudIngreso" method="POST">
                            <input type="hidden" name="formato" value="parqueadero">
                            <table>
                                <tr>
                                    <th><label for="nit">Nit</label></th>
                                    <th><input type="text" name="nit" id="nit" maxlength="20" required> </th>
                                </tr>
                                <tr>
                                    <th><label for="usuario"> Usuario: </label></th>
                                    <th><input type="text" name="usuario" id="usuario" maxlength="30" required></th>
                                </tr>
                                <tr>
                                    <th><label for="contrasena"> Contraseña: </label></th>
                                    <th><input type="password" name="contrasena" id="contrasena" maxlength="30" required></th>
                                </tr>
                                <tr>
                                    <th><label for="nombreParqueadero"> Nombre Parqueadero: </label></th>
                                    <th><input type="text" name="nombreParqueadero" id="nombreParqueadero" maxlength="20" required></th>
                                </tr>
                                <tr>
                                    <th><label for="ciudad"> Ciudad: </label></th>
                                    <th><select name="ciudad" id="ciudad">
                                            <option> Bogota D.C </option>
                                        </select></th>
                                </tr>
                                <tr>
                                    <th><label for="direccion"> Dirección: </label></th>
                                    <th><input type="text" name="direccion" id="direccion" maxlength="30" required></th>
                                </tr>
                                <tr>
                                    <th><label for="tipo_Parqueadero"> Tipo de Parqueadero: </label></th>
                                    <th><select name="tipo_Parqueadero" id="tipo_Parqueadero">
                                            <%
                                                for (int i = 0; i < tipoParqueadero.length; i++) {
                                                    out.println("<option>" + tipoParqueadero[i] + "</option>");
                                                }
                                            %>
                                        </select></th>
                                </tr>
                                <tr>
                                    <th><label for="localidad"> Localidad: </label></th>
                                    <th><select name="localidad" id="localidad">
                                            <%
                                                for (int i = 0; i < localidades.length; i++) {
                                                    out.println("<option>" + (i + 1) + " " + localidades[i] + "</option>");
                                                }
                                            %>
                                        </select></th>
                                </tr>
                                <tr>
                                    <th><label for="latitud"> Latitud: </label></th>
                                    <th><input type="text" name="latitud" id="latitud" maxlength="52" ></th>
                                </tr>
                                <tr>
                                    <th><label for="longitud"> Longitud: </label></th>
                                    <th><input type="text" name="longitud" id="longitud" maxlength="52" ></th>
                                </tr>
                                <tr>
                                    <th></th>
                                    <th><input type="submit" name="registro" value="Registrar Parqueadero"></th>
                                </tr>
                            </table>
                            <input type="hidden" name="tipoCliente" value="parqueadero">
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </body>
    <script src="assests/changes.js"></script>
</html>