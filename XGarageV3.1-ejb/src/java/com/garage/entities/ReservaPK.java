/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Diego
 */
@Embeddable
public class ReservaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "FECHA")
    private String fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PLAZAID")
    private String plazaid;

    public ReservaPK() {
    }

    public ReservaPK(String fecha, String plazaid) {
        this.fecha = fecha;
        this.plazaid = plazaid;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPlazaid() {
        return plazaid;
    }

    public void setPlazaid(String plazaid) {
        this.plazaid = plazaid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (plazaid != null ? plazaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaPK)) {
            return false;
        }
        ReservaPK other = (ReservaPK) object;
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if ((this.plazaid == null && other.plazaid != null) || (this.plazaid != null && !this.plazaid.equals(other.plazaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.garage.entities.ReservaPK[ fecha=" + fecha + ", plazaid=" + plazaid + " ]";
    }
    
}
