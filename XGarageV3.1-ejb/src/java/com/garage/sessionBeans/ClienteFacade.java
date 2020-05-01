/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.sessionBeans;

import com.garage.entities.Cliente;
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
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {

    @PersistenceContext(unitName = "XGarageV3.1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    @Override
    public void create(Cliente cliente) {
        em.persist(cliente);
    }

    @Override
    public void edit(Cliente cliente) {
        em.merge(cliente);
    }

    @Override
    public Cliente find(String numeroLicencia) {
        return em.find(Cliente.class, numeroLicencia);
    }

    @Override
    public List<Cliente> findAll() {
        return em.createNamedQuery("Cliente.findAll").getResultList();
    }

    //Problema
    @Override
    public void remove(String numeroLicencia) {
        Query query = em.createQuery("DELETE FROM Reserva AS r WHERE r.numerolicencia.numerolicencia = :numerolicencia");
        query.setParameter("numerolicencia", numeroLicencia);
        query.executeUpdate();
        em.remove(find(numeroLicencia));
    }

    @Override
    public Cliente getCliente(String usuario) {
        System.out.println("Entro");
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente AS c WHERE c.usuario.usuario = :usuario", Cliente.class);
        query.setParameter("usuario", usuario);
        return query.getSingleResult();
    }

    @Override
    public List<Object[]> parqueaderoCercanos(String localidad, String tipoDeAuto, String fecha) {
//         TypedQuery<Object[]> query = em.createQuery(
//                "SELECT p.nombre, p.ubicacion.ubicacionPK.direccion, p.ubicacion.ubicacionPK.ciudad, p.ubicacion.localidad, p.nit FROM Parqueadero AS p WHERE 0 <(SELECT COUNT(a.disponibilidad) FROM Plaza AS a WHERE a MEMBER OF p.plazaCollection AND a.disponibilidad = TRUE AND a.tipodeauto = :tipoDeAuto) AND p.ubicacion.localidad = :localidad", Object[].class);
        TypedQuery<Object[]> query = em.createQuery(
                //                "SELECT p.latitud, p.longitud, p.nombre, p.direccion, p.nit FROM Parqueadero AS p WHERE 0 <(SELECT COUNT(a.disponibilidad) FROM Plaza AS a WHERE a MEMBER OF p.plazaCollection AND a.disponibilidad = TRUE AND a.tipodeauto = :tipoDeAuto) AND p.localidad = :localidad"
                //               "SELECT p.latitud, p.longitud, p.nombre, p.direccion, p.nit FROM Parqueadero AS p WHERE p.nit = (---------) AND p.localidad = :localidad "
                //                SELECT p.nit, p.latitud, p.longitud, p.nombre, p.direccion FROM Parqueadero AS p WHERE p.nit IN (         ) AND p.localidad = :localidad
                //                " SELECT p.latitud, p.longitud, p.nombre, p.direccion, p.nit, p.tipoparqueadero.preciominuto FROM Parqueadero AS p LEFT OUTER JOIN p.plazaCollection AS pc ON pc.nit.nit IN ( SELECT DISTINCT pl.nit.nit FROM Plaza AS pl WHERE pl.plazaid NOT IN (SELECT re.reservaPK.plazaid FROM Reserva AS re WHERE re.fechareserva LIKE :fecha ) AND pl.tipodeauto = :tipoDeAuto ) WHERE p.localidad = :localidad"
                " SELECT p.latitud, p.longitud, p.nombre, p.direccion, p.nit, p.tipoparqueadero.preciominuto FROM Parqueadero AS p WHERE p.nit IN ( SELECT DISTINCT pl.nit.nit FROM Plaza AS pl WHERE pl.plazaid NOT IN (SELECT re.reservaPK.plazaid FROM Reserva AS re WHERE re.fechareserva LIKE :fecha AND re.activo = true) AND pl.tipodeauto = :tipoDeAuto ) AND p.localidad = :localidad ",
                 Object[].class);
        // Se agrego AND re.activo = true
        fecha = fecha.split("T")[0] + "%";
        query.setParameter("fecha", fecha);
        query.setParameter("tipoDeAuto", tipoDeAuto);
        query.setParameter("localidad", localidad);
        
//        List<Object[]> lista = query.getResultList();
//        System.out.println(lista.size());
//        for (int i = 0; i < lista.size(); i++) {
//            for (int j = 0; j < lista.get(i).length ; j++) {
//                System.out.print(lista.get(i)[j]+"      ");
//            }
//            
//            System.out.println("..........................");
//        }
//      return lista;
        return query.getResultList();
       
    }

    @Override
    public List<Object[]> plazasDisponibles(String fecha, String tipoDeAuto, String nit) {
        TypedQuery<Object[]> query = em.createQuery(
                //AND (pl.tipodeauto = :tipoDeAuto) AND (pl.nit = :nit)
                "SELECT pl.plazaid FROM Plaza AS pl WHERE pl.nit.nit = :nit AND pl.tipodeauto = :tipoDeAuto AND pl.plazaid NOT IN (SELECT re.reservaPK.plazaid FROM Reserva AS re WHERE re.fechareserva LIKE :fecha AND re.activo = TRUE) " //"SELECT p.plazaid, p.disponibilidad FROM Plaza AS p WHERE p.nit.nit = :nit AND p.disponibilidad = TRUE"
                ,Object[].class);
        // Se agrego AND re.activo = true
        fecha = fecha.split("T")[0] + "%";
        query.setParameter("fecha", fecha);
        query.setParameter("tipoDeAuto", tipoDeAuto);
        query.setParameter("nit", nit);
//      List<Object[]> lista = query.getResultList();
//      for (int i = 0; i < lista.size(); i++) {
//          System.out.println(lista.get(i));
//      }
//      return lista;
        return query.getResultList();
    }

    @Override
    public List<Object[]> reservasActivas(String numeroLicencia) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT p.nit.nombre, p.nit.direccion, r.reservaPK.plazaid, r.fechareserva, r.reservaPK.fecha FROM Plaza AS p INNER JOIN p.reservaCollection AS r WHERE r.activo = TRUE AND r.fechasalida IS NULL AND r.numerolicencia.numerolicencia = :numeroLicencia ORDER BY r.fechareserva DESC", Object[].class);
        query.setParameter("numeroLicencia", numeroLicencia);
        return query.getResultList();
    }

}
