/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.RelacionesComerciales.bo;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.EmpresaDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl.EmpresaDAOImpl;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Empresa;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.TipoEmpresa;

/**
 *
 * @author camilo
 */
public class EmpresaBO {
    
    // Creamos la clase.
    private EmpresaDAO empresaDAO; 
    
    public EmpresaBO(){
        this.empresaDAO = new EmpresaDAOImpl(); // Constructor
    }
    
    public Integer insertar(Integer idEmpresa, String nombre, String direccion, String telefono, String email, String tipoIndustria, TipoEmpresa tipo, Date fecha_afiliacion, String RUC, String razonSocial, Double calificacion, String pais,
            Date fechaRegistro, Date fechaUltimaCompra){
       Empresa empresa;
        if(fechaRegistro == null){ // Es proveedor
            empresa = new Empresa(idEmpresa, nombre, direccion, telefono, email, tipoIndustria, tipo, fecha_afiliacion, RUC, razonSocial, calificacion, pais);
        }
        else{
            empresa = new Empresa(idEmpresa, nombre, direccion, telefono, email, tipoIndustria, tipo, pais, fechaRegistro, fechaUltimaCompra);
        }
        return empresaDAO.insertar(empresa);
        
        
    }
    
    public Integer modificar(Integer idEmpresa, String nombre, String direccion, String telefono, String email, String tipoIndustria, TipoEmpresa tipo, Date fecha_afiliacion, String RUC, String razonSocial, Double calificacion, String pais,
            Date fechaRegistro, Date fechaUltimaCompra){
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(idEmpresa);
        empresa.setNombre(nombre);
        empresa.setDireccion(direccion);
        empresa.setTelefono(telefono);
        empresa.setEmail(email);
        empresa.setTipoIndustria(tipoIndustria);
        empresa.setTipo(tipo);
        empresa.setFecha_afiliacion(fecha_afiliacion);
        empresa.setRUC(RUC);
        empresa.setRazonSocial(razonSocial);
        empresa.setCalificacion(calificacion);
        empresa.setPais(pais);
        empresa.setFechaRegistro(fechaRegistro);
        empresa.setFechaUltimaCompra(fechaUltimaCompra);
        return this.empresaDAO.modificar(empresa);
        
    }
    
    public Integer eliminar(Empresa empresa){
        return this.empresaDAO.eliminar(empresa);
    }
    
    public Empresa obtenerPorId(Integer idEmpresa){
        return this.empresaDAO.obtenerPorId(idEmpresa); // Pendiente
    }
    
    public ArrayList<Empresa> listarTodos(){
        return this.empresaDAO.listarTodos();
    }
    
}