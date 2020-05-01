/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "PRECIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Precio.findAll", query = "SELECT p FROM Precio p")
    , @NamedQuery(name = "Precio.findByTipoparqueadero", query = "SELECT p FROM Precio p WHERE p.tipoparqueadero = :tipoparqueadero")
    , @NamedQuery(name = "Precio.findByPreciominuto", query = "SELECT p FROM Precio p WHERE p.preciominuto = :preciominuto")
    , @NamedQuery(name = "Precio.findByPreciohora", query = "SELECT p FROM Precio p WHERE p.preciohora = :preciohora")
    , @NamedQuery(name = "Precio.findByPrecioreserva", query = "SELECT p FROM Precio p WHERE p.precioreserva = :precioreserva")
    , @NamedQuery(name = "Precio.findByPreciointerrupcion", query = "SELECT p FROM Precio p WHERE p.preciointerrupcion = :preciointerrupcion")})
public class Precio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TIPOPARQUEADERO")
    private String tipoparqueadero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIOMINUTO")
    private double preciominuto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIOHORA")
    private double preciohora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIORESERVA")
    private double precioreserva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIOINTERRUPCION")
    private double preciointerrupcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoparqueadero")
    private Collection<Parqueadero> parqueaderoCollection;

    public Precio() {
    }

    public Precio(String tipoparqueadero) {
        this.tipoparqueadero = tipoparqueadero;
    }

    public Precio(String tipoparqueadero, double preciominuto, double preciohora, double precioreserva, double preciointerrupcion) {
        this.tipoparqueadero = tipoparqueadero;
        this.preciominuto = preciominuto;
        this.preciohora = preciohora;
        this.precioreserva = precioreserva;
        this.preciointerrupcion = preciointerrupcion;
    }

    public String getTipoparqueadero() {
        return tipoparqueadero;
    }

    public void setTipoparqueadero(String tipoparqueadero) {
        this.tipoparqueadero = tipoparqueadero;
    }

    public double getPreciominuto() {
        return preciominuto;
    }

    public void setPreciominuto(double preciominuto) {
        this.preciominuto = preciominuto;
    }

    public double getPreciohora() {
        return preciohora;
    }

    public void setPreciohora(double preciohora) {
        this.preciohora = preciohora;
    }

    public double getPrecioreserva() {
        return precioreserva;
    }

    public void setPrecioreserva(double precioreserva) {
        this.precioreserva = precioreserva;
    }

    public double getPreciointerrupcion() {
        return preciointerrupcion;
    }

    public void setPreciointerrupcion(double preciointerrupcion) {
        this.preciointerrupcion = preciointerrupcion;
    }

    @XmlTransient
    public Collection<Parqueadero> getParqueaderoCollection() {
        return parqueaderoCollection;
    }

    public void setParqueaderoCollection(Collection<Parqueadero> parqueaderoCollection) {
        this.parqueaderoCollection = parqueaderoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoparqueadero != null ? tipoparqueadero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Precio)) {
            return false;
        }
        Precio other = (Precio) object;
        if ((this.tipoparqueadero == null && other.tipoparqueadero != null) || (this.tipoparqueadero != null && !this.tipoparqueadero.equals(other.tipoparqueadero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.garage.entities.Precio[ tipoparqueadero=" + tipoparqueadero + " ]";
    }
    
}
