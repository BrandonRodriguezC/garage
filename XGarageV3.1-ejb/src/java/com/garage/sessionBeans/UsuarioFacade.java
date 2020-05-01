/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.sessionBeans;

import com.garage.entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Diego
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "XGarageV3.1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
     @Override
    public void create(Usuario usuario) {
        em.persist(usuario);
    }

    @Override
    public void edit(Usuario usuario) {
        em.merge(usuario);
    }

    @Override
    public Usuario find(String id) {
        return em.find(Usuario.class, id);
    }

    @Override
    public List<Usuario> findAll() {
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }

    @Override
    public void remove(String usuario) {
        em.remove(find(usuario));
    }
    
    @Override
    public boolean contrasena(String usuario, String contrasena) {
        Usuario user = find(usuario);
        if (user != null) {
            if (user.getContrasena().equals(contrasena)) {
                return true;
            }
            return false;
        }
        return false;
    }
    
    
    @Override
    public boolean cambiarContrasena(String usuario, String contrasenaActual, String nuevaContrasena) {
        Usuario user = find(usuario);
        if (user != null) {
            if (user.getContrasena().equals(contrasenaActual)) {
                user.setContrasena(nuevaContrasena);
                edit(user);
                return true;
            }
        }
        return false;
    }
    
}
