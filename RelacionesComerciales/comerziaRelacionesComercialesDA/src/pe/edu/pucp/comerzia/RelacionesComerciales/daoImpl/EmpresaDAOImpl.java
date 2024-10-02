package pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.EmpresaDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Empresa;
import pe.edu.pucp.comerzia.db.DAOImpl;

public class EmpresaDAOImpl extends DAOImpl implements EmpresaDAO {

    private Empresa empresa;

    public EmpresaDAOImpl() {
        super("Empresa"); // Nombre de la tabla
        this.empresa = null;
    }

    @Override
    public Integer insertar(Empresa empresa) {
        this.retornarLlavePrimaria = true;
        this.empresa = empresa;
        Integer id = insertar();
        this.retornarLlavePrimaria = false;
        return id;
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
        // Se crea un array para listar todos los elementos.
        ArrayList<Empresa> lista = new ArrayList<>();
        listar();
        try {
            while (resultset.next()) {
                lista.add(new Empresa(resultset.getInt("idEmpresa"), resultset.getString("nombre"), resultset.getString("direccion"),
                        resultset.getString("telefono"), resultset.getString("email"), resultset.getString("tipoIndustria")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(EmpresaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;

    }

    @Override
    public Empresa obtenerPorId(Integer idEmpresa) {
        super.obtener_Por_Id(idEmpresa);
        return this.empresa;

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
                this.empresa = null;
            } else {
                this.empresa = generaEmpresaResult();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Empresa generaEmpresaResult() throws SQLException {
        Empresa empresa_local = new Empresa();
        empresa_local.setIdEmpresa(this.resultset.getInt("idEmpresa"));
        empresa_local.setNombre(this.resultset.getString("nombre"));
        empresa_local.setDireccion(this.resultset.getString("direccion"));
        empresa_local.setTelefono(this.resultset.getString("telefono"));
        empresa_local.setEmail(this.resultset.getString("email"));
        empresa_local.setTipoIndustria(this.resultset.getString("tipoIndustria"));
        return empresa_local;
    }

    @Override
    protected String obtenerID() {
        return empresa.getIdEmpresa().toString();// Proviene de Integer
    }

    @Override
    protected String obtenerListaDeAtributosParaInsert() {
        String sql = "nombre, direccion, telefono, email, tipoIndustria";
        return sql;
    }

    @Override
    protected String obtenerListaDeValoresParaInsert() {
        String sql = "";
        sql = sql.concat("'" + this.empresa.getNombre() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + empresa.getDireccion() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + empresa.getTelefono() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + empresa.getEmail() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + empresa.getTipoIndustria() + "'");
        return sql;
    }

    @Override
    protected String obtenerListaDeAtributosYValoresParaActualizacion() {
        String sql = "idEmpresa = ";
        sql = sql.concat(this.empresa.getIdEmpresa().toString());
        sql = sql.concat("nombre = ");
        sql = sql.concat("'" + this.empresa.getNombre() + "'");
        sql = sql.concat(", direccion = ");
        sql = sql.concat("'" + this.empresa.getDireccion() + "'");
        sql = sql.concat(", telefono = ");
        sql = sql.concat("'" + this.empresa.getTelefono() + "'");
        sql = sql.concat(", email = ");
        sql = sql.concat("'" + this.empresa.getEmail() + "'");
        sql = sql.concat(", tipoIndustria = ");
        sql = sql.concat("'" + this.empresa.getTipoIndustria() + "'");
        return sql;
    }

    @Override
    protected String obtenerIDcategoria() {
        return "idEmpresa";
    }

}
