package pe.edu.pucp.comerzia.GestionDeAlmacen.dao;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.ProductoAlmacenado;

public interface ProductoAlmacenadoDAO {
    
    public Integer insertar(ProductoAlmacenado productoAlmacenado);

    public Integer modificar(ProductoAlmacenado productoAlmacenado);

    public Integer eliminar(ProductoAlmacenado productoAlmacenado);

    public ArrayList<ProductoAlmacenado> listarTodos();

    public ProductoAlmacenado obtenerPorId(Integer idProductoAlmacenado);
}
