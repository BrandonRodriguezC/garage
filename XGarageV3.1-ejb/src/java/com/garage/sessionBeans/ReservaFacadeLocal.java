/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.sessionBeans;

import com.garage.entities.Reserva;
import com.garage.entities.ReservaPK;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Diego
 */
@Local
public interface ReservaFacadeLocal {

    void create(Reserva reserva);

    void edit(Reserva reserva);

    void remove(ReservaPK reserva);

    Reserva find(ReservaPK id);

    List<Reserva> findAll();

    List<Reserva> findRange(int[] range);

    int count();
    
    List<Object[]> reportesUsuario(String numeroLicencia, String localidad);

    List<Object[]> reportesUsuario(String numeroLicencia, String localidad, String fecha);
    
    double costo(ReservaPK llave, String accion);

    List<Object[]> reporteTotal(String fecha);

    List<Object[]> reporteLocalidad(String localidad, String fecha);

    List<Object[]> reportesParqueadero(String nombreParqueadero, String fecha);
    
    List<Object[]> reporteTotal(String fechaInicio, String fechaFin);

    List<Object[]> reporteLocalidad(String localidad, String fechaInicio, String fechaFin);

    List<Object[]> reportesParqueadero(String nombreParqueadero, String fechaInicio, String fechaFin);
    
    List<Object[]> reporteHorario(String nombreParqueadero, String fecha, String tipo);
    
    List<Object[]> historialParqueadero(String nit);
    
    List<Object[]> pendientesParqueadero(String nit) ;

}
