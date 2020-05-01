/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.sessionBeans;

import com.garage.entities.Reserva;
import com.garage.entities.ReservaPK;
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
    public Object[] costo(ReservaPK llave) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT p.tipoparqueadero.preciohora, p.tipoparqueadero.preciominuto, p.tipoparqueadero.precioreserva, r.fechaentrada, r.fechasalida FROM Plaza AS pl JOIN pl.reservaCollection AS r JOIN pl.nit AS p WHERE r.reservaPK.fecha = :fecha AND r.reservaPK.plazaid = :plazaid", Object[].class);
        query.setParameter("fecha", llave.getFecha());
        query.setParameter("plazaid", llave.getPlazaid());
        return query.getResultList().get(0);
    }

//----------------------------------------Metodos Reporte MinTransporte (Reportes Diarios)----------------------------------------------   
    @Override
    public List<Object[]> reporteTotal(String fecha) {
        System.out.println(fecha);
        //Problemas por tipo de dato para hacer el SUM
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT r.plaza.nit.nombre, r.plaza.tipodeauto, COUNT(r) FROM Reserva AS r WHERE r.fechareserva LIKE :fecha GROUP BY r.plaza.nit.nombre, r.plaza.tipodeauto", Object[].class);
        query.setParameter("fecha",  fecha.split("T")[0] + "%");
        return query.getResultList();
    }

    @Override
    public List<Object[]> reporteLocalidad(String localidad, String fecha) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT r.plaza.nit.nombre, r.plaza.tipodeauto, COUNT(r) FROM Reserva AS r WHERE r.plaza.nit.localidad = :localidad AND r.fechareserva LIKE :fecha GROUP BY r.plaza.nit.nombre, r.plaza.tipodeauto", Object[].class);
        query.setParameter("localidad", localidad);
        query.setParameter("fecha", fecha.split("T")[0] + "%");
        return query.getResultList();
    }

    @Override
    public List<Object[]> reportesParqueadero(String nombreParqueadero, String fecha) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT r.plaza.plazaid, r.plaza.tipodeauto, COUNT(r) FROM Reserva AS r WHERE r.plaza.nit.nombre = :nombreParqueadero AND r.fechareserva LIKE :fecha GROUP BY r.plaza.plazaid, r.plaza.tipodeauto", Object[].class);
        query.setParameter("fecha", fecha.split("T")[0] + "%");
        query.setParameter("nombreParqueadero", nombreParqueadero);
        return query.getResultList();
    }


//--------------------------------------Metodos Reporte MinTransporte (Reportes de Intervalos)---------------------------------------- 

    @Override
    public List<Object[]> reporteTotal(String fechaInicio, String fechaFin) {
        //Problemas por tipo de dato para hacer el SUM
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT r.plaza.nit.nombre, r.plaza.tipodeauto, COUNT(r) FROM Reserva AS r WHERE r.fechareserva >= :fechaInicio AND r.fechareserva <= :fechaFin GROUP BY r.plaza.nit.nombre, r.plaza.tipodeauto", Object[].class);
        query.setParameter("fechaInicio",  fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.getResultList();
    }

    @Override
    public List<Object[]> reporteLocalidad(String localidad, String fechaInicio, String fechaFin) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT r.plaza.nit.nombre, r.plaza.tipodeauto, COUNT(r) FROM Reserva AS r WHERE r.plaza.nit.localidad = :localidad AND r.fechareserva >= :fechaInicio AND r.fechareserva <= :fechaFin GROUP BY r.plaza.nit.nombre, r.plaza.tipodeauto", Object[].class);
        query.setParameter("localidad", localidad);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.getResultList();
    }

    @Override
    public List<Object[]> reportesParqueadero(String nombreParqueadero, String fechaInicio, String fechaFin) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT r.plaza.plazaid, r.plaza.tipodeauto, COUNT(r) FROM Reserva AS r WHERE r.plaza.nit.nombre = :nombreParqueadero AND r.fechareserva >= :fechaInicio AND r.fechareserva <= :fechaFin GROUP BY r.plaza.plazaid, r.plaza.tipodeauto", Object[].class);
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
        
//        System.out.println("Fecha " + fecha);
//        System.out.println("Fecha Inicio " + fechaInicio);
//        System.out.println("Fecha Fin " + fechaFin);
//        System.out.println("Dia: " + dia[0]);
//        System.out.println("Tipo " + tipo);
        String q = "SELECT r.plaza.plazaid, r.plaza.tipodeauto, COUNT(r),'"+tipo+"' FROM Reserva AS r WHERE r.fechareserva >= :fechaInicio AND r.fechareserva <= :fechaFin AND r.plaza.nit.nombre = :nombreParqueadero GROUP BY r.plaza.plazaid, r.plaza.tipodeauto";
        TypedQuery<Object[]> query = em.createQuery(q, Object[].class);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("nombreParqueadero", nombreParqueadero);
        
        return query.getResultList();
    }
    
    
    
    
}