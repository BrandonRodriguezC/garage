/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.sessionBeans;

import com.garage.entities.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Diego
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(String usuario);

    Usuario find(String usuario);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    boolean contrasena(String usuario, String contrasena);
    
    boolean cambiarContrasena(String usuario, String contrasenaActual, String nuevaContrasena);
    
}