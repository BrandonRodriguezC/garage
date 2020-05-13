/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.sessionBeans;

import com.garage.entities.Parqueadero;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Diego
 */
@Stateless
public class ParqueaderoFacade extends AbstractFacade<Parqueadero> implements ParqueaderoFacadeLocal {

    @PersistenceContext(unitName = "XGarageV3.1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParqueaderoFacade() {
        super(Parqueadero.class);
    }

    @Override
    public void create(Parqueadero parqueadero) {
        em.persist(parqueadero);
    }

    @Override
    public void edit(Parqueadero parqueadero) {
        em.merge(parqueadero);
    }

    @Override
    public Parqueadero find(String id) {
        return em.find(Parqueadero.class, id);
    }

    @Override
    public void remove(String nit) {
        Query query = em.createQuery("DELETE FROM Reserva AS r WHERE r.plaza.nit.nit = :nit");
        query.setParameter("nit", nit);
        query.executeUpdate();
        query = em.createQuery("DELETE FROM Plaza AS p WHERE p.nit.nit = :nit");
        query.setParameter("nit", nit);
        query.executeUpdate();
        em.remove(find(nit));
    }

    @Override
    public List<Parqueadero> findAll() {
        return em.createNamedQuery("Parqueadero.findAll").getResultList();
    }

    @Override
    public Parqueadero getParqueadero(String usuario) {
        TypedQuery<Parqueadero> query = em.createQuery("SELECT p FROM Parqueadero AS p WHERE p.usuario.usuario = :usuario", Parqueadero.class);
        query.setParameter("usuario", usuario);
        return query.getSingleResult();
    }
    
    @Override
    public List<Object[]> reservasActivas(String nit, String fecha) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT r.reservaPK.plazaid, r.placa, r.reservaPK.fecha, r.fechareserva, r.fechaentrada ,r.fechasalida, r.valor  FROM Reserva AS r WHERE r.activo = TRUE AND r.plaza.nit.nit = :nit AND r.fechareserva LIKE :fecha ORDER BY r.fechareserva ASC " //                "SELECT r.reservaPK, r.numerolicencia.usuario.usuario, r.reservaPK.fecha, r.fechaentrada, r.fechasalida, r.valor FROM Plaza AS p INNER JOIN p.reservaCollection AS r WHERE r.activo = TRUE AND p.nit.nit = :nit ORDER BY r.reservaPK.fecha DESC"
                ,
                 Object[].class);
        query.setParameter("fecha", fecha.split("T")[0] + "%");
        query.setParameter("nit", nit);
        return query.getResultList();
    }

    @Override
    public List<Object[]> plazasDisponibles(String nit, String tipoDeAuto) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = ts;
        String fecha = formatter.format(date).split("T")[0] + "%";
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT pl.plazaid, pl.tipodeauto FROM Plaza AS pl WHERE pl.nit.nit = :nit AND pl.tipodeauto = :tipoDeAuto AND pl.plazaid NOT IN (SELECT re.reservaPK.plazaid FROM Reserva AS re WHERE re.fechareserva LIKE :fecha AND re.activo = TRUE) ",
                 Object[].class);
        query.setParameter("fecha", fecha);
        query.setParameter("nit", nit);
        query.setParameter("tipoDeAuto", tipoDeAuto);
        return query.getResultList();
    }

}
