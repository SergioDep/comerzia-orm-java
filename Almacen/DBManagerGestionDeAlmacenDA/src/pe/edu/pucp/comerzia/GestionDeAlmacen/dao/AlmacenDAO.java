package pe.edu.pucp.comerzia.GestionDeAlmacen.dao;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Almacen;

public interface AlmacenDAO {

    public Integer insertar(Almacen almacen);

    public Integer modificar(Almacen almacen);

    public Integer eliminar(Almacen almacen);

    public ArrayList<Almacen> listarTodos();

    public Almacen obtenerPorId(Integer idAlmacen);
}