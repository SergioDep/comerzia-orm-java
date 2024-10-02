package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;
import pe.edu.pucp.comerzia.db.DAOImpl;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.PersonaDAO;

public class PersonaDAOImpl extends DAOImpl implements PersonaDAO {

    private Persona persona;

    public PersonaDAOImpl() {
        super("Persona");
        this.persona = null;
    }

    @Override
    public Integer insertar(Persona persona) {
        this.retornarLlavePrimaria = true;
        this.persona = persona;
        Integer id = insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsert() {
        // return "nombre, estado, descripcion";
        return "dni, nombreCompleto, telefono, correo, direccion";
    }

    @Override
    protected String obtenerListaDeValoresParaInsert() {
        String sql = "";
        sql = sql.concat("'" + this.persona.getDni() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.persona.getNombreCompleto() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.persona.getTelefono() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.persona.getCorreo() + "'");
        sql = sql.concat(", ");
        sql = sql.concat("'" + this.persona.getDireccion() + "'");
        return sql;
    }

    @Override
    public Integer modificar(Persona persona) {
        this.persona = persona;
        return modificar();
    }

    @Override
    public Integer eliminar(Persona persona) {
        this.persona = persona;
        return eliminar();
    }

    @Override
    public ArrayList<Persona> listarTodos() {
        ArrayList<Persona> lista = new ArrayList<>();
        listar();
        try {
            while (this.resultset.next()) {
                lista.add(generaPersonaResult());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public Persona obtenerPorId(Integer idPersona) {
        obtener_Por_Id(idPersona);
        try {
            return generaPersonaResult();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Persona generaPersonaResult() throws SQLException {
        Persona persona_local = new Persona();
        persona_local.setIdPersona(this.resultset.getInt("idPersona"));
        persona_local.setDni(this.resultset.getString("dni"));
        persona_local.setNombreCompleto(this.resultset.getString("nombreCompleto"));
        persona_local.setTelefono(this.resultset.getString("telefono"));
        persona_local.setCorreo(this.resultset.getString("correo"));
        persona_local.setDireccion(this.resultset.getString("direccion"));
        return persona_local;
    }

    @Override
    protected String obtenerID() {
        return persona.getIdPersona().toString();
    }

    @Override
    protected String obtenerIDcategoria() {
        return "idPersona";
    }

    @Override
    protected String obtenerListaDeAtributosYValoresParaActualizacion() {
        String sql = " idPersona = ";
        sql = sql.concat(this.persona.getIdPersona().toString());
        sql = sql.concat(", dni = ");
        sql = sql.concat("'" + this.persona.getDni() + "'");
        sql = sql.concat(", nombreCompleto = ");
        sql = sql.concat("'" + this.persona.getNombreCompleto() + "'");
        sql = sql.concat(", telefono = ");
        sql = sql.concat("'" + this.persona.getTelefono() + "'");
        sql = sql.concat(", correo = ");
        sql = sql.concat("'" + this.persona.getCorreo() + "'");
        sql = sql.concat(", direccion = ");
        sql = sql.concat("'" + this.persona.getDireccion() + "'");
        return sql;
    }
}
