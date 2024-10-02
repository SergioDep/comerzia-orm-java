package pe.edu.pucp.comerzia.RelacionesComerciales.dao;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Empresa;

public interface EmpresaDAO {
    
    public Integer insertar(Empresa empresa);
    
    public Integer modificar(Empresa empresa);
    
    public Integer eliminar(Empresa empresa);
    
    public ArrayList<Empresa> listarTodos();
    
    public Empresa obtenerPorId(Integer idEmpresa);
    
}