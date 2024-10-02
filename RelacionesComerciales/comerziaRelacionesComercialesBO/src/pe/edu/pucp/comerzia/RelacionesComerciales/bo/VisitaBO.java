/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.RelacionesComerciales.bo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.VisitaDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl.VisitaDAOImpl;

import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Visita;

public class VisitaBO {
    
    // Creamos la clase.
    private VisitaDAO visitaDAO; 
    
    public VisitaBO(){
        this.visitaDAO = new VisitaDAOImpl(); // Constructor
    }
    
    public Integer insertar(Integer idVisita, Date fecha, Time duracion, Integer idEmpresa, Integer idPersona){
        Visita visita = new Visita(idVisita, fecha, duracion, idEmpresa, idPersona);
        return this.visitaDAO.insertar(visita);
    }
    
    public Integer modificar(Integer idVisita, Date fecha, Time duracion, Integer idEmpresa, Integer idPersona){
        Visita visita = new Visita();
        visita.setIdVisita(idVisita);
        visita.setFecha(fecha);
        visita.setDuracion(duracion);
        visita.setIdEmpresa(idEmpresa);
        visita.setIdPersona(idPersona);
        return visitaDAO.modificar(visita);
        
    }
    
    public Integer eliminar(Visita visita){
        return this.visitaDAO.eliminar(visita);
    }
    
    public Visita obtenerPorId(Integer idVisita){
        return this.visitaDAO.obtenerPorId(idVisita); // Pendiente
    }
    
    public ArrayList<Visita> listarTodos(){
        return this.visitaDAO.listarTodos();
    }
    
    
    
}