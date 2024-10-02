package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.AdministradorDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Administrador;
import pe.edu.pucp.comerzia.db.DAOImpl;


/*
public class Administrador {

    private Integer idAdministrador;
    private static int idCorrelativo = 1;

    private Integer idAlmacen;
 */
public class AdministradorDAOImpl extends DAOImpl implements AdministradorDAO {

    private Administrador administrador;

    public AdministradorDAOImpl() {
        super("Administrador");
        this.administrador = null;
    }

    @Override
    public Integer insertar(Administrador administrador) {
        this.retornarLlavePrimaria = true;
        this.administrador = administrador;
        Integer id = insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsert() {
        // return "nombre, estado, descripcion";
        return "idAlmacen";
    }

    @Override
    protected String obtenerListaDeValoresParaInsert() {
        String sql = "";
        // sql = sql.concat("'" + this.administrador.getNombre() + "'");
        // sql = sql.concat(", ");
        // sql = sql.concat("'" + this.administrador.getEstado() + "'");
        // sql = sql.concat(", ");
        // sql = sql.concat("'" + this.administrador.getDescripcion() + "'");
        sql = sql.concat(this.administrador.getIdAlmacen().toString());

        return sql;
    }

    @Override
    public Integer modificar(Administrador administrador) {
        this.administrador = administrador;
        return modificar();
    }

    @Override
    public Integer eliminar(Administrador administrador) {
        this.administrador = administrador;
        return eliminar();
    }

    @Override
    public ArrayList<Administrador> listarTodos() {
        ArrayList<Administrador> lista = new ArrayList<>();
        listar();
        try {
            while (this.resultset.next()) {
                lista.add(generaAdministradorResult());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AdministradorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public Administrador obtenerPorId(Integer idAdministrador) {
        obtener_Por_Id(idAdministrador);
        try {
            return generaAdministradorResult();
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    protected void generarObjetoResultado(){
        try {
            if(!this.resultset.next()){
                this.administrador=null;
            }
            else{
                this.administrador=this.generaAdministradorResult();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Administrador generaAdministradorResult() throws SQLException {
        Administrador administrador_local = new Administrador();
        // administrador_local.setIdAdministrador(this.resultset.getInt("idAdministrador"));
        // administrador_local.setNombre(this.resultset.getString("nombre"));
        // administrador_local.setEstado(this.resultset.getString("estado"));
        // administrador_local.setDescripcion(this.resultset.getString("descripcion"));
        administrador_local.setIdAdministrador(this.resultset.getInt("idAdministrador"));
        administrador_local.setIdAlmacen(this.resultset.getInt("idAlmacen"));

        return administrador_local;
    }

    @Override
    protected String obtenerID() {
        return administrador.getIdAdministrador().toString();
    }

    @Override
    protected String obtenerIDcategoria() {
        return "idAdministrador";
    }

    @Override
    protected String obtenerListaDeAtributosYValoresParaActualizacion() {
        String sql = " idAdministrador = ";
        // sql = sql.concat(this.administrador.getIdAdministrador().toString());
        // sql = sql.concat(", nombre = ");
        // sql = sql.concat("'" + this.administrador.getNombre() + "'");
        // sql = sql.concat(", estado = ");
        // sql = sql.concat("'" + this.administrador.getEstado() + "'");
        // sql = sql.concat(", descripcion = ");
        // sql = sql.concat("'" + this.administrador.getDescripcion() + "'");
        sql = sql.concat(this.administrador.getIdAdministrador().toString());
        sql = sql.concat(", idAlmacen = ");
        sql = sql.concat(this.administrador.getIdAlmacen().toString());

        return sql;
    }
}
