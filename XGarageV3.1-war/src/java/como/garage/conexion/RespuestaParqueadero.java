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
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private UsuarioFacadeLocal usuarioFacade;

    @EJB
    private ParqueaderoFacadeLocal parqueaderoFacade;

    @EJB
    private PlazaFacadeLocal plazaFacade;

    
    
    
    
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
                System.out.println(agregarPlaza(request, response));
                request.setAttribute("plazas", plazaFacade.obtenerPlazas(request.getParameter("usuario")));
                reenvio = request.getRequestDispatcher("actualizarDatosParqueadero.jsp");
                reenvio.forward(request, response);
            }

        } else if (request.getParameter("actualizarDatos") != null) {
            if (request.getParameter("actualizarDatos").equals("Actualizar Datos")) {
                System.out.println(actualizarDatos(request, response));
                reenvio = request.getRequestDispatcher("actualizarDatosParqueadero.jsp");
                reenvio.forward(request, response);
            }

        } else if (request.getParameter("actualizarPlazas") != null) {
            if (request.getParameter("actualizarPlazas").equals("Actualizar Plazas")) {
                actualizarPlazas(request, response);
            }

        } else if (request.getParameter("eliminarCuenta") != null) {
            if (request.getParameter("eliminarCuenta").equals("Eliminar Cuenta")) {
                System.out.println(eliminarCuenta(request, response));
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
                System.out.println(adquirirPlaza(request, response));
                reenvio = request.getRequestDispatcher("parqueadero.jsp");
                reenvio.forward(request, response);
            }
//        } else if (request.getParameter("retirarAuto") != null) {
//            if (request.getParameter("retirarAuto").equals("Retirar Auto")) {
//                System.out.println(retirarAuto(request, response));
//                reenvio = request.getRequestDispatcher("parqueadero.jsp");
//                reenvio.forward(request, response);
//            }
//        }
        }else if (request.getParameter("eliminarPlaza") != null) {
            if (request.getParameter("eliminarPlaza").equals("Eliminar Plaza")) {
                System.out.println(eliminarPlaza(request, response));
                reenvio = request.getRequestDispatcher("actualizarDatosParqueadero.jsp");
                reenvio.forward(request, response);
            }
        } else if (request.getParameter("ingresarVehiculoReserva") != null) {
            if (request.getParameter("ingresarVehiculoReserva").equals("Ingresar Vehivulo Reserva")) {
                ingresarVehiculoReserva(request, response);
                reservasActivas(request, response);
            }
        } else if (request.getParameter("retirarVehiculoReserva") != null) {
            if (request.getParameter("retirarVehiculoReserva").equals("Retirar Vehivulo Reserva")) {
                retirarVehiculoReserva(request, response);
                reservasActivas(request, response);
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
        
        System.out.println(fecha);
        if (fecha.equals("")){
            request.setAttribute("reservasActivas", parqueaderoFacade.reservasActivas(nit));
        }else {
            System.out.println("Entro");
            request.setAttribute("reservasActivas", parqueaderoFacade.reservasActivas(nit, fecha));
        }
        
        reenvio = request.getRequestDispatcher("parqueadero.jsp");
        reenvio.forward(request, response);
    }

    /*El Metodo se encarga de retornar las plazas disponibles en el momento de la solicitud*/
    private void plazasDisponibles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("plazasDisponibles", parqueaderoFacade.plazasDisponibles(request.getParameter("nit"),
                request.getParameter("tipoDeAuto")));
        reenvio = request.getRequestDispatcher("parqueadero.jsp");
        reenvio.forward(request, response);
    }

    /*
    El Metodo se encarga de ingresar un auto el cual no ha reservado (Corregirlo "Agregar hora de entrada")
    REVISAR----- CAMBIO DE USO EN PLAZA (DISPONIBILIDAD)
     */
    private String adquirirPlaza(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("tipoReserva") != null) {
            System.out.println("com.garage.conexion.RespuestaParqueadero.adquirirPlaza()");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH:mm");
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            Date date = ts;
            String fechaReserva = formatter.format(date);
            String plazaId = request.getParameter("tipoReserva");
            String placa = request.getParameter("placa");
            String modelo = request.getParameter("modelo");

//            plaza = plazaFacade.find(plazaId);
//            plaza.setDisponibilidad(false);
//            plazaFacade.edit(plaza);
            ReservaPK llavePrimaria = new ReservaPK(fechaReserva, plazaId);
            reserva = new Reserva(llavePrimaria, true, modelo, placa);
            reservaFacade.create(reserva);
            return "El codigo de acceso es: " + plazaId + "/" + fechaReserva;
        }
        return "Favor Elegir Plaza";
    }

    /*El Metodo se encarga de dar por terminada una reserva segun el codigo ingresado*/
//    private String retirarAuto(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String codigo[] = request.getParameter("codigoReserva").split("/");
//        llavePrimaria = new ReservaPK(codigo[1], codigo[0]);
//
//        if ((reserva = reservaFacade.find(llavePrimaria)) != null) {
//            if (reserva.getActivo() == true) {
//                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH:mm");
//                Timestamp ts = new Timestamp(System.currentTimeMillis());
//                Date date = ts;
//                reserva.setFechasalida(formatter.format(date));
//                reserva.setActivo(false);
//                reservaFacade.edit(reserva);
//                plaza = plazaFacade.find(codigo[0]);
//                plaza.setDisponibilidad(true);
//                plazaFacade.edit(plaza);
//                return "El vehiculo puede ser retirado";
//            }
//        }
//        return "El codigo de reserva no coincide";
//    }

    private void ingresarVehiculoReserva(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("reservaActivaSeleccionada").split("/")[0];
        String fecha = request.getParameter("reservaActivaSeleccionada").split("/")[1];
        System.out.println(fecha + " " + id);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = ts;

        String fechaIngreso = formatter.format(date);
        System.out.println(fechaIngreso);
        ReservaPK re = new ReservaPK(fecha, id);
        reserva = reservaFacade.find(re);
        if (reserva.getFechaentrada() == null) {
            reserva.setFechaentrada(fechaIngreso);
            reservaFacade.edit(reserva);
        }
    }

    private void retirarVehiculoReserva(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("reservaActivaSeleccionada").split("/")[0];
        String fecha = request.getParameter("reservaActivaSeleccionada").split("/")[1];

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = ts;

        String fechaSalida = formatter.format(date);

        ReservaPK re = new ReservaPK(fecha, id);
        Reserva reserva = reservaFacade.find(re);

        if (reserva.getFechaentrada() != null) {
            if (reserva.getFechasalida() == null) {
                reserva.setFechasalida(fechaSalida);
                reserva.setActivo(false);
                reservaFacade.edit(reserva);
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
