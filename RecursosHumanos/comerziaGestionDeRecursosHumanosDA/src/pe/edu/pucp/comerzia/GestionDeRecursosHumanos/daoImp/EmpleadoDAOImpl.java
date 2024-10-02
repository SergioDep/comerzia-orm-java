package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.EmpleadoDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Empleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;
import pe.edu.pucp.comerzia.db.DAOImpl;

/*
public class Empleado {

    private Integer idEmpleado;
    private Integer idPersona;
    private static Integer idCorrelativo = 1;

    private EstadoEmpleado estado;
    private String nombreUsuario;
    private String contrasenha;
    private Double salario;
    private Date fechaContratacion;
    }
 */
public class EmpleadoDAOImpl extends DAOImpl implements EmpleadoDAO {

    private Empleado empleado;

    public EmpleadoDAOImpl() {
        super("Empleado");
        this.empleado = null;
    }

    @Override
    public Integer insertar(Empleado empleado) {
        this.retornarLlavePrimaria = true;
        this.empleado = empleado;
        Integer id = insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsert() {
        // return "nombre, estado, descripcion";
        return "idPersona, estado, nombreUsuario, contrasenha, salario, fechaContratacion";
    }

    @Override
    protected String obtenerListaDeValoresParaInsert() {
        String sql = "";
        // sql = sql.concat("'" + this.empleado.getNombre() + "'");
        // sql = sql.concat(", ");
        // sql = sql.concat("'" + this.empleado.getEstado() + "'");
        // sql = sql.concat(", ");
        // sql = sql.concat("'" + this.empleado.getDescripcion() + "'");
        sql = sql.concat(this.empleado.getIdPersona().toString());
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.empleado.getEstado() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.empleado.getNombreUsuario() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.empleado.getContrasenha() + "'");
        sql = sql.concat(", ");
        sql = sql.concat(this.empleado.getSalario().toString());
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.empleado.getFechaContratacion().toString() + "'");

        return sql;
    }

    @Override
    public Integer modificar(Empleado empleado) {
        this.empleado = empleado;
        return modificar();
    }

    @Override
    public Integer eliminar(Empleado empleado) {
        this.empleado = empleado;
        return eliminar();
    }

    @Override
    public ArrayList<Empleado> listarTodos() {
        ArrayList<Empleado> lista = new ArrayList<>();
        listar();
        try {
            while (this.resultset.next()) {
                lista.add(generaEmpleadoResult());
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public Empleado obtenerPorId(Integer idEmpleado) {
        obtener_Por_Id(idEmpleado);
        try {
            return generaEmpleadoResult();
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Empleado generaEmpleadoResult() throws SQLException {
        Empleado empleado_local = new Empleado();
        // empleado_local.setIdEmpleado(this.resultset.getInt("idEmpleado"));
        // empleado_local.setNombre(this.resultset.getString("nombre"));
        // empleado_local.setEstado(this.resultset.getString("estado"));
        // empleado_local.setDescripcion(this.resultset.getString("descripcion"));
        empleado_local.setIdEmpleado(this.resultset.getInt("idEmpleado"));
        empleado_local.setIdPersona(this.resultset.getInt("idPersona"));
        empleado_local.setEstado(EstadoEmpleado.valueOf(this.resultset.getString("estado")));
        empleado_local.setNombreUsuario(this.resultset.getString("nombreUsuario"));
        empleado_local.setContrasenha(this.resultset.getString("contrasenha"));
        empleado_local.setSalario(this.resultset.getDouble("salario"));
        empleado_local.setFechaContratacion(this.resultset.getDate("fechaContratacion"));

        return empleado_local;
    }

    @Override
    protected String obtenerID() {
        return empleado.getIdEmpleado().toString();
    }

    @Override
    protected String obtenerIDcategoria() {
        return "idEmpleado";
    }

    @Override
    protected String obtenerListaDeAtributosYValoresParaActualizacion() {
        String sql = " idEmpleado = ";
        // sql = sql.concat(this.empleado.getIdEmpleado().toString());
        // sql = sql.concat(", nombre = ");
        // sql = sql.concat("'" + this.empleado.getNombre() + "'");
        // sql = sql.concat(", estado = ");
        // sql = sql.concat("'" + this.empleado.getEstado() + "'");
        // sql = sql.concat(", descripcion = ");
        // sql = sql.concat("'" + this.empleado.getDescripcion() + "'");
        sql = sql.concat(this.empleado.getIdEmpleado().toString());
        sql = sql.concat(", idPersona = ");
        sql = sql.concat(this.empleado.getIdPersona().toString());
        sql = sql.concat(", estado = ");
        sql = sql.concat("'" + this.empleado.getEstado() + "'");
        sql = sql.concat(", nombreUsuario = ");
        sql = sql.concat("'" + this.empleado.getNombreUsuario() + "'");
        sql = sql.concat(", contrasenha = ");
        sql = sql.concat("'" + this.empleado.getContrasenha() + "'");
        sql = sql.concat(", salario = ");
        sql = sql.concat(this.empleado.getSalario().toString());
        sql = sql.concat(", fechaContratacion = ");
        sql = sql.concat("'" + this.empleado.getFechaContratacion().toString() + "'");

        return sql;
    }
}
