package pe.edu.pucp.comerzia.GestionDeAlmacen.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeAlmacen.dao.AlmacenDAO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Almacen;
import pe.edu.pucp.comerzia.db.DAOImpl;


public class AlmacenDAOImpl extends DAOImpl implements AlmacenDAO {
    private Almacen almacen;

    public AlmacenDAOImpl() {
        super("Almacen");
        this.almacen = null;
    }

    @Override
    public Integer insertar(Almacen almacen) {
        this.retornarLlavePrimaria = true;
        this.almacen = almacen;
        Integer id = insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsert() {
        return "nombre, estado, descripcion";
    }

    @Override
    protected String obtenerListaDeValoresParaInsert() {
        String sql = "";
        sql = sql.concat("'" + this.almacen.getNombre() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.almacen.getEstado() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.almacen.getDescripcion() + "'");
        return sql;
    }

    @Override
    public Integer modificar(Almacen almacen) {
        this.almacen = almacen;
        return modificar();
    }

    @Override
    public Integer eliminar(Almacen almacen) {
        this.almacen = almacen;
        return eliminar();
    }

    @Override
    public ArrayList<Almacen> listarTodos() {
        ArrayList<Almacen> lista = new ArrayList<>();
        listar();
        try {
            while (this.resultset.next())
                lista.add(generaAlmacenResult());
        } catch (SQLException ex) {
            Logger.getLogger(AlmacenDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
        return lista;
    }

    @Override
    public Almacen obtenerPorId(Integer idAlmacen) {
        obtener_Por_Id(idAlmacen);
        return this.almacen;
    }
    
    @Override
    protected void generarObjetoResultado(){
        try {
            if(!this.resultset.next()){
                this.almacen=null;
            }
            else{
                this.almacen=this.generaAlmacenResult();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlmacenDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Almacen generaAlmacenResult() throws SQLException {
        Almacen almacen_local = new Almacen();
        almacen_local.setIdAlmacen(this.resultset.getInt("idAlmacen"));
        almacen_local.setNombre(this.resultset.getString("nombre"));
        almacen_local.setEstado(this.resultset.getString("estado"));
        almacen_local.setDescripcion(this.resultset.getString("descripcion"));
        if(this.resultset.getBoolean("eliminado")==true)return null;
        return almacen_local;
    }

    @Override
    protected String obtenerID() {
        return almacen.getIdAlmacen().toString();
    }

    @Override
    protected String obtenerIDcategoria() {
        return "idAlmacen";
    }

    @Override
    protected String obtenerListaDeAtributosYValoresParaActualizacion() {
        String sql = " idAlmacen = ";
        sql = sql.concat(this.almacen.getIdAlmacen().toString());
        sql = sql.concat(", nombre = ");
        sql = sql.concat("'" + this.almacen.getNombre() + "'");
        sql = sql.concat(", estado = ");
        sql = sql.concat("'" + this.almacen.getEstado() + "'");
        sql = sql.concat(", descripcion = ");
        sql = sql.concat("'" + this.almacen.getDescripcion() + "'");
        return sql;
    }
}