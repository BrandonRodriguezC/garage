/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package como.garage.conexion;

import com.garage.entities.Precio;
import com.garage.sessionBeans.PrecioFacadeLocal;
import com.garage.sessionBeans.ReservaFacadeLocal;
import java.io.IOException;
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
    private PrecioFacadeLocal precioFacade;

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
            }else if ((request.getParameter("reportesSecretariaTransportes").equals("Reporte por Plazas"))){
                reportePorPlazas(request, response);
            }
        }else if (request.getParameter("actualizarPrecios") != null){
            if (request.getParameter("actualizarPrecios").equals("Actualizar Precios")){
                actualizarPrecios(request, response);
            }
        }
    }

    /*El Metodo se encarga de realizar los reportes diarios segun los datos ingresados por el usuario */
    private void reporteDiario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fecha = request.getParameter("fecha_Diaria");
        String localidad = request.getParameter("localidad");
        
        if(fecha.equals("")){
            request.getSession().setAttribute("advertencia", "Para Realizar este reporte se requiere la fecha");
        }else{
            if (localidad.equals("Todos")) {
                request.getSession().setAttribute("reporteDiario", reservaFacade.reporteTotal(fecha));
            } else {
                request.getSession().setAttribute("reporteDiario", reservaFacade.reporteLocalidad(localidad, fecha));
            }
            request.getSession().setAttribute("tipoReporte", "reporteDiario");
        }
        
        reenvio = request.getRequestDispatcher("reportes.jsp");
        reenvio.forward(request, response);
    }
    
    private void reportePorPlazas (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreParqueadero = request.getParameter("nombreParqueadero");
        String fecha = request.getParameter("fecha_Diaria");
        
        if(!fecha.equals("") || !nombreParqueadero.equals("")){
            request.getSession().setAttribute("reportePlaza", reservaFacade.reportesParqueadero(nombreParqueadero, fecha));
            request.getSession().setAttribute("tipoReporte", "reportePlaza");
        }else{
            request.setAttribute("advertencia", "Para Realizar el Reporte por Plazas se requiere el nombre del establecimiento y la fecha");
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
        
        if(!fechaInicio.equals("") || ! fechaFin.equals("")){
            if (nombreParqueadero.equals("")) {
                if (localidad.equals("Todos")) {
                    request.getSession().setAttribute("reporteIntervalo", reservaFacade.reporteTotal(fechaInicio, fechaFin));
                } else {
                    request.getSession().setAttribute("reporteIntervalo", reservaFacade.reporteLocalidad(localidad, fechaInicio, fechaFin));
                }
            } else {
                request.getSession().setAttribute("reporteIntervalo", reservaFacade.reportesParqueadero(nombreParqueadero, fechaInicio, fechaFin));
            }
            request.getSession().setAttribute("tipoReporte", "reporteIntervalo");
        }else{
            request.setAttribute("advertencia", "Para realizar el reporte por intervalo debe ingresar las fechas");
        }
        reenvio = request.getRequestDispatcher("reportes.jsp");
        reenvio.forward(request, response);
    }
    
    /*El Metodo se encarga de realizar los reportes segun los horarios del dia (Mañana, Tarde, Noche y Madrugada) (FALTA COMPLEMENTARLO)*/
    private void reportePorHorario (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fecha = request.getParameter("fecha_Diaria");
        String nombreParqueadero = request.getParameter("nombreParqueadero");
        
        if(!fecha.equals("") || !nombreParqueadero.equals("")){
            request.getSession().setAttribute("reporteHorarioMad",reservaFacade.reporteHorario(nombreParqueadero, fecha, "Madrugada"));
            request.getSession().setAttribute("reporteHorarioMan", reservaFacade.reporteHorario(nombreParqueadero, fecha, "Mañana"));
            request.getSession().setAttribute("reporteHorarioTar", reservaFacade.reporteHorario(nombreParqueadero, fecha, "Tarde"));
            request.getSession().setAttribute("reporteHorarioNoc", reservaFacade.reporteHorario(nombreParqueadero, fecha, "Noche")); 
            request.getSession().setAttribute("tipoReporte", "reporteHorario");
            
        }else{
            request.setAttribute("advertencia", "Para Realizar el Reporte por Horario se requiere el nombre del establecimiento y la fecha");
        }
        
        
        reenvio = request.getRequestDispatcher("reportes.jsp");
        reenvio.forward(request, response);
    }

    public void actualizarPrecios (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipoPrecio = request.getParameter("tipoPrecio");
        String precioMinuto = request.getParameter("precioMinuto");
        String precioHora = request.getParameter("precioHora");
        
        if(!precioMinuto.equals("") && !precioHora.equals("")){
            Precio precio = precioFacade.find(tipoPrecio);
            if (precio != null) {
                precio.setPreciominuto(Double.parseDouble(precioMinuto));
                precio.setPreciohora(Double.parseDouble(precioHora));
                
                precioFacade.edit(precio);
            }
        }else{
            request.setAttribute("reporte", "No han sido ingresados");
        }
        reenvio = request.getRequestDispatcher("actualizarPrecios.jsp");
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
