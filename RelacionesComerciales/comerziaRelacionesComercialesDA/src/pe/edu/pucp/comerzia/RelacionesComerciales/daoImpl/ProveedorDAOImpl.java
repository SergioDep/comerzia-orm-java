/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Proveedor;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.ProveedorDAO;
import pe.edu.pucp.comerzia.db.DAOImpl;

/**
 *
 * @author camilo
 */
public class ProveedorDAOImpl extends DAOImpl implements ProveedorDAO {

    private Proveedor proveedor;

    public ProveedorDAOImpl() {
        super("Proveedor"); // Se selecciona la tabla
        this.proveedor = null;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsert() {
        return "idEmpresa, fecha_afiliacion, RUC, razonSocial, calificacion, pais";

    }

    @Override
    protected String obtenerListaDeValoresParaInsert() {
        String sql = "";
        // sql = sql.concat(proveedor.getIdEmpresa().toString());
        // sql = sql.concat("', '" + proveedor.getFecha_afiliacion().toString());
        // sql = sql.concat("', '" + proveedor.getRUC().toString());
        // sql = sql.concat("', '" + proveedor.getRazonSocial().toString());
        // sql = sql.concat("', '" + proveedor.getCalificacion().toString());
        // sql = sql.concat("', '" + proveedor.getPais().toString());

        // sql = sql.concat("'" + this.vendedor.getNombre() + "'");
        // sql = sql.concat(", ");
        // sql = sql.concat("'" + this.vendedor.getEstado() + "'");
        // sql = sql.concat(", ");
        // sql = sql.concat("'" + this.vendedor.getDescripcion() + "'");
        sql = sql.concat(proveedor.getIdEmpresa().toString());
        sql = sql.concat(", ");
        sql = sql.concat("'" + proveedor.getFecha_afiliacion().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + proveedor.getRUC().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + proveedor.getRazonSocial().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + proveedor.getCalificacion().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + proveedor.getPais().toString() + "'");

        return sql;
    }

    @Override
    protected String obtenerListaDeAtributosYValoresParaActualizacion() {
        String sql = "idEmpresa = '" + proveedor.getIdEmpresa().toString() + "',";
        sql = sql.concat("fecha_afiliacion= " + proveedor.getFecha_afiliacion().toString() + "',");
        sql = sql.concat("RUC = " + proveedor.getRUC().toString() + "',");
        sql = sql.concat("razonSocial= " + proveedor.getRazonSocial().toString() + "',");
        sql = sql.concat("calficacion = " + proveedor.getCalificacion().toString() + "',");
        sql = sql.concat("pais= " + proveedor.getPais().toString() + "',");
        return sql;
    }

    @Override
    protected String obtenerID() {
        return proveedor.getIdEmpresa().toString();
    }

    @Override
    protected String obtenerIDcategoria() {
        return "idEmpresa";
    }

    @Override
    public Integer insertar(Proveedor proveedor) {
        this.retornarLlavePrimaria = true;
        this.proveedor = proveedor;
        Integer id = insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    public Integer modificar(Proveedor proveedor) {
        this.proveedor = proveedor;
        return modificar();
    }

    @Override
    public Integer eliminar(Proveedor proveedor) {
        this.proveedor = proveedor;
        return eliminar();
    }

    @Override
    public ArrayList<Proveedor> listarTodos() {
        ArrayList<Proveedor> arr = new ArrayList<Proveedor>();
        try {
            while (resultset.next()) {
                arr.add(new Proveedor(resultset.getInt("idEmpresa"), resultset.getDate("fecha_afiliacion"), resultset.getString("RUC"),
                        resultset.getString("razonSocial"), resultset.getDouble("calificacion"), resultset.getString("pais")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    @Override
    public Proveedor obtenerPorId(Integer idProveedor) {
        super.obtener_Por_Id(idProveedor);
        try {
            return new Proveedor(resultset.getInt("idEmpresa"), resultset.getDate("fecha_afiliacion"), resultset.getString("RUC"),
                    resultset.getString("razonSocial"), resultset.getDouble("calificacion"), resultset.getString("pais"));
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
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
                this.proveedor = null;
            } else {
                this.proveedor = generaProveedorResult();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Proveedor generaProveedorResult() throws SQLException {
        Proveedor proveedor_local = new Proveedor();
        proveedor_local.setIdEmpresa(this.resultset.getInt("idEmpresa"));
        proveedor_local.setFecha_afiliacion(this.resultset.getDate("fecha_afiliacion"));
        proveedor_local.setRUC(this.resultset.getString("RUC"));
        proveedor_local.setRazonSocial(this.resultset.getString("razonSocial"));
        proveedor_local.setCalificacion(this.resultset.getDouble("calificacion"));
        proveedor_local.setPais(this.resultset.getString("pais"));
        return proveedor_local;
    }
}
