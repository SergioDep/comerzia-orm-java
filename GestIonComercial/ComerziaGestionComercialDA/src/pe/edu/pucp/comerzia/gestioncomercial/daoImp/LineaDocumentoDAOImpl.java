package pe.edu.pucp.comerzia.gestioncomercial.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.gestioncomercial.model.LineaDocumento;
import pe.edu.pucp.comerzia.gestioncomercial.dao.LineaDocumentoDAO;
import pe.edu.pucp.comerzia.db.DAOImpl;

public class LineaDocumentoDAOImpl extends DAOImpl implements LineaDocumentoDAO{
    private LineaDocumento lineaDocumento;

    public LineaDocumentoDAOImpl() {
        super("LineaDocumento");
        this.lineaDocumento = null;
    }

    @Override
    public Integer insertar(LineaDocumento lineaDocumento) {
        this.lineaDocumento = lineaDocumento;
        return insertar();
    }

    @Override
    protected String obtenerListaDeAtributosParaInsert() {
        return "idLinea, idDocumento, idProducto, cantidad, precioUnitario";
    }

    @Override
    protected String obtenerListaDeValoresParaInsert() {
        String sql = "";
        sql = sql.concat(this.lineaDocumento.getIdLinea().toString());
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.lineaDocumento.getIdDocumento().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.lineaDocumento.getIdProducto().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.lineaDocumento.getCantidad().toString() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.lineaDocumento.getPrecioUnitario().toString() + "'");
        return sql;
    }

    @Override
    public Integer modificar(LineaDocumento lineaDocumento) {
        this.lineaDocumento = lineaDocumento;
        return modificar();
    }

    @Override
    public Integer eliminar(LineaDocumento lineaDocumento) {
        this.lineaDocumento = lineaDocumento;
        return eliminar();
    }

    @Override
    public ArrayList<LineaDocumento> listarTodos() {
        ArrayList<LineaDocumento> lista = new ArrayList<>();
        listar();
        try {
            while (this.resultset.next())
                lista.add(generaLineaDocumentoResult());
        } catch (SQLException ex) {
            Logger.getLogger(LineaDocumentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public LineaDocumento obtenerPorId(Integer idLineaDocumento) {
        obtener_Por_Id(idLineaDocumento);
        try {
            return generaLineaDocumentoResult();
        } catch (SQLException ex) {
            Logger.getLogger(LineaDocumentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private LineaDocumento generaLineaDocumentoResult() throws SQLException{
        LineaDocumento lineaDocumento_local = new LineaDocumento();
        lineaDocumento_local.setIdLinea(this.resultset.getInt("idLinea"));
        lineaDocumento_local.setIdDocumento(this.resultset.getInt("idDocumento"));
        lineaDocumento_local.setIdProducto(this.resultset.getInt("idProducto"));
        lineaDocumento_local.setCantidad(this.resultset.getInt("cantidad"));
        lineaDocumento_local.setPrecioUnitario(this.resultset.getDouble("precioUnitario"));
        return lineaDocumento_local;
    }
    
    @Override
    protected String obtenerID(){
        return lineaDocumento.getIdLinea().toString();
    }
    
    @Override
    protected String obtenerIDcategoria(){
        return "idLinea";
    }
    
    @Override
    protected String obtenerListaDeAtributosYValoresParaActualizacion(){
        String sql = " idLinea = ";
        sql = sql.concat(this.lineaDocumento.getIdLinea().toString());
        sql = sql.concat(", idDocumento = ");
        sql = sql.concat("'" + this.lineaDocumento.getIdDocumento().toString() + "'");
        sql = sql.concat(", idProducto = ");
        sql = sql.concat("'" + this.lineaDocumento.getIdProducto().toString() + "'");
        sql = sql.concat(", cantidad = ");
        sql = sql.concat("'" + this.lineaDocumento.getCantidad().toString() + "'");
        sql = sql.concat(", precioUnitario = ");
        sql = sql.concat("'" + this.lineaDocumento.getPrecioUnitario().toString() + "'");
        return sql;
    }
}
