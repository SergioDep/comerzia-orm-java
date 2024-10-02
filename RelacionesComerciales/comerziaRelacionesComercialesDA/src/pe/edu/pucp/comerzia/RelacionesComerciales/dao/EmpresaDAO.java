/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.comerzia.RelacionesComerciales.dao;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Empresa;

/**
 *
 * @author camilo
 */
public interface EmpresaDAO {
    
    public Integer insertar(Empresa proveedor);
    
    public Integer modificar(Empresa proveedor);
    
    public Integer eliminar(Empresa proveedor);
    
    public ArrayList<Empresa> listarTodos();
    
    public Empresa obtenerPorId(Integer idEmpresa);
    
}