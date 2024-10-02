package pe.edu.pucp.comerzia.gestioncomercial.dao;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.gestioncomercial.model.Documento;

public interface DocumentoDAO {
    public Integer insertar(Documento documento);
    
    public Integer modificar(Documento documento);
    
    public Integer eliminar(Documento documento);
    
    public ArrayList<Documento> listarTodos();
    
    public Documento obtenerPorId(Integer idDocumento);
}