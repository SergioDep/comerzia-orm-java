/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.RelacionesComerciales.bo;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Proveedor;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.ProveedorDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl.ProveedorDAOImpl;

/**
 *
 * @author camilo
 */
public class ProveedorBO {
    
    // Creamos la clase.
    private ProveedorDAO proveedorDAO; 
    
    public ProveedorBO(){
        this.proveedorDAO = new ProveedorDAOImpl(); // Constructor
    }
    
    public Integer insertar(Integer idEmpresa, Date fecha_afiliacion, String RUC, String razonSocial, Double calificacion, String pais){
        Proveedor proveedor;
        proveedor = new Proveedor(idEmpresa, fecha_afiliacion, RUC, razonSocial, calificacion, pais);
        return proveedorDAO.insertar(proveedor);
        
        
    }
    
    public Integer modificar(Integer idEmpresa, Date fecha_afiliacion, String RUC, String razonSocial, Double calificacion, String pais){
        Proveedor Proveedor = new Proveedor();
        Proveedor.setFecha_afiliacion(fecha_afiliacion);
        Proveedor.setRUC(RUC);
        Proveedor.setRazonSocial(razonSocial);
        Proveedor.setCalificacion(calificacion);
        Proveedor.setPais(pais);
        return this.proveedorDAO.modificar(Proveedor);
        
    }
    
    public Integer eliminar(Proveedor Proveedor){
        return this.proveedorDAO.eliminar(Proveedor);
    }
    
    public Proveedor obtenerPorId(Integer idProveedor){
        return this.proveedorDAO.obtenerPorId(idProveedor); // Pendiente
    }
    
    public ArrayList<Proveedor> listarTodos(){
        return this.proveedorDAO.listarTodos();
    }
}
