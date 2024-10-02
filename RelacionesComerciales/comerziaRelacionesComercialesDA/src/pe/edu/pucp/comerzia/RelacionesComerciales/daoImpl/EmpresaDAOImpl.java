/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.EmpresaDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Empresa;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.TipoEmpresa;
import pe.edu.pucp.comerzia.db.DAOImpl;

/**
 *
 * @author camilo
 */
public class EmpresaDAOImpl extends DAOImpl implements EmpresaDAO {

    private Empresa empresa;

    public EmpresaDAOImpl() {
        super("Empresa"); // Nombre de la tabla
        this.empresa = null;
    }

    @Override
    public Integer insertar(Empresa empresa) {
        this.empresa = empresa;
        return insertar();
    }

    @Override
    public Integer modificar(Empresa empresa) {
        this.empresa = empresa;
        return modificar(); // Igual llama a la clase padre.
    }

    @Override
    public Integer eliminar(Empresa empresa) {
        this.empresa = empresa;
        return eliminar();
    }

    @Override
    public ArrayList<Empresa> listarTodos() {
        return listarTodos();
    }

    @Override
    public Empresa obtenerPorId(Integer idEmpresa) {
        try {
            // ?
            // Pendiente
            super.obtener_Por_Id(idEmpresa); // Se castea
            // this.resultset
            Empresa empresaLocal = new Empresa();
            empresaLocal.setIdEmpresa(this.resultset.getInt("idEmpresa"));
            empresaLocal.setNombre(this.resultset.getString("nombre"));
            empresaLocal.setDireccion(this.resultset.getString("direccion"));
            empresaLocal.setTelefono(this.resultset.getString("telefono"));
            empresaLocal.setEmail(this.resultset.getString("email"));
            empresaLocal.setTipoIndustria(this.resultset.getString("tipoIndustria"));
            empresaLocal.setTipo(TipoEmpresa.valueOf(this.resultset.getString("tipo"))); // Asumiendo que el tipo se almacena como una cadena

// Atributos de proveedor
            empresaLocal.setFecha_afiliacion(this.resultset.getDate("fecha_afiliacion"));
            empresaLocal.setRUC(this.resultset.getString("RUC"));
            empresaLocal.setRazonSocial(this.resultset.getString("razonSocial"));
            empresaLocal.setCalificacion(this.resultset.getDouble("calificacion"));
            empresaLocal.setPais(this.resultset.getString("pais"));

// Atributos de cliente
            empresaLocal.setFechaRegistro(this.resultset.getDate("fechaRegistro"));
            empresaLocal.setFechaUltimaCompra(this.resultset.getDate("fechaUltimaCompra"));
            return empresaLocal;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    protected String obtenerID() {
        return empresa.getIdEmpresa().toString();// Proviene de Integer
    }

    @Override
    protected String obtenerListaDeAtributosParaInsert() {
        String sql = "idEmpresa, nombre, direccion, telefono, email, tipoIndustria, tipo";

        // Si es proveedor, incluimos atributos de proveedor
        if (this.empresa.getRUC() != null || this.empresa.getRazonSocial() != null || this.empresa.getFecha_afiliacion() != null) {
            sql = sql.concat(", fecha_afiliacion, RUC, razonSocial, calificacion, pais");
        }

        // Si es cliente, incluimos atributos de cliente
        if (this.empresa.getFechaRegistro() != null || this.empresa.getFechaUltimaCompra() != null) {
            sql = sql.concat(", fechaRegistro, fechaUltimaCompra");
        }

        return sql;
    }

    protected String obtenerListaDeValoresParaInsert() {
        String sql = "idEmpresa = ";
        sql = sql.concat(this.empresa.getIdEmpresa().toString());
        sql = sql.concat(", nombre = ");
        sql = sql.concat("'" + this.empresa.getNombre() + "'");
        sql = sql.concat(", direccion = ");
        sql = sql.concat("'" + this.empresa.getDireccion() + "'");
        sql = sql.concat(", telefono = ");
        sql = sql.concat("'" + this.empresa.getTelefono() + "'");
        sql = sql.concat(", email = ");
        sql = sql.concat("'" + this.empresa.getEmail() + "'");
        sql = sql.concat(", tipoIndustria = ");
        sql = sql.concat("'" + this.empresa.getTipoIndustria() + "'");
        sql = sql.concat(", tipo = ");
        sql = sql.concat("'" + this.empresa.getTipo().toString() + "'"); // Suponiendo que es un enum y se puede llamar toString()

        // Atributos de proveedor
        if (this.empresa.getFecha_afiliacion() != null) {
            sql = sql.concat(", fecha_afiliacion = ");
            sql = sql.concat("'" + this.empresa.getFecha_afiliacion().toString() + "'");
        }
        if (this.empresa.getRUC() != null) {
            sql = sql.concat(", RUC = ");
            sql = sql.concat("'" + this.empresa.getRUC() + "'");
        }
        if (this.empresa.getRazonSocial() != null) {
            sql = sql.concat(", razonSocial = ");
            sql = sql.concat("'" + this.empresa.getRazonSocial() + "'");
        }
        if (this.empresa.getCalificacion() != null) {
            sql = sql.concat(", calificacion = ");
            sql = sql.concat(this.empresa.getCalificacion().toString());
        }
        if (this.empresa.getPais() != null) {
            sql = sql.concat(", pais = ");
            sql = sql.concat("'" + this.empresa.getPais() + "'");
        }

        // Atributos de cliente
        if (this.empresa.getFechaRegistro() != null) {
            sql = sql.concat(", fechaRegistro = ");
            sql = sql.concat("'" + this.empresa.getFechaRegistro().toString() + "'");
        }
        if (this.empresa.getFechaUltimaCompra() != null) {
            sql = sql.concat(", fechaUltimaCompra = ");
            sql = sql.concat("'" + this.empresa.getFechaUltimaCompra().toString() + "'");
        }

        return sql;
    }

    protected String obtenerListaDeAtributosYValoresParaActualizacion() {
        String sql = "idEmpresa = ";
        sql = sql.concat(this.empresa.getIdEmpresa().toString());

        // Verificar si los campos no son nulos para poder incluirlos.
        if (this.empresa.getNombre() != null) {
            sql = sql.concat(", nombre = ");
            sql = sql.concat("'" + this.empresa.getNombre() + "'");
        }

        if (this.empresa.getDireccion() != null) {
            sql = sql.concat(", direccion = ");
            sql = sql.concat("'" + this.empresa.getDireccion() + "'");
        }

        if (this.empresa.getTelefono() != null) {
            sql = sql.concat(", telefono = ");
            sql = sql.concat("'" + this.empresa.getTelefono() + "'");
        }

        if (this.empresa.getEmail() != null) {
            sql = sql.concat(", email = ");
            sql = sql.concat("'" + this.empresa.getEmail() + "'");
        }

        if (this.empresa.getTipoIndustria() != null) {
            sql = sql.concat(", tipoIndustria = ");
            sql = sql.concat("'" + this.empresa.getTipoIndustria() + "'");
        }

        if (this.empresa.getTipo() != null) {
            sql = sql.concat(", tipo = ");
            sql = sql.concat("'" + this.empresa.getTipo().toString() + "'");
        }

        // Atributos de proveedor
        if (this.empresa.getFecha_afiliacion() != null) {
            sql = sql.concat(", fecha_afiliacion = ");
            sql = sql.concat("'" + this.empresa.getFecha_afiliacion().toString() + "'");
        }

        if (this.empresa.getRUC() != null) {
            sql = sql.concat(", RUC = ");
            sql = sql.concat("'" + this.empresa.getRUC() + "'");
        }

        if (this.empresa.getRazonSocial() != null) {
            sql = sql.concat(", razonSocial = ");
            sql = sql.concat("'" + this.empresa.getRazonSocial() + "'");
        }

        if (this.empresa.getCalificacion() != null) {
            sql = sql.concat(", calificacion = ");
            sql = sql.concat(this.empresa.getCalificacion().toString());
        }

        if (this.empresa.getPais() != null) {
            sql = sql.concat(", pais = ");
            sql = sql.concat("'" + this.empresa.getPais() + "'");
        }

        // Atributos de cliente
        if (this.empresa.getFechaRegistro() != null) {
            sql = sql.concat(", fechaRegistro = ");
            sql = sql.concat("'" + this.empresa.getFechaRegistro().toString() + "'");
        }

        if (this.empresa.getFechaUltimaCompra() != null) {
            sql = sql.concat(", fechaUltimaCompra = ");
            sql = sql.concat("'" + this.empresa.getFechaUltimaCompra().toString() + "'");
        }

        return sql;
    }

    @Override
    protected String obtenerIDcategoria() {
        return "idEmpresa";
    }

}
