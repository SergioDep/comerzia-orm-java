/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.RelacionesComerciales.Model;

import java.sql.Time;
import java.util.Date;


public class Visita {
    private Integer idVisita;
    private Date fecha;
    private Time duracion;
    private Integer idEmpresa;
    private Integer idPersona;
      
    // Constructores.

    public Visita() {
    }

    public Visita(Integer idVisita, Date fecha, Time duracion, Integer idEmpresa, Integer idPersona) {
        this.idVisita = idVisita;
        this.fecha = fecha;
        this.duracion = duracion;
        this.idEmpresa = idEmpresa;
        this.idPersona = idPersona;
    }
    
    // Getters y Setters.

    public Integer getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(Integer idVisita) {
        this.idVisita = idVisita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }
    
    

   
    
}