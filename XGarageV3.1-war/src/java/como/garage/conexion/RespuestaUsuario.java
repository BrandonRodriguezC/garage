/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package como.garage.conexion;

import com.garage.entities.Cliente;
import com.garage.entities.Reserva;
import com.garage.entities.ReservaPK;
import com.garage.entities.Usuario;
import com.garage.sessionBeans.ClienteFacadeLocal;
import com.garage.sessionBeans.ParqueaderoFacadeLocal;
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
public class RespuestaUsuario extends HttpServlet {

    @EJB
    private ReservaFacadeLocal reservaFacade;

    @EJB
    private ParqueaderoFacadeLocal parqueaderoFacade;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    @EJB
    private ClienteFacadeLocal clienteFacade;
    
    
    private Usuario user;
    private Cliente client;
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
        if (request.getParameter("actualizarDatos") != null) {
            if (request.getParameter("actualizarDatos").equals("Actualizar Datos")) {
                actualizarDatos(request, response);
                reenvio = request.getRequestDispatcher("actualizarDatosUsuario.jsp");
                reenvio.forward(request, response);
            }
        } else if (request.getParameter("eliminarCuenta") != null) {
            if (request.getParameter("eliminarCuenta").equals("Eliminar Cuenta")) {
                eliminarCuenta(request, response);
                reenvio = request.getRequestDispatcher("index.jsp");
                reenvio.forward(request, response);
            }

        } else if (request.getParameter("buscarParqueadero") != null) {
            if (request.getParameter("buscarParqueadero").equals("Buscar Parqueaderos")) {
                buscarParqueadero(request, response);
            }

        } else if (request.getParameter("reservar") != null) {
            if (request.getParameter("reservar").equals("Reservar")) {
                request.setAttribute("mensajeReserva", reservar(request, response));
                reenvio = request.getRequestDispatcher("usuario.jsp");
                reenvio.forward(request, response);
            }
        } else if (request.getParameter("reservasActivas") != null) {
            if (request.getParameter("reservasActivas").equals("Actualizar Reservas")) {
                reservasActivas(request, response);
            }
        } else if (request.getParameter("cancelarReservas") != null) {
            if (request.getParameter("cancelarReservas").equals("Cancelar Reserva")) {
                cancelarReservas(request, response);
                reservasActivas(request, response);
            }
        } else if (request.getParameter("BuscarPlaza") != null) {
            if (request.getParameter("BuscarPlaza").equals("Buscar plaza")) {
                buscarPlaza(request, response);
            }
        } else if (request.getParameter("reporteUsuario") != null) {
            if (request.getParameter("reporteUsuario").equals("Generar Reporte")) {
                reporteUsuario(request, response);
            }
        } else if (request.getParameter("costoDeReserva") != null) {
            if (request.getParameter("costoDeReserva").equals("Calcular Deuda Actual")) {
                costeAproximado(request, response);
            }
        }
    }

    /**/
    private void buscarPlaza(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nit = request.getParameter("Parqueadero_Nit").trim();
        String fecha = request.getParameter("fecha").trim();
        String tipoDeAuto = request.getParameter("tipoDeAuto").trim();
        List<Object[]> lista = clienteFacade.plazasDisponibles(fecha, tipoDeAuto, nit);
        String titulo = request.getParameter("Parqueadero_Titulo");
        String tarifa = request.getParameter("Parqueadero_Tarifa");
        String direccion = request.getParameter("Parqueadero_Direccion");
        
        request.setAttribute("ParqueaderoNitI", nit);
        request.setAttribute("ParqueaderoTituloI", titulo);
        request.setAttribute("ParqueaderoTarifaI", tarifa);
        request.setAttribute("ParqueaderoDireccionI", direccion);
        request.setAttribute("plazasLista", lista);
        request.getSession().setAttribute("panelDerecho", "block");

        reenvio = request.getRequestDispatcher("usuario.jsp");
        reenvio.forward(request, response);

    }

    /*El siguiente metodo se encarga de buscar parqueaderos disponibles segun la localidad y el tipo de plaza*/
    private void buscarParqueadero(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fecha = request.getParameter("fecha");
        String localidad = request.getParameter("localidad");
        String tipoDeAuto = request.getParameter("tipoPlaza");
        
        request.getSession().setAttribute("variable", clienteFacade.parqueaderoCercanos(localidad, tipoDeAuto, fecha));
        request.getSession().setAttribute("tipoDeAuto", tipoDeAuto);
        request.getSession().setAttribute("fecha", fecha);
        reenvio = request.getRequestDispatcher("usuario.jsp");
        reenvio.forward(request, response);
    }

    /*El Metodo se encarga de actualizar los datos requeridos por el usuario*/
    private String actualizarDatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroLicencia = request.getParameter("numeroLicencia");
        String usuario = request.getParameter("usuario");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String documentoIdentidad = request.getParameter("documentoIdentidad");
        String tipoDocumento = request.getParameter("tipoDocumento");
        String contrasena = request.getParameter("contrasena");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");

        client = clienteFacade.find(numeroLicencia);
        user = usuarioFacade.find(usuario);

        if (!nombre.equals("")) {
            client.setNombre(nombre);
        }
        if (!apellido.equals("")) {
            client.setApellido(apellido);
        }
        if (!contrasena.equals("")) {
            user.setContrasena(contrasena);
            usuarioFacade.edit(user);
        }
        if (!documentoIdentidad.equals("")) {
            client.setDocumentoidentidad(documentoIdentidad);
        }
        if (!correo.equals("")) {
            client.setCorreo(correo);
        }
        if (!telefono.equals("")) {
            client.setTelefono(telefono);
        }
        client.setTipodocumento(tipoDocumento);
        clienteFacade.edit(client);

        return "Los Datos han sido actualizados correctamente";
    }

    /*El Metodo se encarga de eliminar la cuenta del usuario (reservas, datos y usuario)*/
    private String eliminarCuenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().getAttribute("usuario");
        String usuario = request.getParameter("usuario");
        String numeroLicencia = request.getParameter("numeroLicencia");
        clienteFacade.remove(numeroLicencia);
        usuarioFacade.remove(usuario);
        return "Usuario Eliminado Exitosamente";
    }

    /*El metodo sirve para realizar reservas dentro de la plaza seleccionada (hay que arreglar la hora de reserva)*/
    private String reservar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("Parqueadero_Nit") != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            Date date = ts;
            String numeroLicencia = request.getParameter("numero_Licencia");
            String nit = request.getParameter("Parqueadero_Nit");
            String modelo = request.getParameter("modelo");
            String placa = request.getParameter("placa");
            String plaza = request.getParameter("plaza_Reservar");
            String fechaReserva = request.getParameter("fecha");

            if (fechaReserva.equals("")) {
                fechaReserva = formatter.format(date);
            }
            String fechaReservaPK = formatter.format(date);

            client = clienteFacade.find(numeroLicencia);
            ReservaPK reservaPK = new ReservaPK(fechaReservaPK, plaza);
            reserva = new Reserva(reservaPK, client, modelo, placa, true, fechaReserva);

            reservaFacade.create(reserva);

            String rta = "La Reserva exitosa en el parqueadero: " + parqueaderoFacade.find(nit).getNombre()
                    + " codigo de reserva: " + plaza + "/" + fechaReserva;
            return rta;
        }
        return "Favor elegir Parqueadero";
    }

    /*El metodo retorna las reservas que los usuarios tengan activas*/
    private void reservasActivas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroLicencia = request.getParameter("numeroLicencia");
        List<Object[]> lista = clienteFacade.reservasActivas(numeroLicencia);
        
        List<String> color = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = ts;
        String ya = formatter.format(date);
        Date fechaActual = transformacionFecha(ya);

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i)[5] == null) {
                Date fechaEntrada = transformacionFecha(lista.get(i)[3].toString());
                long diferencia = fechaActual.getTime() - fechaEntrada.getTime();
                long minutos = diferencia / (60 * 1000);
                if (minutos > 30) {
                    color.add("red");
                }
            }
            color.add("black");
        }
        
        request.setAttribute("color", color);
        request.setAttribute("reservasActivas",lista );
        reenvio = request.getRequestDispatcher("usuario.jsp");
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

    /*Metodo encarga de cancelar la reserva seleccionada*/
    private String cancelarReservas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("reservaPK") != null) {
            String llave[] = request.getParameter("reservaPK").split("/");
            ReservaPK llavePrimaria = new ReservaPK(llave[0], llave[1]);
            try {
                if ((reserva = reservaFacade.find(llavePrimaria)) != null && llave[2] == null) {
                    reserva.setActivo(false);
                    reservaFacade.edit(reserva);
                    return "Reserva Cancelada";
                }
            } catch (Exception e) {
                if ((reserva = reservaFacade.find(llavePrimaria)) != null) {
                    reserva.setActivo(false);
                    reservaFacade.edit(reserva);
                    return "Reserva Cancelada";
                }
            }

            return "Reserva Invalida";
        }
        return "Favor seleccionar reserva";
    }

    /*El metodo retorna las reservas que han sido realizadas por el usuario dependiendo de su localidad*/
    private void reporteUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroLicencia = request.getParameter("numeroLicencia");
        String localidadReporte = request.getParameter("localidadReporte");
        String fecha = request.getParameter("fechaReporte");

        if (!fecha.equals("")) {
            request.setAttribute("reportes", reservaFacade.reportesUsuario(numeroLicencia, localidadReporte, fecha));
        } else {
            request.setAttribute("reportes", reservaFacade.reportesUsuario(numeroLicencia, localidadReporte));
        }

        reenvio = request.getRequestDispatcher("usuario.jsp");
        reenvio.forward(request, response);
    }

    /*Metodo encargado de calcular el valor que tiene un cliente desde la entrada del vehiculo a la hora actual*/
    private void costeAproximado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("reservaPK") != null) {
            String llave[] = request.getParameter("reservaPK").split("/");
            double costo = reservaFacade.costo(new ReservaPK(llave[0], llave[1]), "deuda");
            request.setAttribute("costo", "Su deuda actual es: $ " + costo);
        } else {
            request.setAttribute("costo", "No es posible calcular ");
        }
        reenvio = request.getRequestDispatcher("usuario.jsp");
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