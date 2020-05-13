/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package como.garage.conexion;

import com.garage.entities.Parqueadero;
import com.garage.entities.Plaza;
import com.garage.entities.Reserva;
import com.garage.entities.ReservaPK;
import com.garage.entities.Usuario;
import com.garage.sessionBeans.ParqueaderoFacadeLocal;
import com.garage.sessionBeans.PlazaFacadeLocal;
import com.garage.sessionBeans.ReservaFacadeLocal;
import com.garage.sessionBeans.UsuarioFacadeLocal;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class RespuestaParqueadero extends HttpServlet {

    @EJB
    private ReservaFacadeLocal reservaFacade;

    @EJB
    private PlazaFacadeLocal plazaFacade;

    @EJB
    private ParqueaderoFacadeLocal parqueaderoFacade;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    

    

    private Parqueadero park;
    private Usuario user;
    private Plaza plaza;
    private ReservaPK llavePrimaria;
    private Reserva reserva;

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
        if (request.getParameter("agregarPlaza") != null) {
            if (request.getParameter("agregarPlaza").equals("Agregar Plaza")) {
                agregarPlaza(request, response);
                request.setAttribute("plazas", plazaFacade.obtenerPlazas(request.getParameter("usuario")));
                reenvio = request.getRequestDispatcher("actualizarDatosParqueadero.jsp");
                reenvio.forward(request, response);
            }

        } else if (request.getParameter("actualizarDatos") != null) {
            if (request.getParameter("actualizarDatos").equals("Actualizar Datos")) {
                actualizarDatos(request, response);
                reenvio = request.getRequestDispatcher("actualizarDatosParqueadero.jsp");
                reenvio.forward(request, response);
            }

        } else if (request.getParameter("actualizarPlazas") != null) {
            if (request.getParameter("actualizarPlazas").equals("Actualizar Plazas")) {
                actualizarPlazas(request, response);
            }

        } else if (request.getParameter("eliminarCuenta") != null) {
            if (request.getParameter("eliminarCuenta").equals("Eliminar Cuenta")) {
                eliminarCuenta(request, response);
                reenvio = request.getRequestDispatcher("index.jsp");
                reenvio.forward(request, response);
            }

        } else if (request.getParameter("reservasActivas") != null) {
            if (request.getParameter("reservasActivas").equals("Actualizar Reservas")) {
                reservasActivas(request, response);
            }
        } else if (request.getParameter("plazasDisponibles") != null) {
            if (request.getParameter("plazasDisponibles").equals("Plazas Disponibles")) {
                plazasDisponibles(request, response);
            }

        } else if (request.getParameter("adquirirPlaza") != null) {
            if (request.getParameter("adquirirPlaza").equals("Adquirir Plaza")) {
                adquirirPlaza(request, response);
                reenvio = request.getRequestDispatcher("parqueadero.jsp");
                reenvio.forward(request, response);
            }
        } else if (request.getParameter("eliminarPlaza") != null) {
            if (request.getParameter("eliminarPlaza").equals("Eliminar Plaza")) {
                eliminarPlaza(request, response);
                reenvio = request.getRequestDispatcher("actualizarDatosParqueadero.jsp");
                reenvio.forward(request, response);
            }
        } else if (request.getParameter("ingresarVehiculoReserva") != null) {
            if (request.getParameter("ingresarVehiculoReserva").equals("Ingresar Vehiculo")) {
                ingresarVehiculoReserva(request, response);
                reservasActivas(request, response);
            }
        } else if (request.getParameter("retirarVehiculoReserva") != null) {
            if (request.getParameter("retirarVehiculoReserva").equals("Retirar Vehiculo")) {
                retirarVehiculoReserva(request, response);
                reservasActivas(request, response);
            }
        } else if (request.getParameter("historial") != null) {
            if (request.getParameter("historial").equals("Historial")) {
                historial(request, response);

            }
        } else if (request.getParameter("penalizar") != null) {
            if (request.getParameter("penalizar").equals("Penalizar")) {
                penalizar(request, response);
                reservasActivas(request, response);
            }
        } else if (request.getParameter("pendientes") != null) {
            if (request.getParameter("pendientes").equals("Pendientes")) {
                pendientes(request, response);
            }
        }

    }

    /*El Metodo se encarga de agregar una nueva plaza dentro de la base de datos*/
    private String agregarPlaza(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nit = request.getParameter("nit");
        String plazaId = request.getParameter("nombrePlaza") + "-" + nit;
        String tipoDeAuto = request.getParameter("tipoPlaza");
        // Boolean disponibilidad = request.getParameter("disponibilidad").equals("True") ? true : false;

        if (plazaFacade.find(plazaId) == null) {
            plaza = new Plaza(parqueaderoFacade.find(nit), plazaId, tipoDeAuto);
            plazaFacade.create(plaza);
            return "La plaza: " + plazaId + " ha sido creada con exito";
        }
        return "La plaza: " + plazaId + " ya ha sido creada";
    }

    /**
     * El Metodo se encarga de actualizar los datos del parqueadero (Hay que
     * agregar latitud, longitud y tipo de Parqueadero)
     *
     */
    private String actualizarDatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String nit = request.getParameter("nit");
        String contrasena = request.getParameter("contrasena");
        String nombre = request.getParameter("nombre");
        String ciudad = request.getParameter("ciudad");
        String direccion = request.getParameter("direccion");
        String localidad = request.getParameter("localidad");
        String latitud = request.getParameter("latitud");
        String longitud = request.getParameter("longitud");
        park = parqueaderoFacade.find(nit);
        user = usuarioFacade.find(usuario);
        if (!contrasena.equals("")) {
            user.setContrasena(contrasena);
            usuarioFacade.edit(user);
        }
        if (!nombre.equals("")) {
            park.setNombre(nombre);
        }
        if (!ciudad.equals("")) {
            park.setCiudad(ciudad);

        }
        if (!direccion.equals("")) {
            park.setDireccion(direccion);
        }
        if (!latitud.equals("")) {
            park.setLatitud(Double.parseDouble(latitud));
        }
        if (!longitud.equals("")) {
            park.setLongitud(Double.parseDouble(longitud));
        }
        park.setLocalidad(localidad);
        parqueaderoFacade.edit(park);

        return "Los datos han sido actualizados exitosamente";
    }

    /*El Metodo se encarga de retornar todas las plazas del parqueadero*/
    private void actualizarPlazas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("plazas", plazaFacade.obtenerPlazas(request.getParameter("nit")));
        reenvio = request.getRequestDispatcher("actualizarDatosParqueadero.jsp");
        reenvio.forward(request, response);
    }

    /*El Metodo se encarga de eliminar una plaza dada por el usuario*/
    private String eliminarPlaza(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombrePlaza = request.getParameter("nombrePlaza");
        String nit = request.getParameter("nit");
        if (!nombrePlaza.equals("")) {
            String plazaId = nombrePlaza + "-" + nit;
            if (plazaFacade.eliminarPlaza(plazaId)) {
                return "Plaza Eliminada";
            }
        }
        return "Plaza no encontrada";
    }

    /*El Metodo se encarga de eliminar todos los datos del parqueadero (plazas,reservas, ubicacion y usuario)*/
    private String eliminarCuenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String nit = request.getParameter("nit");
        parqueaderoFacade.remove(nit);
        usuarioFacade.remove(usuario);
        return "Parqueadero Eliminado Exitosamente";
    }

    /*El Metodo se encarga de retornar las reservas que se encuentran disponibles (Hay que agregar fechas para filtrado)*/
    private void reservasActivas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nit = request.getParameter("nit");
        String fecha = request.getParameter("fecha");

        if (fecha.equals("")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            Date date = ts;
            fecha = formatter.format(date);
        }

        List<Object[]> lista = parqueaderoFacade.reservasActivas(nit, fecha);
        List<String> color = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = ts;
        String ya = formatter.format(date);
        Date fechaActual = transformacionFecha(ya);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i)[4] == null) {
                Date fechaEntrada = transformacionFecha(lista.get(i)[3].toString());
                long diferencia = fechaActual.getTime() - fechaEntrada.getTime();
                long minutos = diferencia / (60 * 1000);
                if (minutos > 30) {
                    color.add("red");
                }
            }else{
            color.add("black");
            }
        }
        
        request.setAttribute("reservasActivas", lista);
        request.setAttribute("color", color);
        request.setAttribute("filterV", "none");
        
        reenvio = request.getRequestDispatcher("parqueadero.jsp");
        reenvio.forward(request, response);
    }

    private Date transformacionFecha(String fecha) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            return format.parse(fecha);
        } catch (ParseException ex) {
            return new Date();
        }
    }

    /*El Metodo se encarga de retornar las plazas disponibles en el momento de la solicitud*/
    private void plazasDisponibles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("plazasDisponibles", parqueaderoFacade.plazasDisponibles(request.getParameter("nit"),
                request.getParameter("tipoDeAuto")));
        request.setAttribute("filterV", "none");
        reenvio = request.getRequestDispatcher("parqueadero.jsp");
        reenvio.forward(request, response);
    }

    /*
    El Metodo se encarga de ingresar un auto el cual no ha reservado (Corregirlo "Agregar hora de entrada")
    REVISAR----- CAMBIO DE USO EN PLAZA (DISPONIBILIDAD)
     */
    private String adquirirPlaza(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("plazaId") != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            Date date = ts;
            String fechaReserva = formatter.format(date);
            String plazaId = request.getParameter("plazaId");
            String placa = request.getParameter("placa");
            String modelo = request.getParameter("modelo");
            llavePrimaria = new ReservaPK(fechaReserva, plazaId);
            reserva = new Reserva(llavePrimaria, null, modelo, placa, true, fechaReserva);
            reservaFacade.create(reserva);
            return "El codigo de acceso es: " + plazaId + "/" + fechaReserva;
        }
        return "Favor Elegir Plaza";
    }

    private void ingresarVehiculoReserva(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("reservaActivaSeleccionada") != null) {
            String arreglo[] = request.getParameter("reservaActivaSeleccionada").split("/");
            String id = arreglo[0];
            String fecha = arreglo[1];
            String color = arreglo[2];
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            Date date = ts;

            String fechaIngreso = formatter.format(date);
            ReservaPK re = new ReservaPK(fecha, id);
            reserva = reservaFacade.find(re);
            if (reserva.getFechaentrada() == null && !color.equals("red")) {
                reserva.setFechaentrada(fechaIngreso);
                reservaFacade.edit(reserva);
            }
        }
    }

    private void retirarVehiculoReserva(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("reservaActivaSeleccionada") != null){
            String areglo[] = request.getParameter("reservaActivaSeleccionada").split("/");

            String id = areglo[0];
            String fecha = areglo[1];
            String placa = areglo[3];
            String entrada = areglo[4];

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            Date date = ts;

            String fechaSalida = formatter.format(date);

            ReservaPK re = new ReservaPK(fecha, id);
            reserva = reservaFacade.find(re);
            double costo = 0;
            if (reserva.getFechaentrada() != null) {
                if (reserva.getFechasalida() == null) {
                    reserva.setFechasalida(fechaSalida);
                    reservaFacade.edit(reserva);
                    costo = reservaFacade.costo(new ReservaPK(fecha, id), "facturar");
                    reserva.setValor(costo);
                    reserva.setActivo(false);
                    reservaFacade.edit(reserva);
                }
            }
            request.setAttribute("filterV", "block");
            request.setAttribute("popUpH", "Factura Exitosa");
            request.setAttribute("popUpD", "Placa: " + placa + "<br/>" + "Fecha de entrada: " + entrada + "<br/>" + "Fecha de salida: " + fechaSalida + "<br/>" + "Costo: " + costo);
        }
    }

    private void historial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nit = request.getSession().getAttribute("nit").toString();
        List<Object[]> resultado = reservaFacade.historialParqueadero(nit);
        request.setAttribute("reservasActivas", resultado);
        request.setAttribute("filterV", "none");
        reenvio = request.getRequestDispatcher("parqueadero.jsp");
        reenvio.forward(request, response);
        
    }

    private void pendientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nit = request.getSession().getAttribute("nit").toString();
        List<Object[]> lista = reservaFacade.pendientesParqueadero(nit);
        
        List<String> color = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = ts;
        String ya = formatter.format(date);
        Date fechaActual = transformacionFecha(ya);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i)[4] == null) {
                Date fechaEntrada = transformacionFecha(lista.get(i)[3].toString());
                long diferencia = fechaActual.getTime() - fechaEntrada.getTime();
                long minutos = diferencia / (60 * 1000);
                if (minutos > 30) {
                    color.add("red");
                }
            }else{
            color.add("black");
            }
        }
        request.setAttribute("reservasActivas", lista);
        request.setAttribute("color", color);
        request.setAttribute("filterV", "none");
        reenvio = request.getRequestDispatcher("parqueadero.jsp");
        reenvio.forward(request, response);
    }

    private void penalizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("reservaActivaSeleccionada") != null){
            String arreglo[] = request.getParameter("reservaActivaSeleccionada").split("/");
            String id = arreglo[0];
            String fecha = arreglo[1];
            String color = arreglo[2];

            if (color.equals("red")) {
                Reserva re = reservaFacade.find(new ReservaPK(fecha, id));
                re.setFechaentrada("PENALIZADO");
                re.setFechasalida("PENALIZADO");
                re.setActivo(Boolean.FALSE);
                re.setValor(reservaFacade.costo(new ReservaPK(fecha, id), "penalizar"));
                reservaFacade.edit(re);
            }
        }
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