/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garage.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "RESERVA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")
    , @NamedQuery(name = "Reserva.findByFecha", query = "SELECT r FROM Reserva r WHERE r.reservaPK.fecha = :fecha")
    , @NamedQuery(name = "Reserva.findByPlazaid", query = "SELECT r FROM Reserva r WHERE r.reservaPK.plazaid = :plazaid")
    , @NamedQuery(name = "Reserva.findByActivo", query = "SELECT r FROM Reserva r WHERE r.activo = :activo")
    , @NamedQuery(name = "Reserva.findByModelo", query = "SELECT r FROM Reserva r WHERE r.modelo = :modelo")
    , @NamedQuery(name = "Reserva.findByPlaca", query = "SELECT r FROM Reserva r WHERE r.placa = :placa")
    , @NamedQuery(name = "Reserva.findByFechareserva", query = "SELECT r FROM Reserva r WHERE r.fechareserva = :fechareserva")
    , @NamedQuery(name = "Reserva.findByFechaentrada", query = "SELECT r FROM Reserva r WHERE r.fechaentrada = :fechaentrada")
    , @NamedQuery(name = "Reserva.findByFechasalida", query = "SELECT r FROM Reserva r WHERE r.fechasalida = :fechasalida")
    , @NamedQuery(name = "Reserva.findByValor", query = "SELECT r FROM Reserva r WHERE r.valor = :valor")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservaPK reservaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVO")
    private Boolean activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MODELO")
    private String modelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PLACA")
    private String placa;
    @Size(max = 30)
    @Column(name = "FECHARESERVA")
    private String fechareserva;
    @Size(max = 30)
    @Column(name = "FECHAENTRADA")
    private String fechaentrada;
    @Size(max = 30)
    @Column(name = "FECHASALIDA")
    private String fechasalida;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR")
    private Double valor;
    @JoinColumn(name = "NUMEROLICENCIA", referencedColumnName = "NUMEROLICENCIA")
    @ManyToOne
    private Cliente numerolicencia;
    @JoinColumn(name = "PLAZAID", referencedColumnName = "PLAZAID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Plaza plaza;

    public Reserva() {
    }

    public Reserva(ReservaPK reservaPK) {
        this.reservaPK = reservaPK;
    }

    public Reserva(ReservaPK reservaPK, Boolean activo, String modelo, String placa) {
        this.reservaPK = reservaPK;
        this.activo = activo;
        this.modelo = modelo;
        this.placa = placa;
    }

    public Reserva(String fecha, String plazaid) {
        this.reservaPK = new ReservaPK(fecha, plazaid);
    }

    public Reserva(ReservaPK reservaPK, Cliente numeroLicencia, String modelo, String placa, Boolean activo) {
        this.reservaPK = reservaPK;
        this.numerolicencia = numeroLicencia;
        this.activo = activo;
        this.modelo = modelo;
        this.placa = placa;
    }
    
    public Reserva(ReservaPK reservaPK, Cliente numeroLicencia, String modelo, String placa, Boolean activo, String fechaReserva) {
        this.reservaPK = reservaPK;
        this.numerolicencia = numeroLicencia;
        this.activo = activo;
        this.modelo = modelo;
        this.placa = placa;
        this.fechareserva = fechaReserva;
    }

    public ReservaPK getReservaPK() {
        return reservaPK;
    }

    public void setReservaPK(ReservaPK reservaPK) {
        this.reservaPK = reservaPK;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getFechareserva() {
        return fechareserva;
    }

    public void setFechareserva(String fechareserva) {
        this.fechareserva = fechareserva;
    }

    public String getFechaentrada() {
        return fechaentrada;
    }

    public void setFechaentrada(String fechaentrada) {
        this.fechaentrada = fechaentrada;
    }

    public String getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(String fechasalida) {
        this.fechasalida = fechasalida;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Cliente getNumerolicencia() {
        return numerolicencia;
    }

    public void setNumerolicencia(Cliente numerolicencia) {
        this.numerolicencia = numerolicencia;
    }

    public Plaza getPlaza() {
        return plaza;
    }

    public void setPlaza(Plaza plaza) {
        this.plaza = plaza;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservaPK != null ? reservaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.reservaPK == null && other.reservaPK != null) || (this.reservaPK != null && !this.reservaPK.equals(other.reservaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.garage.entities.Reserva[ reservaPK=" + reservaPK + " ]";
    }
    
}
