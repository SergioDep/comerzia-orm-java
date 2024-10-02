package pe.edu.pucp.comerzia.gestioncomercial.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.gestioncomercial.dao.DocumentoDAO;
import pe.edu.pucp.comerzia.gestioncomercial.model.Documento;
import pe.edu.pucp.comerzia.gestioncomercial.model.Estado;
import pe.edu.pucp.comerzia.gestioncomercial.model.Tipo;
import pe.edu.pucp.comerzia.db.DAOImpl;

public class DocumentoDAOImpl extends DAOImpl implements DocumentoDAO{
    private Documento documento;

    public DocumentoDAOImpl() {
        super("Documento");
        this.documento = null;
    }

    @Override
    public Integer insertar(Documento documento) {
        this.retornarLlavePrimaria = true;
        this.documento = documento;
        Integer id = insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsert() {
        return "idDocumento, idEmpresa, estado, tipo, idVendedor, idAdministrador, idTrabajadorDeAlmacen";
    }

    @Override
    protected String obtenerListaDeValoresParaInsert() {
        String sql = "";
        sql = sql.concat(this.documento.getIdDocumento().toString());
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.documento.getIdEmpresa().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.documento.getEstado().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.documento.getTipo().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.documento.getIdVendedor().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.documento.getIdAdministrador().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.documento.getIdTrabajadorDeAlmacen().toString() + "'");
        return sql;
    }

    @Override
    public Integer modificar(Documento documento) {
        this.documento = documento;
        return modificar();
    }

    @Override
    public Integer eliminar(Documento documento) {
        this.documento = documento;
        return eliminar();
    }
    
    @Override
    public ArrayList<Documento> listarTodos(){    
        ArrayList<Documento> lista = new ArrayList<>();
        listar();
        try {
            while (this.resultset.next())
                lista.add(generaDocumentoResult());
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public Documento obtenerPorId(Integer idEspecialidad) {
        obtener_Por_Id(idEspecialidad);
        try {
            return generaDocumentoResult();
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    protected void generarObjetoResultado(){
        try {
            if(!this.resultset.next()){
                this.documento=null;
            }
            else{
                this.documento=this.generaDocumentoResult();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Documento generaDocumentoResult() throws SQLException{
        Documento documento_local = new Documento();
        documento_local.setIdDocumento(this.resultset.getInt("idDocumento"));
        documento_local.setIdEmpresa(this.resultset.getInt("idEmpresa"));
        documento_local.setEstado(Estado.valueOf(this.resultset.getString("estado")));
        documento_local.setTipo(Tipo.valueOf(this.resultset.getString("tipo")));
        documento_local.setIdVendedor(this.resultset.getInt("idVendedor"));
        documento_local.setIdAdministrador(this.resultset.getInt("idAdministrador"));
        documento_local.setIdTrabajadorDeAlmacen(this.resultset.getInt("idTrabajadorDeAlmacen"));
        return documento_local;
    }
    
    @Override
    protected String obtenerID(){
        return documento.getIdDocumento().toString();
    }
    
    @Override
    protected String obtenerIDcategoria(){
        return "idDocumento";
    }
    
    @Override
    protected String obtenerListaDeAtributosYValoresParaActualizacion(){
        String sql = " idDocumento = ";
        sql = sql.concat(this.documento.getIdDocumento().toString());
        sql = sql.concat(", idEmpresa = ");
        sql = sql.concat("'" + this.documento.getIdEmpresa().toString() + "'");
        sql = sql.concat(", estado = ");
        sql = sql.concat("'" + this.documento.getEstado().toString() + "'");
        sql = sql.concat(", tipo = ");
        sql = sql.concat("'" + this.documento.getTipo().toString() + "'");
        sql = sql.concat(", idVendedor = ");
        sql = sql.concat("'" + this.documento.getIdVendedor().toString() + "'");
        sql = sql.concat(", idAdministrador = ");
        sql = sql.concat("'" + this.documento.getIdAdministrador().toString() + "'");
        sql = sql.concat(", idTrabajadorDeAlmacen = ");
        sql = sql.concat("'" + this.documento.getIdTrabajadorDeAlmacen().toString() + "'");
        return sql;
    }
}
