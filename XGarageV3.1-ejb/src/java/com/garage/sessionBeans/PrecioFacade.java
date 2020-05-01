/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.sessionBeans;

import com.garage.entities.Precio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Diego
 */
@Stateless
public class PrecioFacade extends AbstractFacade<Precio> implements PrecioFacadeLocal {

    @PersistenceContext(unitName = "XGarageV3.1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrecioFacade() {
        super(Precio.class);
    }
    
    @Override
    public void create(Precio precio) {
        em.persist(precio);
    }

    @Override
    public void edit(Precio precio) {
        em.merge(precio);
    }

    @Override
    public Precio find(String nit) {
        return em.find(Precio.class, nit);
    }

    @Override
    public void remove(String nit) {
        em.remove(find(nit));
    }
    
}
