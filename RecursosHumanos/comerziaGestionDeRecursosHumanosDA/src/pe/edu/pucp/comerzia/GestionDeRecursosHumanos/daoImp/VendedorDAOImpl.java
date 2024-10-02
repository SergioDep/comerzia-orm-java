package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.VendedorDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Vendedor;
import pe.edu.pucp.comerzia.db.DAOImpl;

/*

public class Vendedor {

    private Integer idVendedor;
    private static Integer idCorrelativo = 1;

    private Integer idEmpleado;
    // private Empleado empleado;

    private Double ingresosVentas;
    private Double porcentajeComision;
 */
public class VendedorDAOImpl extends DAOImpl implements VendedorDAO {

    private Vendedor vendedor;

    public VendedorDAOImpl() {
        super("Vendedor");
        this.vendedor = null;
    }

    @Override
    public Integer insertar(Vendedor vendedor) {
        this.retornarLlavePrimaria = true;
        this.vendedor = vendedor;
        Integer id = insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsert() {
        // return "nombre, estado, descripcion";
        return "idEmpleado, ingresosVentas, porcentajeComision";
    }

    @Override
    protected String obtenerListaDeValoresParaInsert() {
        String sql = "";
        // sql = sql.concat("'" + this.vendedor.getNombre() + "'");
        // sql = sql.concat(", ");
        // sql = sql.concat("'" + this.vendedor.getEstado() + "'");
        // sql = sql.concat(", ");
        // sql = sql.concat("'" + this.vendedor.getDescripcion() + "'");
        sql = sql.concat(this.vendedor.getIdEmpleado().toString());
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.vendedor.getIngresosVentas() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.vendedor.getPorcentajeComision() + "'");

        return sql;
    }

    @Override
    public Integer modificar(Vendedor vendedor) {
        this.vendedor = vendedor;
        return modificar();
    }

    @Override
    public Integer eliminar(Vendedor vendedor) {
        this.vendedor = vendedor;
        return eliminar();
    }

    @Override
    public ArrayList<Vendedor> listarTodos() {
        ArrayList<Vendedor> lista = new ArrayList<>();
        listar();
        try {
            while (this.resultset.next()) {
                lista.add(generaVendedorResult());
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendedorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(VendedorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public Vendedor obtenerPorId(Integer idVendedor) {
        obtener_Por_Id(idVendedor);
        try {
            return generaVendedorResult();
        } catch (SQLException ex) {
            Logger.getLogger(VendedorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Vendedor generaVendedorResult() throws SQLException {
        Vendedor vendedor_local = new Vendedor();
        // vendedor_local.setIdVendedor(this.resultset.getInt("idVendedor"));
        // vendedor_local.setNombre(this.resultset.getString("nombre"));
        // vendedor_local.setEstado(this.resultset.getString("estado"));
        // vendedor_local.setDescripcion(this.resultset.getString("descripcion"));
        vendedor_local.setIdVendedor(this.resultset.getInt("idVendedor"));
        vendedor_local.setIdEmpleado(this.resultset.getInt("idEmpleado"));
        vendedor_local.setIngresosVentas(this.resultset.getDouble("ingresosVentas"));
        vendedor_local.setPorcentajeComision(this.resultset.getDouble("porcentajeComision"));

        return vendedor_local;
    }

    @Override
    protected String obtenerID() {
        return vendedor.getIdVendedor().toString();
    }

    @Override
    protected String obtenerIDcategoria() {
        return "idVendedor";
    }

    @Override
    protected String obtenerListaDeAtributosYValoresParaActualizacion() {
        String sql = " idVendedor = ";
        // sql = sql.concat(this.vendedor.getIdVendedor().toString());
        // sql = sql.concat(", nombre = ");
        // sql = sql.concat("'" + this.vendedor.getNombre() + "'");
        // sql = sql.concat(", estado = ");
        // sql = sql.concat("'" + this.vendedor.getEstado() + "'");
        // sql = sql.concat(", descripcion = ");
        // sql = sql.concat("'" + this.vendedor.getDescripcion() + "'");
        sql = sql.concat(this.vendedor.getIdVendedor().toString());
        sql = sql.concat(", idEmpleado = ");
        sql = sql.concat(this.vendedor.getIdEmpleado().toString());
        sql = sql.concat(", ingresosVentas = ");
        sql = sql.concat(this.vendedor.getIngresosVentas().toString());
        sql = sql.concat(", porcentajeComision = ");
        sql = sql.concat(this.vendedor.getPorcentajeComision().toString());

        return sql;
    }
}
