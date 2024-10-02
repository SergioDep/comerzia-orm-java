package pe.edu.pucp.comerzia.db;

import pe.edu.pucp.comerzia.config.DBManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAOImpl {

    protected String nombre_tabla;
    protected Connection conexion;
    protected PreparedStatement statement;
    protected ResultSet resultset;
    protected Boolean retornarLlavePrimaria;

    public DAOImpl(String nombre_tabla) {
        this.nombre_tabla = nombre_tabla;
    }

    protected void abrirConexion() throws SQLException {
        this.conexion = DBManager.getInstance().getConnection();
    }

    protected void cerrarConexion() throws SQLException {
        if (this.conexion != null) {
            this.conexion.close();
        }
    }

    protected void iniciarTransaccion() throws SQLException {
        this.conexion = DBManager.getInstance().getConnection();
        this.conexion.setAutoCommit(false);
    }

    protected void comitarTransaccion() throws SQLException {
        this.conexion.commit();
    }

    protected void rollbackTransaccion() throws SQLException {
        if (this.conexion != null) {
            this.conexion.rollback();
        }
    }

    protected Integer ejecutarModificacionesEnBD(String sql) throws SQLException {
        this.statement = this.conexion.prepareStatement(sql);
        Integer filasAfectadas = this.statement.executeUpdate();
        return filasAfectadas;
    }

    protected void ejecutarConsultaEnBD(String sql) throws SQLException {
        this.statement = this.conexion.prepareStatement(sql);
        this.resultset = this.statement.executeQuery(sql);
    }

    public Integer insertar() {
        Integer resultado = 0;
        try {
            this.iniciarTransaccion();
            String sql = this.generarSQLParaInsercion();
            resultado = this.ejecutarModificacionesEnBD(sql);
            if (this.retornarLlavePrimaria) {
                Integer id = this.retornarUltimoAutoGenerado();
                resultado = id;
            }
            this.comitarTransaccion();
        } catch (SQLException ex) {
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;

    }

    private String generarSQLParaInsercion() {
        String sql = "insert into " + this.nombre_tabla;
        sql = sql.concat(" (");
        sql = sql.concat(this.obtenerListaDeAtributosParaInsert());
        sql = sql.concat(") values(");
        sql = sql.concat(this.obtenerListaDeValoresParaInsert());
        sql = sql.concat(")");
        return sql;
    }

    protected abstract String obtenerListaDeAtributosParaInsert();

    protected abstract String obtenerListaDeValoresParaInsert();

    public Integer modificar() {
        Integer resultado = 0;
        try {
            this.iniciarTransaccion();
            String sql = this.generarSQLParaActualizacion();
            //System.out.println("AA: "+sql);
            resultado = this.ejecutarModificacionesEnBD(sql);
            this.comitarTransaccion();
        } catch (SQLException ex) {
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    private String generarSQLParaActualizacion() {
        String sql = "UPDATE " + this.nombre_tabla;
        sql = sql.concat(" SET ");
        sql = sql.concat(this.obtenerListaDeAtributosYValoresParaActualizacion());
        sql = sql.concat(" WHERE ");
        sql = sql.concat(obtenerIDcategoria());
        sql = sql.concat(" = ");
        sql = sql.concat(obtenerID());
        sql = sql.concat(";");
        return sql;
    }

    protected abstract String obtenerListaDeAtributosYValoresParaActualizacion();

    public Integer eliminar() {
        Integer resultado = 0;
        try {
            this.iniciarTransaccion();
            String sql = this.generarSQLParaEliminacion();
            resultado = this.ejecutarModificacionesEnBD(sql);
            this.comitarTransaccion();
        } catch (SQLException ex) {
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    private String generarSQLParaEliminacion() {
        String sql = "UPDATE " + this.nombre_tabla;
        sql = sql.concat(" SET eliminado = 1 WHERE ");
        sql = sql.concat(obtenerIDcategoria());
        sql = sql.concat(" = ");
        sql = sql.concat(obtenerID());
        sql = sql.concat(";");
        return sql;
    }

    protected abstract String obtenerID();

    protected abstract String obtenerIDcategoria();
    
    protected abstract void generarObjetoResultado();

    public void obtener_Por_Id(Integer id) {
        try {
            this.iniciarTransaccion();
            String sql = this.generarSQLParaConsulta(id);
            this.ejecutarConsultaEnBD(sql);
            this.generarObjetoResultado();
        } catch (SQLException ex) {
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private String generarSQLParaConsulta(Integer id) {
        String sql = "SELECT * FROM " + this.nombre_tabla;
        sql = sql.concat(" WHERE " + obtenerIDcategoria());
        sql = sql.concat(" = " + id.toString());
        return sql;
    }

    public void listar() {
//        try {
//            this.abrirConexion();
//            String sql = "SELECT * FROM " + this.nombre_tabla + " WHERE eliminado = FALSE";
//            this.ejecutarConsultaEnBD(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//        } finally{
//            try {
//                this.cerrarConexion();
//            } catch (SQLException ex) {
//                Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        try {
            this.abrirConexion();
            String sql = "SELECT * FROM " + this.nombre_tabla + " WHERE eliminado = 0";
            this.ejecutarConsultaEnBD(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected Integer retornarUltimoAutoGenerado() throws SQLException {
        Integer resultado = null;
        String sql = "select @@last_insert_id as id";
        this.ejecutarConsultaEnBD(sql);
        if (this.resultset.next()) {
            resultado = this.resultset.getInt("id");
        }
        return resultado;
    }
}
