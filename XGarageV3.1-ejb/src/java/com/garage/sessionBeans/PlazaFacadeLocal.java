/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.sessionBeans;

import com.garage.entities.Plaza;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Diego
 */
@Local
public interface PlazaFacadeLocal {

    void create(Plaza plaza);

    void edit(Plaza plaza);

    void remove(String plazaId);

    Plaza find(String plazaId);

    List<Plaza> findAll();

    List<Plaza> findRange(int[] range);

    int count();

    public boolean eliminarPlaza(String plazaId);

    List<Object[]> obtenerPlazas(String parqueadero);
    
}