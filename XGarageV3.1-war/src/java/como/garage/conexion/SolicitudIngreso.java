/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package como.garage.conexion;

import com.garage.entities.Cliente;
import com.garage.entities.Parqueadero;
import com.garage.entities.Usuario;
import com.garage.sessionBeans.ClienteFacadeLocal;
import com.garage.sessionBeans.ParqueaderoFacadeLocal;
import com.garage.sessionBeans.PrecioFacadeLocal;
import com.garage.sessionBeans.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
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
public class SolicitudIngreso extends HttpServlet {

    @EJB
    private ClienteFacadeLocal clienteFacade;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    @EJB
    private ParqueaderoFacadeLocal parqueaderoFacade;

    @EJB
    private PrecioFacadeLocal precioFacade;

    
    
    private Usuario user;
    private Parqueadero park;
    private Cliente client;

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
        if (request.getParameter("ingreso") != null) {
            if (request.getParameter("ingreso").equals("Ingresar")) {
                ingreso(request, response);
            }
        } else if (request.getParameter("cambioContrasena") != null) {
            if (request.getParameter("cambioContrasena").equals("Cambiar ContraseÃ±a")) {
                System.out.println(cambioContrasena(request, response));
                reenvio = request.getRequestDispatcher("ingreso.jsp");
                reenvio.forward(request, response);
            }
        } else if (request.getParameter("registro") != null) {
            if (usuarioFacade.find(request.getParameter("usuario")) == null) {
                user = new Usuario(request.getParameter("usuario"), request.getParameter("contrasena"), request.getParameter("tipoCliente"));

                if (request.getParameter("registro").equals("Registrar Usuario")) {
                    System.out.println(registroUsuario(request, response, user));

                } else if (request.getParameter("registro").equals("Registrar Parqueadero")) {
                    System.out.println(registroParqueadero(request, response, user));
                }
                reenvio = request.getRequestDispatcher("ingreso.jsp");
                reenvio.forward(request, response);
            }
        }
    }

    /*El Metodo se encarga de dar acceso a la plataforma a los usuarios existentes en la plataforma (parqueadero o cliente)*/
    private void ingreso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario").trim();
        String contrasena = request.getParameter("contrasena").trim();
        if (usuarioFacade.contrasena(usuario, contrasena)) {
            user = usuarioFacade.find(usuario);
            if (user.getTipodeusuario().equals("usuario")) {
                Cliente us = clienteFacade.getCliente(usuario);
                request.getSession().setAttribute("usuario", request.getParameter("usuario"));
                request.getSession().setAttribute("numeroLicencia", us.getNumerolicencia());
                request.getSession().setAttribute("nombre", us.getNombre());
                request.getSession().setAttribute("apellido", us.getApellido());
                request.getSession().setAttribute("telefono", us.getTelefono());
                request.getSession().setAttribute("tipodocumento", us.getTipodocumento());
                request.getSession().setAttribute("documentoidentidad", us.getDocumentoidentidad());

                reenvio = request.getRequestDispatcher("usuario.jsp");
                reenvio.forward(request, response);

            } else if (user.getTipodeusuario().equals("parqueadero")) {
                Parqueadero par = parqueaderoFacade.getParqueadero(usuario);
                request.getSession().setAttribute("usuario", request.getParameter("usuario"));
                request.getSession().setAttribute("nit", par.getNit());
                request.getSession().setAttribute("nombre", par.getNombre());
                request.getSession().setAttribute("ciudad", par.getCiudad());
                request.getSession().setAttribute("direccion", par.getDireccion());
                request.getSession().setAttribute("localidad", par.getLocalidad().split(" ")[1]);

                reenvio = request.getRequestDispatcher("parqueadero.jsp");
                reenvio.forward(request, response);
            }
        } else {
            reenvio = request.getRequestDispatcher("ingreso.jsp");
            reenvio.forward(request, response);
        }
    }

    /*El Metodo se encarga de agregar el usuario a la plataforma*/
    private String registroUsuario(HttpServletRequest request, HttpServletResponse response, Usuario user)
            throws ServletException, IOException {
        String numeroLicencia = request.getParameter("numeroLicencia").trim();
        String nombre = request.getParameter("nombre").trim();
        String apellido = request.getParameter("apellido").trim();
        String documentoIdentidad = request.getParameter("documentoIdentidad").trim();
        String tipoDocumento = request.getParameter("tipoDocumento").trim();
        String correo = request.getParameter("correo").trim();
        String telefono = request.getParameter("telefono").trim();

        if (clienteFacade.find(numeroLicencia) == null) {
            client = new Cliente(user, numeroLicencia, nombre, apellido, documentoIdentidad, tipoDocumento);
            if (!correo.equals("")) {
                client.setCorreo(correo);
            }
            if (!telefono.equals("")) {
                client.setTelefono(telefono);
            }
            usuarioFacade.create(user);
            clienteFacade.create(client);
            return "El usuario: " + user.getUsuario() + " ha sido creado exitosamente";
        }
        return "El usuario: " + user.getUsuario() + " ya ha sido creado";
    }

    /**
     * El Metodo se encarga de agregar el parqueadero a la plataforma
     *
     */
    private String registroParqueadero(HttpServletRequest request, HttpServletResponse response, Usuario user)
            throws ServletException, IOException {
        String nit = request.getParameter("nit").trim();
        String nombre = request.getParameter("nombreParqueadero").trim();
        String ciudad = request.getParameter("ciudad").trim();
        String direccion = request.getParameter("direccion").trim();
        String localidad = request.getParameter("localidad").trim();
        String longitud = request.getParameter("longitud").trim();
        String latitud = request.getParameter("latitud").trim();
        String tipoParqueadero = request.getParameter("tipo_Parqueadero");

        System.out.println(tipoParqueadero);
        if (parqueaderoFacade.find(nit) == null) {
            usuarioFacade.create(user);
            park = new Parqueadero(user, nit, nombre, precioFacade.find(tipoParqueadero), Double.parseDouble(longitud), Double.parseDouble(latitud), ciudad, direccion, localidad);
            // System.out.println(park.toString());
            parqueaderoFacade.create(park);
            return "El parqueadero: " + nombre + " ha sido creado con exito";
        }
        return "El parqueadero: " + nombre + " ya ha sido creado";
    }

    /*El Metodo se encarga de cambiar la contraseña de un usuario ya existente*/
    private String cambioContrasena(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String contrasenaActual = request.getParameter("actualContrasena");
        String nuevaContrasena = request.getParameter("nuevaContrasena");
        if (usuarioFacade.cambiarContrasena(usuario, contrasenaActual, nuevaContrasena)) {
            return "La contraseña del usuario: " + usuario + " con exito";
        }
        return "La contraseña actual ingresada no corresponde con la del usuario";
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
