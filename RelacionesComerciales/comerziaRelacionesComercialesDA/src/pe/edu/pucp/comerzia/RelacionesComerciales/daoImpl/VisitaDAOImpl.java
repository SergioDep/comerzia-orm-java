/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.VisitaDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Visita;
import pe.edu.pucp.comerzia.db.DAOImpl;

public class VisitaDAOImpl extends DAOImpl implements VisitaDAO {

    private Visita visita;

    public VisitaDAOImpl() {
        super("Visita"); // Nombre de la tabla
        this.visita = null;
    }

    @Override
    public Integer insertar(Visita visita) {
        this.visita = visita;
        return insertar();
    }

    @Override
    public Integer modificar(Visita visita) {
        this.visita = visita;
        return modificar(); // Igual llama a la clase padre.
    }

    @Override
    public Integer eliminar(Visita visita) {
        this.visita = visita;
        return eliminar();
    }

    @Override
    public ArrayList<Visita> listarTodos() {
        return listarTodos();
    }

    @Override
    public Visita obtenerPorId(Integer idVisita) {

        try {
            super.obtener_Por_Id(idVisita);
            Visita visitaLocal = new Visita();
            
            visitaLocal.setIdVisita(this.resultset.getInt("idVisita"));
            visitaLocal.setFecha(this.resultset.getDate("fecha"));
            visitaLocal.setDuracion(this.resultset.getTime("duracion"));
            visitaLocal.setIdEmpresa(this.resultset.getInt("idEmpresa"));
            visitaLocal.setIdPersona(this.resultset.getInt("idPersona"));
            return visitaLocal;
        } catch (SQLException ex) {
            Logger.getLogger(VisitaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected String obtenerID() {
        return visita.getIdVisita().toString();// Proviene de Integer
    }

    @Override
    protected String obtenerListaDeAtributosParaInsert() {
        return "idVisita, fecha, duracion, idEmpresa, idPersona";
    }

    @Override
    protected String obtenerListaDeValoresParaInsert() {
        String sql = "";
        sql = sql.concat(this.visita.getIdVisita().toString());
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.visita.getFecha().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.visita.getDuracion().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.visita.getIdEmpresa().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.visita.getIdPersona().toString() + "'"); // Colocar paquete Persona.
        return sql;
    }

    @Override
    protected String obtenerListaDeAtributosYValoresParaActualizacion() {
        String sql = " idVisita = ";
        sql = sql.concat(this.visita.getIdVisita().toString());
        sql = sql.concat(", fecha = ");
        sql = sql.concat("'" + this.visita.getFecha().toString() + "'");
        sql = sql.concat(", duracion = ");
        sql = sql.concat("'" + this.visita.getDuracion().toString() + "'");
        sql = sql.concat(", idEmpresa = ");
        sql = sql.concat("'" + this.visita.getIdEmpresa().toString() + "'");
        sql = sql.concat(", idPersona = ");
        sql = sql.concat("'" + this.visita.getIdPersona().toString() + "'");

        return sql;
    }

    @Override
    protected String obtenerIDcategoria() {
        return "idVisita";
    }

}
