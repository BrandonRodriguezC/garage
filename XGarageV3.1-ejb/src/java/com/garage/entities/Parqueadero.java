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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "PARQUEADERO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parqueadero.findAll", query = "SELECT p FROM Parqueadero p")
    , @NamedQuery(name = "Parqueadero.findByNit", query = "SELECT p FROM Parqueadero p WHERE p.nit = :nit")
    , @NamedQuery(name = "Parqueadero.findByNombre", query = "SELECT p FROM Parqueadero p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Parqueadero.findByCiudad", query = "SELECT p FROM Parqueadero p WHERE p.ciudad = :ciudad")
    , @NamedQuery(name = "Parqueadero.findByDireccion", query = "SELECT p FROM Parqueadero p WHERE p.direccion = :direccion")
    , @NamedQuery(name = "Parqueadero.findByLatitud", query = "SELECT p FROM Parqueadero p WHERE p.latitud = :latitud")
    , @NamedQuery(name = "Parqueadero.findByLongitud", query = "SELECT p FROM Parqueadero p WHERE p.longitud = :longitud")
    , @NamedQuery(name = "Parqueadero.findByLocalidad", query = "SELECT p FROM Parqueadero p WHERE p.localidad = :localidad")})
public class Parqueadero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NIT")
    private String nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CIUDAD")
    private String ciudad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DIRECCION")
    private String direccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LATITUD")
    private Double latitud;
    @Column(name = "LONGITUD")
    private Double longitud;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "LOCALIDAD")
    private String localidad;
    @JoinColumn(name = "TIPOPARQUEADERO", referencedColumnName = "TIPOPARQUEADERO")
    @ManyToOne(optional = false)
    private Precio tipoparqueadero;
    @JoinColumn(name = "USUARIO", referencedColumnName = "USUARIO")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nit")
    private Collection<Plaza> plazaCollection;

     public Parqueadero() {
    }

    public Parqueadero(String nit) {
        this.nit = nit;
    }

    public Parqueadero(String nit, String nombre, String ciudad, String direccion, String localidad) {
        this.nit = nit;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.localidad = localidad;
    }

        public Parqueadero(Usuario usuario, String nit, String nombre, Precio tipoParqueadero, double longitud, double latitud, String ciudad, String direccion, String Localidad) {
        this.usuario = usuario;
        this.nit = nit;
        this.nombre = nombre;
        this.longitud=longitud;
        this.latitud=latitud;
        this.tipoparqueadero=tipoParqueadero;
        this.ciudad=ciudad;
        this.direccion=direccion;
        this.localidad=Localidad;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Precio getTipoparqueadero() {
        return tipoparqueadero;
    }

    public void setTipoparqueadero(Precio tipoparqueadero) {
        this.tipoparqueadero = tipoparqueadero;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<Plaza> getPlazaCollection() {
        return plazaCollection;
    }

    public void setPlazaCollection(Collection<Plaza> plazaCollection) {
        this.plazaCollection = plazaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nit != null ? nit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parqueadero)) {
            return false;
        }
        Parqueadero other = (Parqueadero) object;
        if ((this.nit == null && other.nit != null) || (this.nit != null && !this.nit.equals(other.nit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.garage.entities.Parqueadero[ nit=" + nit + " ]";
    }
    
}
