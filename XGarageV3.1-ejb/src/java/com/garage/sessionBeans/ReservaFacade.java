/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.sessionBeans;

import com.garage.entities.Reserva;
import com.garage.entities.ReservaPK;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Diego
 */
@Stateless
public class ReservaFacade extends AbstractFacade<Reserva> implements ReservaFacadeLocal {

    @PersistenceContext(unitName = "XGarageV3.1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservaFacade() {
        super(Reserva.class);
    }

    @Override
    public void create(Reserva reserva) {
        em.persist(reserva);
    }

    @Override
    public void edit(Reserva reserva) {
        em.merge(reserva);
    }

    @Override
    public Reserva find(ReservaPK id) {
        return em.find(Reserva.class, id);
    }

    @Override
    public void remove(ReservaPK reserva) {
        em.remove(find(reserva));
    }

//-------------------------------------Reportes Usuario------------------------------------------------    
    @Override
    public List<Object[]> reportesUsuario(String numeroLicencia, String localidad) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT r.plaza.nit.nombre , r.fechareserva , r.fechaentrada, r.fechasalida , r.valor, r.reservaPK.fecha, r.reservaPK.plazaid FROM Reserva AS r WHERE r.numerolicencia.numerolicencia = :numeroLicencia AND r.plaza.nit.localidad = :localidad ORDER BY r.fechareserva DESC", Object[].class);
        query.setParameter("numeroLicencia", numeroLicencia);
        query.setParameter("localidad", localidad);
        return query.getResultList();
    }

    @Override
    public List<Object[]> reportesUsuario(String numeroLicencia, String localidad, String fecha) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT r.plaza.nit.nombre , r.fechareserva , r.fechaentrada, r.fechasalida , r.valor FROM Reserva AS r WHERE r.numerolicencia.numerolicencia = :numeroLicencia AND r.plaza.nit.localidad = :localidad AND r.fechareserva LIKE :fecha", Object[].class);
        query.setParameter("numeroLicencia", numeroLicencia);
        query.setParameter("localidad", localidad);
        query.setParameter("fecha", fecha.split("T")[0] + "%");
        return query.getResultList();
    }

    @Override
    public double costo(ReservaPK llave, String accion) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT p.tipoparqueadero.preciohora, p.tipoparqueadero.preciominuto, p.tipoparqueadero.precioreserva, r.fechaentrada, r.fechasalida FROM Plaza AS pl JOIN pl.reservaCollection AS r JOIN pl.nit AS p WHERE r.reservaPK.fecha = :fecha AND r.reservaPK.plazaid = :plazaid", Object[].class);
        query.setParameter("fecha", llave.getFecha());
        query.setParameter("plazaid", llave.getPlazaid());
        Object[] resultado = query.getResultList().get(0);
        double costo = 0;
        double precioHora = Double.parseDouble(resultado[0].toString());
        double precioMinuto = Double.parseDouble(resultado[1].toString());
        double precioReserva = Double.parseDouble(resultado[2].toString());
        if (accion.equals("deuda")) {
            if (resultado[3] != null) {
                Date fechaEntrada = transformacionFecha(resultado[3].toString());
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                Date fechaActual = transformacionFecha(transformacionFecha(ts));
//                  calcula 2 veces el valor???
                long diferencia = fechaActual.getTime() - fechaEntrada.getTime();
                long minutos = diferencia / (60 * 1000);
                long horas = 0;
                if (minutos > 60) {
                    horas = minutos / 60;
                }
                if (horas != 0) {
                    minutos = minutos - (horas * 60);
                }
               
                costo = minutos * precioMinuto + horas * precioHora + precioReserva;
            } else {
                costo = precioReserva;
            }
        } else if (accion.equals("facturar")) {
            Date fechaEntrada = transformacionFecha(resultado[3].toString());
            Date fechaSalida = transformacionFecha(resultado[4].toString());
            long diferencia = fechaSalida.getTime() - fechaEntrada.getTime();
            long minutos = diferencia / (60 * 1000);
            long horas = 0;
            if (minutos > 60) {
                horas = minutos / 60;
            }
            if (horas != 0) {
                minutos = minutos - (horas * 60);
            }
            
            costo = minutos * precioMinuto + horas * precioHora + precioReserva;
        } else if (accion.equals("penalizar")) {
            costo = precioMinuto * 30;
        }
       
        return costo;
    }

    /*Metodo encargado de pasar de String a fecha segun el formato que fue asignado para el proyecto*/
    private Date transformacionFecha(String fecha) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            return format.parse(fecha);
        } catch (ParseException ex) {
            return new Date();
        }
    }

    /*Metodo encargado de pasar de Date a String segun el formato que fue asignado para el proyecto*/
    private String transformacionFecha(Date fecha) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return format.format(fecha);

    }

//----------------------------------------Metodos Reporte MinTransporte (Reportes Diarios)----------------------------------------------   
    @Override
    public List<Object[]> reporteTotal(String fecha) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT r.plaza.nit.nombre, COUNT(r) FROM Reserva AS r WHERE r.fechareserva LIKE :fecha GROUP BY r.plaza.nit.nombre", Object[].class);
        query.setParameter("fecha",  fecha.split("T")[0] + "%");
        return query.getResultList();
    }

    @Override
    public List<Object[]> reporteLocalidad(String localidad, String fecha) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT r.plaza.nit.nombre, COUNT(r) FROM Reserva AS r WHERE r.plaza.nit.localidad = :localidad AND r.fechareserva LIKE :fecha GROUP BY r.plaza.nit.nombre", Object[].class);
        query.setParameter("localidad", localidad);
        query.setParameter("fecha", fecha.split("T")[0] + "%");
        return query.getResultList();
    }

    @Override
    public List<Object[]> reportesParqueadero(String nombreParqueadero, String fecha) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT r.plaza.tipodeauto, COUNT(r) FROM Reserva AS r WHERE r.plaza.nit.nombre = :nombreParqueadero AND r.fechareserva LIKE :fecha GROUP BY  r.plaza.tipodeauto", Object[].class);
        query.setParameter("fecha", fecha.split("T")[0] + "%");
        query.setParameter("nombreParqueadero", nombreParqueadero);
        return query.getResultList();
    }


//--------------------------------------Metodos Reporte MinTransporte (Reportes de Intervalos)---------------------------------------- 

    @Override
    public List<Object[]> reporteTotal(String fechaInicio, String fechaFin) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT  r.plaza.nit.nombre, COUNT(r) FROM Reserva AS r WHERE r.fechareserva >= :fechaInicio AND r.fechareserva <= :fechaFin GROUP BY r.plaza.nit.nombre", Object[].class);
        query.setParameter("fechaInicio",  fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.getResultList();
    }

    @Override
    public List<Object[]> reporteLocalidad(String localidad, String fechaInicio, String fechaFin) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT r.plaza.nit.nombre, COUNT(r) FROM Reserva AS r WHERE r.plaza.nit.localidad = :localidad AND r.fechareserva >= :fechaInicio AND r.fechareserva <= :fechaFin GROUP BY r.plaza.nit.nombre", Object[].class);
        query.setParameter("localidad", localidad);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.getResultList();
    }

    @Override
    public List<Object[]> reportesParqueadero(String nombreParqueadero, String fechaInicio, String fechaFin) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT r.plaza.tipodeauto, COUNT(r) FROM Reserva AS r WHERE r.plaza.nit.nombre = :nombreParqueadero AND r.fechareserva >= :fechaInicio AND r.fechareserva <= :fechaFin GROUP BY r.plaza.tipodeauto", Object[].class);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("nombreParqueadero", nombreParqueadero);
        return query.getResultList();
    }

    @Override
    public List<Object[]> reporteHorario(String nombreParqueadero, String fecha, String tipo) {
        String fechaInicio="", fechaFin="", dia[] = fecha.split("T");
        if(tipo.equals("Madrugada")){
            fechaInicio = dia[0]+"T00:00";
            fechaFin = dia[0]+"T04:59";
        }else if(tipo.equals("Mañana")){
            fechaInicio = dia[0]+"T05:00";
            fechaFin = dia[0]+"T11:59";
        }else if (tipo.equals("Tarde")){
            fechaInicio = dia[0]+"T12:00";
            fechaFin = dia[0]+"T17:59";
        }else if (tipo.equals("Noche")){
            fechaInicio = dia[0]+"T18:00";
            fechaFin = dia[0]+"T23:59";
        }
        String q = "SELECT r.plaza.tipodeauto, COUNT(r),'"+tipo+"' FROM Reserva AS r WHERE r.fechareserva >= :fechaInicio AND r.fechareserva <= :fechaFin AND r.plaza.nit.nombre = :nombreParqueadero GROUP BY r.plaza.tipodeauto";
        TypedQuery<Object[]> query = em.createQuery(q, Object[].class);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("nombreParqueadero", nombreParqueadero);
        
        return query.getResultList();
    }
    
    @Override
    public List<Object[]> historialParqueadero(String nit) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT r.reservaPK.plazaid, r.placa, r.reservaPK.fecha, r.fechareserva, r.fechaentrada ,r.fechasalida, r.valor    FROM Reserva AS r WHERE r.plaza.nit.nit = :nit AND r.activo = FALSE AND r.fechasalida IS NOT NULL ORDER BY r.fechareserva DESC ",
                 Object[].class);
        query.setParameter("nit", nit);
        return query.getResultList();
    }

    @Override
    public List<Object[]> pendientesParqueadero(String nit) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT r.reservaPK.plazaid, r.placa, r.reservaPK.fecha, r.fechareserva, r.fechaentrada ,r.fechasalida, r.valor    FROM Reserva AS r WHERE r.plaza.nit.nit = :nit AND r.activo = TRUE AND r.fechaentrada IS NULL ORDER BY r.fechareserva DESC ",
                 Object[].class);
        query.setParameter("nit", nit);
        return query.getResultList();
    }
    
    
    
    
}