package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.TrabajadorDeAlmacenDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.TrabajadorDeAlmacen;
import pe.edu.pucp.comerzia.db.DAOImpl;

/*
public class TrabajadorDeAlmacen {

    private Integer idTrabajadorDeAlmacen;
    private static Integer idCorrelativo = 1;

    private Integer idEmpleado;
    // private Empleado empleado;
    private Integer idAlmacen;
    // private Almacen almacenDeTrabajo;

    private boolean licenciaMontacarga;
 */
public class TrabajadorDeAlmacenDAOImpl extends DAOImpl implements TrabajadorDeAlmacenDAO {

    private TrabajadorDeAlmacen trabajadorDeAlmacen;

    public TrabajadorDeAlmacenDAOImpl() {
        super("TrabajadorDeAlmacen");
        this.trabajadorDeAlmacen = null;
    }

    @Override
    public Integer insertar(TrabajadorDeAlmacen trabajadorDeAlmacen) {
        this.retornarLlavePrimaria = true;
        this.trabajadorDeAlmacen = trabajadorDeAlmacen;
        Integer id = insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsert() {
        // return "nombre, estado, descripcion";
        return "idEmpleado, idAlmacen, licenciaMontacarga";
    }

    @Override
    protected String obtenerListaDeValoresParaInsert() {
        String sql = "";
        // sql = sql.concat("'" + this.trabajadorDeAlmacen.getNombre() + "'");
        // sql = sql.concat(", ");
        // sql = sql.concat("'" + this.trabajadorDeAlmacen.getEstado() + "'");
        // sql = sql.concat(", ");
        // sql = sql.concat("'" + this.trabajadorDeAlmacen.getDescripcion() + "'");
        sql = sql.concat("'" + this.trabajadorDeAlmacen.getIdEmpleado() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.trabajadorDeAlmacen.getIdAlmacen() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.trabajadorDeAlmacen.isLicenciaMontacarga() + "'");

        return sql;
    }

    @Override
    public Integer modificar(TrabajadorDeAlmacen trabajadorDeAlmacen) {
        this.trabajadorDeAlmacen = trabajadorDeAlmacen;
        return modificar();
    }

    @Override
    public Integer eliminar(TrabajadorDeAlmacen trabajadorDeAlmacen) {
        this.trabajadorDeAlmacen = trabajadorDeAlmacen;
        return eliminar();
    }

    @Override
    public ArrayList<TrabajadorDeAlmacen> listarTodos() {
        ArrayList<TrabajadorDeAlmacen> lista = new ArrayList<>();
        listar();
        try {
            while (this.resultset.next()) {
                lista.add(generaTrabajadorDeAlmacenResult());
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrabajadorDeAlmacenDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(TrabajadorDeAlmacenDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public TrabajadorDeAlmacen obtenerPorId(Integer idTrabajadorDeAlmacen) {
        obtener_Por_Id(idTrabajadorDeAlmacen);
        try {
            return generaTrabajadorDeAlmacenResult();
        } catch (SQLException ex) {
            Logger.getLogger(TrabajadorDeAlmacenDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private TrabajadorDeAlmacen generaTrabajadorDeAlmacenResult() throws SQLException {
        TrabajadorDeAlmacen trabajadorDeAlmacen_local = new TrabajadorDeAlmacen();
        // trabajadorDeAlmacen_local.setIdTrabajadorDeAlmacen(this.resultset.getInt("idTrabajadorDeAlmacen"));
        // trabajadorDeAlmacen_local.setNombre(this.resultset.getString("nombre"));
        // trabajadorDeAlmacen_local.setEstado(this.resultset.getString("estado"));
        // trabajadorDeAlmacen_local.setDescripcion(this.resultset.getString("descripcion"));
        trabajadorDeAlmacen_local.setIdTrabajadorDeAlmacen(this.resultset.getInt("idTrabajadorDeAlmacen"));
        trabajadorDeAlmacen_local.setIdEmpleado(this.resultset.getInt("idEmpleado"));
        trabajadorDeAlmacen_local.setIdAlmacen(this.resultset.getInt("idAlmacen"));
        trabajadorDeAlmacen_local.setLicenciaMontacarga(this.resultset.getBoolean("licenciaMontacarga"));

        return trabajadorDeAlmacen_local;
    }

    @Override
    protected String obtenerID() {
        return trabajadorDeAlmacen.getIdTrabajadorDeAlmacen().toString();
    }

    @Override
    protected String obtenerIDcategoria() {
        return "idTrabajadorDeAlmacen";
    }

    @Override
    protected String obtenerListaDeAtributosYValoresParaActualizacion() {
        String sql = " idTrabajadorDeAlmacen = ";
        // sql = sql.concat(this.trabajadorDeAlmacen.getIdTrabajadorDeAlmacen().toString());
        // sql = sql.concat(", nombre = ");
        // sql = sql.concat("'" + this.trabajadorDeAlmacen.getNombre() + "'");
        // sql = sql.concat(", estado = ");
        // sql = sql.concat("'" + this.trabajadorDeAlmacen.getEstado() + "'");
        // sql = sql.concat(", descripcion = ");
        // sql = sql.concat("'" + this.trabajadorDeAlmacen.getDescripcion() + "'");
        sql = sql.concat(this.trabajadorDeAlmacen.getIdTrabajadorDeAlmacen().toString());
        sql = sql.concat(", idEmpleado = ");
        sql = sql.concat(this.trabajadorDeAlmacen.getIdEmpleado().toString());
        sql = sql.concat(", idAlmacen = ");
        sql = sql.concat(this.trabajadorDeAlmacen.getIdAlmacen().toString());
        sql = sql.concat(", licenciaMontacarga = ");
        sql = sql.concat("'" + this.trabajadorDeAlmacen.isLicenciaMontacarga() + "'");

        return sql;
    }
}
