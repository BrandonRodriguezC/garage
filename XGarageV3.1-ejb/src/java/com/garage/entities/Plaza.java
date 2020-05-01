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
@Table(name = "PLAZA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plaza.findAll", query = "SELECT p FROM Plaza p")
    , @NamedQuery(name = "Plaza.findByPlazaid", query = "SELECT p FROM Plaza p WHERE p.plazaid = :plazaid")
    , @NamedQuery(name = "Plaza.findByTipodeauto", query = "SELECT p FROM Plaza p WHERE p.tipodeauto = :tipodeauto")})
public class Plaza implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PLAZAID")
    private String plazaid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TIPODEAUTO")
    private String tipodeauto;
    @JoinColumn(name = "NIT", referencedColumnName = "NIT")
    @ManyToOne(optional = false)
    private Parqueadero nit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plaza")
    private Collection<Reserva> reservaCollection;

    public Plaza() {
    }

    public Plaza(String plazaid) {
        this.plazaid = plazaid;
    }

    public Plaza(String plazaid, String tipodeauto) {
        this.plazaid = plazaid;
        this.tipodeauto = tipodeauto;
    }

    public Plaza(Parqueadero parqueadero, String plazaid, String tipodeauto) {
        this.nit = parqueadero;
        this.plazaid = plazaid;
        this.tipodeauto = tipodeauto;

    }

    public String getPlazaid() {
        return plazaid;
    }

    public void setPlazaid(String plazaid) {
        this.plazaid = plazaid;
    }

    public String getTipodeauto() {
        return tipodeauto;
    }

    public void setTipodeauto(String tipodeauto) {
        this.tipodeauto = tipodeauto;
    }

    public Parqueadero getNit() {
        return nit;
    }

    public void setNit(Parqueadero nit) {
        this.nit = nit;
    }

    @XmlTransient
    public Collection<Reserva> getReservaCollection() {
        return reservaCollection;
    }

    public void setReservaCollection(Collection<Reserva> reservaCollection) {
        this.reservaCollection = reservaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plazaid != null ? plazaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plaza)) {
            return false;
        }
        Plaza other = (Plaza) object;
        if ((this.plazaid == null && other.plazaid != null) || (this.plazaid != null && !this.plazaid.equals(other.plazaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.garage.entities.Plaza[ plazaid=" + plazaid + " ]";
    }
    
}
