/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "CLIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByNumerolicencia", query = "SELECT c FROM Cliente c WHERE c.numerolicencia = :numerolicencia")
    , @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cliente.findByApellido", query = "SELECT c FROM Cliente c WHERE c.apellido = :apellido")
    , @NamedQuery(name = "Cliente.findByDocumentoidentidad", query = "SELECT c FROM Cliente c WHERE c.documentoidentidad = :documentoidentidad")
    , @NamedQuery(name = "Cliente.findByTipodocumento", query = "SELECT c FROM Cliente c WHERE c.tipodocumento = :tipodocumento")
    , @NamedQuery(name = "Cliente.findByCorreo", query = "SELECT c FROM Cliente c WHERE c.correo = :correo")
    , @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NUMEROLICENCIA")
    private String numerolicencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "APELLIDO")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DOCUMENTOIDENTIDAD")
    private String documentoidentidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "TIPODOCUMENTO")
    private String tipodocumento;
    @Size(max = 50)
    @Column(name = "CORREO")
    private String correo;
    @Size(max = 10)
    @Column(name = "TELEFONO")
    private String telefono;
    @OneToMany(mappedBy = "numerolicencia")
    private Collection<Reserva> reservaCollection;
    @JoinColumn(name = "USUARIO", referencedColumnName = "USUARIO")
    @ManyToOne
    private Usuario usuario;

    public Cliente() {
    }

    public Cliente(String numerolicencia) {
        this.numerolicencia = numerolicencia;
    }

    public Cliente(String numerolicencia, String nombre, String apellido, String documentoidentidad, String tipodocumento) {
        this.numerolicencia = numerolicencia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documentoidentidad = documentoidentidad;
        this.tipodocumento = tipodocumento;
    }
    
    public Cliente(Usuario usuario, String numerolicencia, String nombre, String apellido, String documentoidentidad, String tipodocumento) {
        this.usuario = usuario;
        this.numerolicencia = numerolicencia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documentoidentidad = documentoidentidad;
        this.tipodocumento = tipodocumento;
    }

    public String getNumerolicencia() {
        return numerolicencia;
    }

    public void setNumerolicencia(String numerolicencia) {
        this.numerolicencia = numerolicencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumentoidentidad() {
        return documentoidentidad;
    }

    public void setDocumentoidentidad(String documentoidentidad) {
        this.documentoidentidad = documentoidentidad;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public Collection<Reserva> getReservaCollection() {
        return reservaCollection;
    }

    public void setReservaCollection(Collection<Reserva> reservaCollection) {
        this.reservaCollection = reservaCollection;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numerolicencia != null ? numerolicencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.numerolicencia == null && other.numerolicencia != null) || (this.numerolicencia != null && !this.numerolicencia.equals(other.numerolicencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.garage.entities.Cliente[ numerolicencia=" + numerolicencia + " ]";
    }
    
}
