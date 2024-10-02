package pe.edu.pucp.comerzia.GestionDeAlmacen.daoImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeAlmacen.dao.ProductoDAO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Producto;
import pe.edu.pucp.comerzia.db.DAOImpl;

public class ProductoDAOImpl extends DAOImpl implements ProductoDAO {
    private Producto producto;

    public ProductoDAOImpl() {
        super("Producto");
        this.producto = null;
    }

    @Override
    public Integer insertar(Producto producto) {
        this.retornarLlavePrimaria = true;
        this.producto = producto;
        Integer id = insertar();
        System.out.print(id);
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsert() {
        return "nombreProducto, precio, stockMinimo";
    }

    @Override
    protected String obtenerListaDeValoresParaInsert() {
        String sql = "";
        sql = sql.concat("'" + this.producto.getNombreProducto() + "'");
        sql = sql.concat(", ");
        sql = sql.concat(this.producto.getPrecio().toString());
        sql = sql.concat(", ");
        sql = sql.concat(this.producto.getStockMinimo().toString());
        return sql;
    }

    @Override
    public Integer modificar(Producto producto) {
        this.producto = producto;
        return modificar();
    }

    @Override
    public Integer eliminar(Producto producto) {
        this.producto = producto;
        return eliminar();
    }

    @Override
    public ArrayList<Producto> listarTodos() {
        ArrayList<Producto> lista = new ArrayList<>();
        listar();
        try {
            while (this.resultset.next())
                lista.add(generaProductoResult());
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public Producto obtenerPorId(Integer idProducto) {
        obtener_Por_Id(idProducto);
        return generaProductoResult();
    }

    private Producto generaProductoResult() {
        Producto producto_local = new Producto ();
        try {
            producto_local.setIdProducto(this.resultset.getInt("idProducto"));
            producto_local.setNombreProducto(this.resultset.getString("nombreProducto"));
            producto_local.setPrecio(this.resultset.getDouble("precio"));
            producto_local.setStockMinimo(this.resultset.getInt("stockMinimo"));
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return producto_local;
    }

    @Override
    protected String obtenerID() {
        return producto.getIdProducto().toString();
    }

    @Override
    protected String obtenerIDcategoria() {
        return "idProducto";
    }

    @Override
    protected String obtenerListaDeAtributosYValoresParaActualizacion() {
        String sql = " idProducto = ";
        sql = sql.concat(this.producto.getIdProducto().toString()); 
        sql = sql.concat(", nombreProducto = ");
        sql = sql.concat("'" + this.producto.getNombreProducto() + "'");
        sql = sql.concat(", precio = ");
        sql = sql.concat(this.producto.getPrecio().toString());
        sql = sql.concat(", stockMinimo = ");
        sql = sql.concat(this.producto.getStockMinimo().toString());
        return sql;
    }
    
}