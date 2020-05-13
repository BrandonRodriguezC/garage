<%-- 
    Document   : reportes
    Created on : 28/03/2020, 02:24:20 PM
    Author     : Diego
--%>

<%@page import="com.garage.datos.Operaciones"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es" xml:lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assests/styles.css">
        <!--Graficos-->
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>

        <title>Reportes</title>
    </head>
    <body >

        <%  Operaciones ope = new Operaciones();
            String localidades[] = ope.getLocalidades();
        %>

        <div class="imagen2">
            <div class="center">

                <h1>Reportes</h1>

                <p>${advertencia}</p>
                <br><br>

                <h2>Reporte Diario</h2>
                <div class="flex" style="height: 70vh">
                    <div  >
                        <table class="tableQ">
                            <caption>Tabla Reporte Diario</caption>
                            <th scope="col">NombreParqueadero</th>
                            <th scope="col">Cantidad de Reservas</th>
                                <c:forEach items="${reporteDiario}" var="reporte">
                                <tr>
                                    <td>${reporte[0]}</td>
                                    <td>${reporte[1]}</td>
                                </tr>
                            </c:forEach> 
                        </table>
                    </div>
                    Grafica Reporte Diario 
                    <div id="reporteDiario" ></div>
                </div>


                <form action="./Reportes" method="GET">

                    <label for="localidad"> Localidad: </label>
                    <select name="localidad" id="localidad">
                        <%for (int i = 0; i < localidades.length; i++) {
                                if (i == 0) {
                                    out.println("<option>Todos</option>");
                                } else {
                                    out.println("<option>" + i + " " + localidades[i] + "</option>");
                                }
                            }
                        %>
                    </select>
                    <br><br>
                    <label for="fecha_Diaria">Fecha</label>
                    <input type="datetime-local" id="fecha_Diaria" name="fecha_Diaria" 
                           min="2020-04-00T00:00" max="2020-12-31T23:00">

                    <input type="submit" name="reportesSecretariaTransportes" value="Reporte Diario" >
                </form>

                <br><br>

                <h2>Reporte por Parqueadero</h2>

                <table class="tableQ">
                    <caption>Tabla Reporte por Horario</caption>
                    <th scope="col">Tipo Vehiculo</th>
                    <th scope="col">Cantidad de Reservas</th>
                    <th scope="col">Horario</th>
                        <c:forEach items="${reporteHorario}" var="reporte">
                        <tr>
                            <td>${reporte[0]}</td>
                            <td>${reporte[1]}</td>
                            <td>${reporte[2]}</td>
                        </tr>
                    </c:forEach> 
                </table>
                <!--Graficas por Horario-->

                <div id="reporteMadrugada" ></div>
                <label for="reporteMadrugada"> Madrugada</label>
                <div id="reporteManaina" ></div>
                <label for="reporteManaina"> Mañana</label>
                <div id="reporteTarde" ></div>
                <label for="reporteTarde"> Tarde</label>
                <div id="reporteNoche" ></div>
                <label for="reporteNoche"> Noche</label>

                <table class="tableQ">
                    <caption>Tabla Reporte por Plaza</caption>
                    <th scope="col">Tipo de Vehiculo</th>
                    <th scope="col">Reservas</th>
                        <c:forEach items="${reportePlaza}" var="reporte">
                        <tr>
                            <td>${reporte[0]}</td>
                            <td>${reporte[1]}</td>
                        </tr>
                    </c:forEach> 
                </table>
                <!--Grafica Reporte por Plaza-->
                <div id="reportePlaza" ></div>



                <form action="./Reportes" method="GET">

                    <label for="parqueadero">Parqueadero</label>
                    <input type="text" name="nombreParqueadero">
                    <br><br>

                    <label for="fecha_Diaria">Fecha</label>
                    <input type="datetime-local" id="fecha_Diaria" name="fecha_Diaria" 
                           min="2020-04-00T00:00" max="2020-12-31T23:00">
                    <br><br>

                    <input type="submit" name="reportesSecretariaTransportes" value="Reporte por Plazas">
                    <input type="submit" name="reportesSecretariaTransportes" value="Reporte por Horario">
                </form>

                <h2>Reporte de Parqueadero por Intervalo</h2>

                <table class="tableQ">
                    <caption>Tabla Reporte por Intervalo</caption>
                    <th scope="col">Nombre</th>
                    <th scope="col">Cantidad de Reservas</th>
                        <c:forEach items="${reporteIntervalo}" var="reporte">
                        <tr>
                            <td>${reporte[0]}</td>
                            <td>${reporte[1]}</td>
                        </tr>
                    </c:forEach> 
                </table>

                <!--Grafica-->
                <div id="reporteIntervalo" ></div>


                <form action="./Reportes" method="GET">
                    <label for="parqueadero">Parqueadero</label>
                    <input type="text" name="nombreParqueadero">
                    <br><br>

                    <p>Reporte de: </p>
                    <input type="datetime-local" id="fecha_Inicio" name="fecha_Inicio" 
                           min="2020-04-00T00:00" max="2020-12-31T23:00"> 
                    <p> a </p>
                    <input type="datetime-local" id="fecha_Fin" name="fecha_Fin" 
                           min="2020-04-00T00:00" max="2020-12-31T23:00">

                    <br><br>
                    <label for="localidad"> Localidad: </label>
                    <select name="localidad" id="localidad">
                        <%
                            for (int i = 0; i < localidades.length; i++) {
                                if (i == 0) {
                                    out.println("<option>Todos</option>");
                                } else {
                                    out.println("<option>" + i + " " + localidades[i] + "</option>");
                                }
                            }
                        %>

                    </select>

                    <input type="submit" name="reportesSecretariaTransportes" value="Reporte por Intervalo">
                </form>



            </div>
        </div>
        <br>

        <script src="assests/graficos.js"></script>
        <script>
            <%
                String ds = "";
                String mad = "", man = "", tar = "", noc = "";
                String tipo = "";

                if (session.getAttribute("tipoReporte") != null) {
                    List<Object[]> datos;
                    tipo = session.getAttribute("tipoReporte").toString();
                    if (tipo.equals("reporteHorario")) {
                        datos = (List<Object[]>) session.getAttribute("reporteHorarioMad");
                        mad = ope.transformarLista(datos);
                        datos = (List<Object[]>) session.getAttribute("reporteHorarioMan");
                        man = ope.transformarLista(datos);
                        datos = (List<Object[]>) session.getAttribute("reporteHorarioTar");
                        tar = ope.transformarLista(datos);
                        datos = (List<Object[]>) session.getAttribute("reporteHorarioNoc");
                        noc = ope.transformarLista(datos);
                    } else {
//                    System.out.println(session.getAttribute("tipoReporte").toString());
                        datos = (List<Object[]>) session.getAttribute(tipo);
                        ds = ope.transformarLista(datos);
                        session.removeAttribute(tipo);
                    }

                }
            %>

            var tipo = "<%= tipo%>";
            if (tipo !== "") {
                if (tipo === 'reporteHorario') {
                    var mad = "<%= mad%>", man = "<%= man%>", tar = "<%= tar%>", noc = "<%= noc%>";
                    cambiarReporteHorario(transfomarJson(transformarString(mad)),
                            transfomarJson(transformarString(man)),
                            transfomarJson(transformarString(tar)),
                            transfomarJson(transformarString(noc)));
                } else {
                    var datos = "<%= ds%>";
                    if (tipo === 'reporteDiario') {
                        cambiarReporteDiario(transfomarJson(transformarString(datos)));
                    } else if (tipo === 'reportePlaza') {
                        cambiarReportePlaza(transfomarJson(transformarString(datos)));
                    } else if (tipo === 'reporteIntervalo') {
                        cambiarReportePorIntervalo(transfomarJson(transformarString(datos)));
                    }

                }

                //console.log(matriz);
            }

        </script>

    </body>
</html>