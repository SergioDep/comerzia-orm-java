package pe.edu.pucp.comerzia.GestionDeAlmacen.dao;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Producto;

public interface ProductoDAO {
    
    public Integer insertar(Producto producto);

    public Integer modificar(Producto producto);

    public Integer eliminar(Producto producto);

    public ArrayList<Producto> listarTodos();

    public Producto obtenerPorId(Integer idProducto);
}
