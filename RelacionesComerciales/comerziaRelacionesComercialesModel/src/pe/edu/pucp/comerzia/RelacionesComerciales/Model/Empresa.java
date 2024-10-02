package pe.edu.pucp.comerzia.RelacionesComerciales.Model;

import java.util.Date;



public class Empresa { // Padre
    
    // Atributos
    private Integer idEmpresa;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String tipoIndustria;
    private TipoEmpresa tipo;
    
    // Atributos de proveedor
    private Date fecha_afiliacion;
    private String RUC;
    private String razonSocial;
    private Double calificacion;
    private String pais;
    
    // Atributos de cliente
    private Date fechaRegistro;
    private Date fechaUltimaCompra;

    // Constructores
    public Empresa() {
        this.idEmpresa = null;
        this.nombre = null;
        this.direccion = null;
        this.telefono = null;
        this.email = null;
        this.tipoIndustria = null;
        this.tipo = null;
        this.fecha_afiliacion = null;
        this.RUC = null;
        this.razonSocial = null;
        this.calificacion = null;
        this.pais = null;
        this.fechaRegistro = null;
        this.fechaUltimaCompra = null;
    }
    
    

    public Empresa(Integer idEmpresa, String nombre, String direccion, String telefono, String email, String tipoIndustria, TipoEmpresa tipo, Date fecha_afiliacion, String RUC, String razonSocial, Double calificacion, String pais, Date fechaRegistro, Date fechaUltimaCompra) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.tipoIndustria = tipoIndustria;
        this.tipo = tipo;
        this.fecha_afiliacion = fecha_afiliacion;
        this.RUC = RUC;
        this.razonSocial = razonSocial;
        this.calificacion = calificacion;
        this.pais = pais;
        this.fechaRegistro = fechaRegistro;
        this.fechaUltimaCompra = fechaUltimaCompra;
    }
    
    
    
    // Empresa proveedora
    public Empresa(Integer idEmpresa, String nombre, String direccion, String telefono, String email, String tipoIndustria, TipoEmpresa tipo, Date fecha_afiliacion, String RUC, String razonSocial, Double calificacion, String pais) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.tipoIndustria = tipoIndustria;
        this.tipo = tipo;
        this.fecha_afiliacion = fecha_afiliacion;
        this.RUC = RUC;
        this.razonSocial = razonSocial;
        this.calificacion = calificacion;
        this.pais = pais;
        // Atributos de cliente no aplican aquí
        this.fechaRegistro = null;
        this.fechaUltimaCompra = null;
    }
    
    // Empresa cliente
    public Empresa(Integer idEmpresa, String nombre, String direccion, String telefono, String email, String tipoIndustria, TipoEmpresa tipo, String pais, Date fechaRegistro, Date fechaUltimaCompra) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.tipoIndustria = tipoIndustria;
        this.tipo = tipo;
        this.pais = pais;
        this.fechaRegistro = fechaRegistro;
        this.fechaUltimaCompra = fechaUltimaCompra;
        // Atributos de proveedor no aplican aquí
        this.fecha_afiliacion = null;
        this.RUC = null;
        this.razonSocial = null;
        this.calificacion = null;
    }
    
    
    // Getters y Setters.

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoIndustria() {
        return tipoIndustria;
    }

    public void setTipoIndustria(String tipoIndustria) {
        this.tipoIndustria = tipoIndustria;
    }

    public TipoEmpresa getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmpresa tipo) {
        this.tipo = tipo;
    }

    public Date getFecha_afiliacion() {
        return fecha_afiliacion;
    }

    public void setFecha_afiliacion(Date fecha_afiliacion) {
        this.fecha_afiliacion = fecha_afiliacion;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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