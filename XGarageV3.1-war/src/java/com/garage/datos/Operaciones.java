/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.datos;

import java.util.List;

/**
 *
 * @author Diego
 */
public class Operaciones {
    
    /**/    
    public Operaciones (){
        
    }
    
    public String[] getLocalidades(){
        String localidades [] ={ "Usaquen", "Chapinero", "Santa Fe", "Sancristobal", "Usme", "Tunjuelito", "Bosa",
                "Kennedy", "Fontibon", "Engativa", "Suba", "Barrios Unidos", "Teusaquillo", "Los Mártires",
                "Antonio Nariño", "Puente Aranda", "La Candelaria", "Rafael Uribe Uribe", "Ciudad Bolivar",
                "Sumapaz"};
        return localidades;
    }
    
    public String[] getTipoParqueadero(){
        String tipoParqueadero[] = {"Alturas o Subterraneos", "Concreto, Asfalto o Gravilla",
                "Piso en Afirmado o Cesped"};
        return tipoParqueadero;
    }
    
    public String[] getTipoPlaza(){
        String tipoPlaza[] = {"Ligeros", "Pesados", "Especial o Agricola"};
        return tipoPlaza;
    }
    
    public String transformarLista (List<Object[]> datos){
        String resultado="";
        for (int i = 0; i < datos.size(); i++) {
            for (int j = 0; j < datos.get(i).length; j++) {
                if (j != datos.get(i).length - 1) {
                    resultado += datos.get(i)[j] + "$";
                } else {
                    resultado += datos.get(i)[j];
                }
            }
            if (i != datos.size() - 1) {
                resultado += "%";
            }
        }
        return resultado;
    }
    
}
