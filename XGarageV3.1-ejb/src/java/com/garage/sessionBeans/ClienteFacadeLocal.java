/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.sessionBeans;

import com.garage.entities.Cliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Diego
 */
@Local
public interface ClienteFacadeLocal {

    void create(Cliente cliente);

    void edit(Cliente cliente);

    void remove(String numeroLicencia);

    Cliente find(String numeroLicencia);

    List<Cliente> findAll();

    List<Cliente> findRange(int[] range);

    int count();

    Cliente getCliente(String usuario);

    List<Object[]> parqueaderoCercanos(String localidad, String tipoDeAuto, String fecha);

    List<Object[]> reservasActivas(String numeroLicencia);

    List<Object[]> plazasDisponibles(String fecha, String tipoDeAuto, String nit);

}
