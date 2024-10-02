package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model;

import java.util.Date;

public class Empleado {

    private Integer idEmpleado;
    private static Integer idCorrelativo = 1;

    private Integer idPersona;
    private EstadoEmpleado estado;
    private String nombreUsuario;
    private String contrasenha;
    private Double salario;
    private Date fechaContratacion;

    public Empleado(Integer idEmpleado, Integer idPersona, EstadoEmpleado estado, String nombreUsuario, String contrasenha, Double salario, Date fechaContratacion) {
        this.idEmpleado = idEmpleado;
        this.idPersona = idPersona;
        this.estado = estado;
        this.nombreUsuario = nombreUsuario;
        this.contrasenha = contrasenha;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
    }

    public Empleado(Integer idPersona, EstadoEmpleado estado, String nombreUsuario, String contrasenha, Double salario, Date fechaContratacion) {
        this.idEmpleado = idCorrelativo++;
        this.idPersona = idPersona;
        this.estado = estado;
        this.nombreUsuario = nombreUsuario;
        this.contrasenha = contrasenha;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
    }

    // nulls
    public Empleado() {
        this.idEmpleado = null;
        this.idPersona = null;
        this.estado = null;
        this.nombreUsuario = null;
        this.contrasenha = null;
        this.salario = null;
        this.fechaContratacion = null;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public EstadoEmpleado getEstado() {
        return estado;
    }

    public void setEstado(EstadoEmpleado estado) {
        this.estado = estado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }
}
