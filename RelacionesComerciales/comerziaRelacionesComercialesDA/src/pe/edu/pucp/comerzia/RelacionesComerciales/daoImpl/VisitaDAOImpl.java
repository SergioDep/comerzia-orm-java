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
        this.retornarLlavePrimaria = true;
        this.visita = visita;
        Integer id = insertar();
        this.retornarLlavePrimaria = false;
        return id;
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
            visitaLocal.setIdCliente(this.resultset.getInt("idCliente"));
            visitaLocal.setIdVendedor(this.resultset.getInt("idVendedor"));
            return visitaLocal;
        } catch (SQLException ex) {
            Logger.getLogger(VisitaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // @Override
    // protected void generarObjetoResultado() {
    //     try {
    //         if (!this.resultset.next()) {
    //             this.vendedor = null;
    //         } else {
    //             this.vendedor = generaVendedorResult();
    //         }
    //     } catch (SQLException ex) {
    //         Logger.getLogger(VendedorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
    //     }
    // }
    // private Vendedor generaVendedorResult() throws SQLException {
    //     Vendedor vendedor_local = new Vendedor();
    //     // vendedor_local.setIdVendedor(this.resultset.getInt("idVendedor"));
    //     // vendedor_local.setNombre(this.resultset.getString("nombre"));
    //     // vendedor_local.setEstado(this.resultset.getString("estado"));
    //     // vendedor_local.setDescripcion(this.resultset.getString("descripcion"));
    //     vendedor_local.setIdVendedor(this.resultset.getInt("idVendedor"));
    //     vendedor_local.setIdEmpleado(this.resultset.getInt("idEmpleado"));
    //     vendedor_local.setIngresosVentas(this.resultset.getDouble("ingresosVentas"));
    //     vendedor_local.setPorcentajeComision(this.resultset.getDouble("porcentajeComision"));
    //     return vendedor_local;
    // }
    @Override
    protected void generarObjetoResultado() {
        try {
            if (!this.resultset.next()) {
                this.visita = null;
            } else {
                this.visita = generaVisitaResult();
            }
        } catch (SQLException ex) {
            Logger.getLogger(VisitaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Visita generaVisitaResult() throws SQLException {
        Visita visita_local = new Visita();
        visita_local.setIdVisita(this.resultset.getInt("idVisita"));
        visita_local.setFecha(this.resultset.getDate("fecha"));
        visita_local.setDuracion(this.resultset.getTime("duracion"));
        visita_local.setIdCliente(this.resultset.getInt("idCliente"));
        visita_local.setIdVendedor(this.resultset.getInt("idVendedor"));
        return visita_local;
    }

    @Override
    protected String obtenerID() {
        return visita.getIdVisita().toString();// Proviene de Integer
    }

    @Override
    protected String obtenerListaDeAtributosParaInsert() {
        return "fecha, duracion, idCliente, idVendedor";
    }

    @Override
    protected String obtenerListaDeValoresParaInsert() {
        String sql = "";
        sql = sql.concat("'" + this.visita.getFecha().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.visita.getDuracion().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.visita.getIdCliente().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.visita.getIdVendedor().toString() + "'"); // Colocar paquete Persona.
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
        sql = sql.concat(", idCliente = ");
        sql = sql.concat("'" + this.visita.getIdCliente().toString() + "'");
        sql = sql.concat(", idVendedor = ");
        sql = sql.concat("'" + this.visita.getIdVendedor().toString() + "'");

        return sql;
    }

    @Override
    protected String obtenerIDcategoria() {
        return "idVisita";
    }

}
