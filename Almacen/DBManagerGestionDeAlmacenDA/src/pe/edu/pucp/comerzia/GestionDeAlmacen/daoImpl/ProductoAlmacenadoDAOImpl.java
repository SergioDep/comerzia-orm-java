package pe.edu.pucp.comerzia.GestionDeAlmacen.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeAlmacen.dao.ProductoAlmacenadoDAO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.ProductoAlmacenado;
import pe.edu.pucp.comerzia.db.DAOImpl;

public class ProductoAlmacenadoDAOImpl extends DAOImpl implements ProductoAlmacenadoDAO {

    private ProductoAlmacenado productoAlmacenado;

    public ProductoAlmacenadoDAOImpl() {
        super("ProductoAlmacenado");
        this.productoAlmacenado = null;
    }

    @Override
    public Integer insertar(ProductoAlmacenado productoAlmacenado) {

        this.retornarLlavePrimaria = true;
        this.productoAlmacenado = productoAlmacenado;
        Integer id = insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsert() {
        return "idProducto, idAlmacen, fechaAlmacenado, stockActual";
    }

    @Override
    protected String obtenerListaDeValoresParaInsert() {
        String sql = "";
        sql = sql.concat("'" + this.productoAlmacenado.getIdProducto().toString() + "', ");
        sql = sql.concat("'" + this.productoAlmacenado.getIdAlmacen().toString() + "', ");
        sql = sql.concat("'" + new java.sql.Date(this.productoAlmacenado.getFechaAlmacenado().getTime()).toString() + "', ");
        sql = sql.concat(this.productoAlmacenado.getStockActual().toString());
        return sql;
    }

    @Override
    public Integer modificar(ProductoAlmacenado productoAlmacenado) {
        this.productoAlmacenado = productoAlmacenado;
        return modificar();
    }

    @Override
    public Integer eliminar(ProductoAlmacenado productoAlmacenado) {
        this.productoAlmacenado = productoAlmacenado;
        return eliminar();
    }

    @Override
    public ArrayList<ProductoAlmacenado> listarTodos() {
        ArrayList<ProductoAlmacenado> lista = new ArrayList<>();
        listar();
        try {
            while (this.resultset.next()) {
                lista.add(generaProductoAlmacenadoResult());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoAlmacenadoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public ProductoAlmacenado obtenerPorId(Integer idProductoAlmacenado) {
        obtener_Por_Id(idProductoAlmacenado);
        return this.productoAlmacenado;
    }

    @Override
    protected void generarObjetoResultado() {
        try {
            if (!this.resultset.next()) {
                this.productoAlmacenado = null;
            } else {
                this.productoAlmacenado = this.generaProductoAlmacenadoResult();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoAlmacenadoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ProductoAlmacenado generaProductoAlmacenadoResult() {
        ProductoAlmacenado productoAlmacenadoLocal = new ProductoAlmacenado();

        try {
            productoAlmacenadoLocal.setIdProductoAlmacenado(this.resultset.getInt("idProductoAlmacenado"));
            productoAlmacenadoLocal.setIdProducto(this.resultset.getInt("idProducto"));
            productoAlmacenadoLocal.setIdAlmacen(this.resultset.getInt("idAlmacen"));
            productoAlmacenadoLocal.setFechaAlmacenado(this.resultset.getDate("fechaAlmacenado"));
            productoAlmacenadoLocal.setStockActual(this.resultset.getInt("stockActual"));
            if (this.resultset.getBoolean("eliminado") == true) {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoAlmacenadoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productoAlmacenadoLocal;
    }

    @Override
    protected String obtenerID() {
        return Integer.toString(productoAlmacenado.getIdProductoAlmacenado());
    }

    @Override
    protected String obtenerIDcategoria() {
        return "idProductoAlmacenado";
    }

    @Override
    protected String obtenerListaDeAtributosYValoresParaActualizacion() {
        /*String sql = " idProductoAlmacenado = ";
        sql = sql.concat(this.productoAlmacenado.getIdProductoAlmacenado().toString());       
        sql += "idProducto = " + this.productoAlmacenado.getIdProducto().toString() + ", ";
        sql += "idAlmacen = " + this.productoAlmacenado.getIdAlmacen().toString() + ", ";
        sql += "fechaAlmacenado = '" + new java.sql.Date(this.productoAlmacenado.getFechaAlmacenado().getTime()).toString() + "', ";
        sql += "stockActual = " + this.productoAlmacenado.getStockActual().toString();*/
        String sql = " idProductoAlmacenado = ";
        sql = sql.concat(this.productoAlmacenado.getIdProductoAlmacenado().toString());
        sql = sql.concat(", idProducto = ");
        sql = sql.concat(this.productoAlmacenado.getIdProducto().toString());
        sql = sql.concat(", idAlmacen = ");
        sql = sql.concat(this.productoAlmacenado.getIdAlmacen().toString());
        sql = sql.concat(", fechaAlmacenado = ");
        sql = sql.concat("'" + new java.sql.Date(this.productoAlmacenado.getFechaAlmacenado().getTime()).toString() + "'");
        sql = sql.concat(", stockActual = ");
        sql = sql.concat(this.productoAlmacenado.getStockActual().toString());
        return sql;
    }
}
