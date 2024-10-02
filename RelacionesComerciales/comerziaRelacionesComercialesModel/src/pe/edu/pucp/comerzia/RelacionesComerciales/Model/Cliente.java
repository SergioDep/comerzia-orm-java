package pe.edu.pucp.comerzia.RelacionesComerciales.Model;

import java.util.Date;

public class Cliente {

    private Integer idCliente;
    private static Integer id_correlativo = 1;
    private Integer idEmpresa; // El ID de la clase padre.
    private Date fechaRegistro;
    private Date fechaUltimaCompra;

    public Cliente(Integer idCliente, Integer idEmpresa, Date fechaRegistro, Date fechaUltimaCompra) {
        this.idCliente = idCliente;
        this.idEmpresa = idEmpresa;
        this.fechaRegistro = fechaRegistro;
        this.fechaUltimaCompra = fechaUltimaCompra;
    }

    public Cliente(Integer idEmpresa, Date fechaRegistro, Date fechaUltimaCompra) {
        this.idCliente = id_correlativo;
        this.idEmpresa = idEmpresa;
        this.fechaRegistro = fechaRegistro;
        this.fechaUltimaCompra = fechaUltimaCompra;
        id_correlativo++;
    }

    // null
    public Cliente() {
        this.idCliente = null;
        this.idEmpresa = null;
        this.fechaRegistro = null;
        this.fechaUltimaCompra = null;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaUltimaCompra() {
        return fechaUltimaCompra;
    }

    public void setFechaUltimaCompra(Date fechaUltimaCompra) {
        this.fechaUltimaCompra = fechaUltimaCompra;
    }

}
