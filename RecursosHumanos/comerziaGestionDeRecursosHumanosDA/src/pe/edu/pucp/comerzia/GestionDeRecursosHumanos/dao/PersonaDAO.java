package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;

public interface PersonaDAO {
    public Integer insertar(Persona persona);
    
    public Integer modificar(Persona persona);
    
    public Integer eliminar(Persona persona);
    
    public ArrayList<Persona> listarTodos();
    
    public Persona obtenerPorId(Integer idPersona);
}
