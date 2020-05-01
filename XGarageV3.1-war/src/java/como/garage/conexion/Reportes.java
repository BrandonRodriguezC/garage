/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package como.garage.conexion;

import com.garage.sessionBeans.ReservaFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Diego
 */
public class Reportes extends HttpServlet {

    @EJB
    private ReservaFacadeLocal reservaFacade;


    private RequestDispatcher reenvio;
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("reportesSecretariaTransportes") != null) {
            if (request.getParameter("reportesSecretariaTransportes").equals("Reporte Diario")) {
                reporteDiario(request, response);
            }else if (request.getParameter("reportesSecretariaTransportes").equals("Reporte por Intervalo")){
                reportePorIntervalo(request, response);
            }else if (request.getParameter("reportesSecretariaTransportes").equals("Reporte por Horario")){
                reportePorHorario(request, response);
            }
        }
    }

    /*El Metodo se encarga de realizar los reportes diarios segun los datos ingresados por el usuario */
    private void reporteDiario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fecha = request.getParameter("fecha_Diaria");
        String nombreParqueadero = request.getParameter("nombreParqueadero");
        String localidad = request.getParameter("localidad");
        
//        System.out.println(fecha + "   " + nombreParqueadero + "   " + localidad);
        
        if(fecha.equals("")){
            request.setAttribute("advertencia", "Para Realizar este reporte se requiere la fecha");
        }else{
            if (nombreParqueadero.equals("")) {
                if (localidad.equals("Todos")) {
                    request.setAttribute("reportes", reservaFacade.reporteTotal(fecha));
                } else {
                    request.setAttribute("reportes", reservaFacade.reporteLocalidad(localidad, fecha));
                }
            } else {
                request.setAttribute("reportes", reservaFacade.reportesParqueadero(nombreParqueadero, fecha));
            }
        }
        reenvio = request.getRequestDispatcher("reportes.jsp");
        reenvio.forward(request, response);
    }
    
    /*El Metodo se encarga de realizar los reportes por intervalos segun los datos ingresados por el usuario */
    private void reportePorIntervalo (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String fechaInicio = request.getParameter("fecha_Inicio");
        String fechaFin = request.getParameter("fecha_Fin");
        String localidad = request.getParameter("localidad");
        String nombreParqueadero = request.getParameter("nombreParqueadero");
        
        if(fechaInicio.equals("") || fechaFin.equals("")){
            request.setAttribute("advertencia", "Para Realizar este reporte se requiere el intervalo de fechas");
        }else{
            if (nombreParqueadero.equals("")) {
                if (localidad.equals("Todos")) {
                    request.setAttribute("reportes", reservaFacade.reporteTotal(fechaInicio, fechaFin));
                } else {
                    request.setAttribute("reportes", reservaFacade.reporteLocalidad(localidad, fechaInicio, fechaFin));
                }
            } else {
                request.setAttribute("reportes", reservaFacade.reportesParqueadero(nombreParqueadero, fechaInicio, fechaFin));
            }
        }
//        System.out.println(fechaInicio + "    "+ fechaFin + "    " + nombreParqueadero);
        reenvio = request.getRequestDispatcher("reportes.jsp");
        reenvio.forward(request, response);
    }
    
    /*El Metodo se encarga de realizar los reportes segun los horarios del dia (Mañana, Tarde, Noche y Madrugada) (FALTA COMPLEMENTARLO)*/
    private void reportePorHorario (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fecha = request.getParameter("fecha_Diaria");;
        String nombreParqueadero = request.getParameter("nombreParqueadero");
        
        if(fecha.equals("") || nombreParqueadero.equals("")){
            request.setAttribute("advertencia", "Para Realizar este reporte se requiere el nombre del establecimiento y la fecha");
        }else{
            List<Object[]> horarios = reservaFacade.reporteHorario(nombreParqueadero, fecha, "Madrugada");
            horarios.addAll(reservaFacade.reporteHorario(nombreParqueadero, fecha, "Mañana"));
            horarios.addAll(reservaFacade.reporteHorario(nombreParqueadero, fecha, "Tarde"));
            horarios.addAll(reservaFacade.reporteHorario(nombreParqueadero, fecha, "Noche"));      
            request.setAttribute("reportes", horarios);
            
        }
        reenvio = request.getRequestDispatcher("reportes.jsp");
        reenvio.forward(request, response);
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
