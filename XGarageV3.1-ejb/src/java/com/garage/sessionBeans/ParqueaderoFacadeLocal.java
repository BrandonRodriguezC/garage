/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.sessionBeans;

import com.garage.entities.Parqueadero;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Diego
 */
@Local
public interface ParqueaderoFacadeLocal {

    void create(Parqueadero parqueadero);

    void edit(Parqueadero parqueadero);

    void remove(String nit);

    Parqueadero find(String nit);

    List<Parqueadero> findAll();

    List<Parqueadero> findRange(int[] range);

    int count();
    
    Parqueadero getParqueadero(String usuario);
    
    List<Object[]> reservasActivas(String nit, String fecha);
    
    List<Object[]> plazasDisponibles(String usuarioparqueadero, String tipoDeAuto);
    
}
