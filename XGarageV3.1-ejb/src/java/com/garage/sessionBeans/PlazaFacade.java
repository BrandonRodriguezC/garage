/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.sessionBeans;

import com.garage.entities.Plaza;
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
public class PlazaFacade extends AbstractFacade<Plaza> implements PlazaFacadeLocal {

    @PersistenceContext(unitName = "XGarageV3.1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlazaFacade() {
        super(Plaza.class);
    }
    
     @Override
    public void create(Plaza plaza) {
        em.persist(plaza);
    }

    @Override
    public Plaza find(String plazaId) {
        return em.find(Plaza.class, plazaId);
    }

    @Override
    public void edit(Plaza plaza) {
        em.merge(plaza);
    }

    @Override
    public List<Plaza> findAll() {
        return em.createNamedQuery("Plaza.findAll").getResultList();
    }

    @Override
    public void remove(String plazaId) {
        em.remove(find(plazaId));
    }

    @Override
    public boolean eliminarPlaza(String plazaId) {
        Plaza plaza = find(plazaId);
        if (plaza != null) {
            remove(plazaId);
            return true;
        }
        return false;
    }

    @Override
    public List<Object[]> obtenerPlazas(String nit) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT a.plazaid, a.tipodeauto FROM Plaza AS a WHERE a.nit.nit = :nit", Object[].class);
        query.setParameter("nit", nit);
        return query.getResultList();
    }
   
}