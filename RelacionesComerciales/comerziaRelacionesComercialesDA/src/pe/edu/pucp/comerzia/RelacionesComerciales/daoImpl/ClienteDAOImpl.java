 package pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Cliente;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.ClienteDAO;
import pe.edu.pucp.comerzia.db.DAOImpl;

public class ClienteDAOImpl extends DAOImpl implements ClienteDAO{
    
    private Cliente cliente;

    public ClienteDAOImpl() {
        super("Cliente"); // Se selecciona la tabla
        this.cliente = null;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsert() {
        return "idEmpresa, fechaRegistro, fechaUltimaCompra";
    }

    @Override
    protected String obtenerListaDeValoresParaInsert() {
        String sql = "";
        sql = sql.concat(this.cliente.getIdEmpresa().toString());
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.cliente.getFechaRegistro().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + cliente.getFechaUltimaCompra().toString() + "'");
        return sql;
    }

    @Override
    protected String obtenerListaDeAtributosYValoresParaActualizacion() {
        String sql = "idEmpresa = " + cliente.getIdEmpresa().toString() + ", ";
        sql = sql.concat("fechaRegistro = '" + cliente.getFechaRegistro().toString() + "',");
        sql = sql.concat("fechaUltimaCompra = '" + cliente.getFechaUltimaCompra().toString()+ "'");
        return sql;
    }

    @Override
    protected String obtenerID() {
        return cliente.getIdEmpresa().toString();
    }

    @Override
    protected String obtenerIDcategoria() {
        return "idCliente";
    }

    @Override
    public Integer insertar(Cliente cliente) {
        this.retornarLlavePrimaria = true;
        this.cliente = cliente;
        Integer id = insertar();
        System.out.print(id);
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    public Integer modificar(Cliente cliente) {
        this.cliente = cliente;
        return modificar();
    }

    @Override
    public Integer eliminar(Cliente cliente) {
        this.cliente = cliente;
        return eliminar();
    }

    @Override
    public ArrayList<Cliente> listarTodos() {
        ArrayList<Cliente> lista = new ArrayList<>();
        listar();
        try {
            while(resultset.next()){
                lista.add(new Cliente(resultset.getInt("idCliente"),resultset.getInt("idEmpresa"),resultset.getDate("fechaRegistro"),resultset.getDate("fechaUltimaCompra")));
            } 
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
    @Override
    protected void generarObjetoResultado(){
        try {
            if(!this.resultset.next()){
                this.cliente=null;
            }
            else{
                this.cliente=new Cliente(resultset.getInt("idCliente"),resultset.getInt("idEmpresa"),resultset.getDate("fechaRegistro"),resultset.getDate("fechaUltimaCompra"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Cliente obtenerPorId(Integer idCliente) {
        super.obtener_Por_Id(idCliente);
        return this.cliente;
    }
    
}
