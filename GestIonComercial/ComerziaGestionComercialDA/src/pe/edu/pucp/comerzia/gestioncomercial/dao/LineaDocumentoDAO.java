package pe.edu.pucp.comerzia.gestioncomercial.dao;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.gestioncomercial.model.LineaDocumento;

public interface LineaDocumentoDAO {
    public Integer insertar(LineaDocumento lineaDocumento);
    
    public Integer modificar(LineaDocumento lineaDocumento);
    
    public Integer eliminar(LineaDocumento lineaDocumento);
    
    public ArrayList<LineaDocumento> listarTodos();
    
    public LineaDocumento obtenerPorId(Integer idLineaDocumento);
}
