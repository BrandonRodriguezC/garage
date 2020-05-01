/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.sessionBeans;

import com.garage.entities.Precio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Diego
 */
@Local
public interface PrecioFacadeLocal {

    void create(Precio precio);

    void edit(Precio precio);

    void remove(String nit);

    Precio find(String nit);

    List<Precio> findAll();

    List<Precio> findRange(int[] range);

    int count();
    
}
